package be.utils;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class UserSender {

    private static Connection connection;
    private static Session session;
    private static MessageProducer messageProducer;

    public static void sendUserXml(String file) throws JMSException{


        String url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("OUT_QUEUE");
        messageProducer = session.createProducer(queue);
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            connection.start();

            String line;
            String text = "";
            while ((line = bufferedReader.readLine()) != null) {
                text += line;
            }
            Message msg = session.createTextMessage(text);
            messageProducer.send(msg);
            connection.close();
            bufferedReader.close();
        }
        catch(Exception e){

        }
    }

}
