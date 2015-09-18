package joker.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by imitsakos on 15/9/2015.
 */
@Configuration
public class OtherConfig {

    @Bean(name = "stats")
    public Statistics getStatistics(){
        return new Statistics();
    }
}
