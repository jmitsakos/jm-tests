package general.test.activemq.spring;

import javax.jms.*;

/**
 * User: i.mitsakos
 * Date: 1/11/2011
 * Time: 4:27 μμ
 */

public class MessageReceiver implements MessageListener {

    public MessageReceiver() {
        System.out.println("MessageReceiver is initialized");
    }

    @Override
    public void onMessage(Message message) {

        if(message instanceof TextMessage){

            try {
                System.out.println("Recieved message: "+ ((TextMessage)message).getText());
            } catch (JMSException e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
        } else if(message instanceof ObjectMessage){

            ObjectMessage objectMessage = ((ObjectMessage) message);
            JmsObjectMessage jmsObjectMessage = null;
            try {
                jmsObjectMessage = (JmsObjectMessage)objectMessage.getObject();
                System.out.println("Recieved message id: "+ jmsObjectMessage.getParams().get("id"));
                System.out.println("Recieved message: " + jmsObjectMessage.getText());
            } catch (JMSException e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }

        } else{
            System.out.println("Unknown JMS message");
        }

    }
}
