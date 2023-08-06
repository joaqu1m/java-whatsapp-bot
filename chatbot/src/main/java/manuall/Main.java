package manuall;
import manuall.dao.ActorJdbcDAO;
import manuall.dto.Actor;
import manuall.services.Chatbot;
import manuall.services.ContratanteCRM;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
public class Main {

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void main(String[] args) throws InterruptedException, IOException, UnsupportedFlavorException {

//        ActorJdbcDAO actorJdbcDAO = new ActorJdbcDAO();
//
//        List<Actor> actorList = actorJdbcDAO.getAll();
//        for (Actor actor : actorList) {
//            System.out.println(actor);
//        }

        Chatbot chatbot = new Chatbot(new ContratanteCRM());
        chatbot.readMessages();
//        Chatbot.sendMessage("Olá, bom dia! Eu sou o Manuel, o assistente virtual da Manuall. Gostaria de saber se você necessita de alguma ajuda.", properties.getProperty("mocknumber"), true);
    }
}