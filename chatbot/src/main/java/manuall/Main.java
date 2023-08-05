package manuall;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        Properties prop = new Properties();
        InputStream input = new FileInputStream("src/main/resources/application.properties");

        prop.load(input);

        System.out.println(prop.getProperty("mocknumber"));
        Chatbot.sendMessage("Hello World!", prop.getProperty("mocknumber"));
    }
}