package joker.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * User: imitsakos
 * Date: 9/7/2014
 * Time: 12:42 μμ
 */

@SpringBootApplication
public class StandaloneApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(StandaloneApplication.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        Statistics statistics= (Statistics)ctx.getBean("stats");
        System.out.println("Last draw: " + statistics.getLastDraw());
    }
}
