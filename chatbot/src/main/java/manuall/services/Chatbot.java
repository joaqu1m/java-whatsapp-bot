package manuall.services;

import manuall.Main;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Chatbot {

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Robot bot;
    private CRM crmSystem;

    public Chatbot() {
        // Default to PrestadorCRM or whatever you want
        this.crmSystem = new PrestadorCRM();
    }
    public Chatbot(CRM crm) {
        this.crmSystem = crm;
    }
    public void someInstanceMethod() {
        this.crmSystem.iniciarProcesso();
    }

    static {
        try {
            bot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void debug() {
        bot.mouseMove(430, 300);
    }

    public static void sendMessage(String message, String number, Boolean openNewChat) throws InterruptedException {

        if (openNewChat) {
            concatKeyPressing(new ArrayList<>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_L)));

            write("https://web.whatsapp.com/send/?phone=" + number);

            keyPress(KeyEvent.VK_ENTER);

            Thread.sleep(2000);
            waitForColor(new Color(42,57,66), 750, 840, 1000);
        } else {
            returnToTyping();
        }

        write(message);

        keyPress(KeyEvent.VK_ENTER);
    }

    public void readMessages() throws InterruptedException, IOException, UnsupportedFlavorException {

        int scrHeight = toolkit.getScreenSize().height;
        int i = 0;
        while(i < 10) {
            int f = 0;
            while (f < scrHeight) {
                if (bot.getPixelColor(405, f).equals(new Color(0, 168, 132))) {
                    bot.mouseMove(300, f);
                    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                    Thread.sleep(5000);

                    String[] clipboardResult = stopToRead();

                    for (String string : clipboardResult) {
                        System.out.println(string);
                    }

                    this.crmSystem.iniciarProcesso();

                    // Executa as ações de CRM
//                    sendMessage("", Main.getProperties().getProperty("mocknumber"), false);

                    returnToTyping();
                    closeCurrentChat();

                    f += 15;
                } else {
                    f += 5;
                }
            }
            bot.mouseMove(430, 300);
            bot.mouseWheel(10);
            i++;
        }
    }

    static String[] stopToRead() throws IOException, UnsupportedFlavorException, InterruptedException {

        bot.keyPress(KeyEvent.VK_CONTROL);
        bot.keyPress(KeyEvent.VK_SHIFT);
        bot.keyPress(KeyEvent.VK_K);
        bot.keyRelease(KeyEvent.VK_K);
        bot.keyRelease(KeyEvent.VK_SHIFT);
        bot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(100);

        write("copy(Array.from(document.querySelectorAll('.selectable-text')).map(function(elemento){return elemento.innerText;}).reverse());");

        keyPress(KeyEvent.VK_ENTER);

        Thread.sleep(100);

        String[] clipboardResult = toolkit.getSystemClipboard().getData(DataFlavor.stringFlavor).toString().split("\n");

        System.out.println(clipboardResult);

        if (clipboardResult.length - 2 > 2) {
            clipboardResult = Arrays.copyOfRange(clipboardResult, 2, clipboardResult.length - 2);
        } else {
            clipboardResult = new String[0];
        }

        return clipboardResult;

    }

    static void write(String text) {
        toolkit.getSystemClipboard().setContents(new StringSelection(text), null);
        concatKeyPressing(new ArrayList<>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_V)));
    }

    static void waitForColor(Color expectedColor, Integer x, Integer y, Integer interval) throws InterruptedException {
        Color color = bot.getPixelColor(750, 840);
        while(!color.equals(expectedColor)) {
            Thread.sleep(interval);
            color = bot.getPixelColor(x, y);
        }
    }

    static void keyPress(Integer keycode) {
        bot.keyPress(keycode);
        bot.keyRelease(keycode);
    }

    static void concatKeyPressing(ArrayList<Integer> keycodes) {
        for (int code : keycodes) {
            bot.keyPress(code);
        }
        for (int i = keycodes.size() - 1; i >= 0; i--) {
            bot.keyRelease(keycodes.get(i));
        }
    }

    static void returnToTyping() {
        bot.mouseMove(750, 840);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    static void closeCurrentChat() {
        keyPress(KeyEvent.VK_ESCAPE);
    }
}
