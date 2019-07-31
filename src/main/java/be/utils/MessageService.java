package be.utils;

import be.business.UserBusinessService;
import be.entity.User;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.io.BufferedReader;
import java.io.FileReader;

@Service
public class MessageService {

    @Autowired
    private UserMarshallingServiceImpl userMarshallingService;
    @Autowired
    private UserBusinessService userBusinessService;

    ConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    MessageProducer messageProducer;
    MessageConsumer messageConsumer;

    @Value("${exportPath}")
    String exportPath;

    @PostConstruct
    private void postConstruct() {
        try {
            String url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue inQueue = session.createQueue("IN_QUEUE");
            Queue outQueue = session.createQueue("OUT_QUEUE");
            connection.start();
            messageProducer = session.createProducer(outQueue);
            messageConsumer = session.createConsumer(inQueue);
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message){

                    try {
                        TextMessage textMessage = (TextMessage) message;
                        String body = textMessage.getText();
                        User request = (User) userMarshallingService.doUnMarshalling(body);
                        userBusinessService.updateDiscount(request.getIdUser(), request.getDiscount());
                    }
                    catch (JMSException e){

                    }
                }
            });
        }
        catch (JMSException e){

        }
    }


    public void sendUserXml(String file){

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(exportPath + file));
            String line;
            String text = "";
            while ((line = bufferedReader.readLine()) != null) {
                text += line;
            }
            Message msg = session.createTextMessage(text);
            messageProducer.send(msg);
            bufferedReader.close();
        }
        catch(Exception e){
        }
    }

}
