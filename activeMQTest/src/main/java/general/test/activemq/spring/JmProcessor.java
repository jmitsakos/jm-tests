package general.test.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * User: i.mitsakos
 * Date: 1/11/2011
 * Time: 5:28 μμ
 */
public class JmProcessor implements Processor {

    @Autowired
	private Worker worker;

    public void process (){
        System.out.println("Started at " + new Date());
        worker.work();

    }

    /*public void setWorker(Worker worker) {
        this.worker = worker;
    }*/
}
