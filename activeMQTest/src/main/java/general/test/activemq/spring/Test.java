package general.test.activemq.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.JMSException;

/**
 * User: i.mitsakos
 * Date: 1/11/2011
 * Time: 5:34 μμ
 */
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-config.xml", Test.class);

        JmsMessageProducer producer = (JmsMessageProducer)c.getBean("producer");
        try {
            producer.sendObjectMessages();
        } catch (JMSException e) {
            System.out.println("Exception occurred in jms message producer: " + e.getMessage());
        }
    }
}
