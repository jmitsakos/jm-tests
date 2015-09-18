package general.test.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: i.mitsakos
 * Date: 2/11/2011
 * Time: 1:23 μμ
 */
public class JmsMessageProducer {

    @Autowired
    protected JmsTemplate jmsTemplate;

    protected int numberOfMessages = 2;

    public void sendTextMessages() throws JMSException {

        for (int i = 0; i < numberOfMessages; ++i) {

            final String message = "message"+ i;
            final int finalI = i;
            System.out.println("Sending message: " + message);
            jmsTemplate.send(new MessageCreator() {

                public Message createMessage(Session session) throws JMSException {

                    TextMessage textMessage = session.createTextMessage(message);

                    textMessage.setIntProperty("messageCount", finalI);

                    return textMessage;

                }

            });
        }
    }

    public void sendObjectMessages() throws JMSException {

        for (int i = 0; i < numberOfMessages; ++i) {

            final JmsObjectMessage message = new JmsObjectMessage();
            message.setText("message" + i);
            Map<String, String> map = new HashMap<String, String>(0);
            map.put("id", String.valueOf(i));
            message.setParams(map);

            final int finalI = i;
            System.out.println("Sending message: " + message);
            jmsTemplate.send(new MessageCreator() {

                public Message createMessage(Session session) throws JMSException {

                    ObjectMessage objectMessage= session.createObjectMessage(message);

                    objectMessage.setIntProperty("messageCount", finalI);

                    return objectMessage;

                }

            });
        }
    }
}
