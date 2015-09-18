package general.test.activemq.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * User: i.mitsakos
 * Date: 1/11/2011
 * Time: 5:28 μμ
 */
@Component
public class JmWorker implements Worker{

    @Override
    @Async
    public void work() {
        System.out.println("Initialize MessageReceiver");
    }
}
