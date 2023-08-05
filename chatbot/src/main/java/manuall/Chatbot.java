package manuall;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Chatbot {

    static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    static Robot bot;

    static {
        try {
            bot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMessage(String message, String number) throws InterruptedException {

        concatKeyPressing(new ArrayList<>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_L)));

        write("https://web.whatsapp.com/send/?phone=" + number);

        keyPress(KeyEvent.VK_ENTER);

        Thread.sleep(2000);
        waitForColor(new Color(42,57,66), 750, 840, 1000);

        write(message);

        keyPress(KeyEvent.VK_ENTER);
    }

    static void write(String text) {
        clipboard.setContents(new StringSelection(text), null);
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
}
