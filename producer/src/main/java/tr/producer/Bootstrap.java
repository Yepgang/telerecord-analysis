package tr.producer;

import tr.common.bean.Producer;
import tr.producer.bean.LocalFileProducer;
import tr.producer.io.LocalFileDataIn;
import tr.producer.io.LocalFileDataOut;

import java.io.IOException;

/**
 * 生产者启动程序
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
        if (args.length < 2){
            System.out.println("The system parameters are incorrect, please pass in the specified format: java -jar Produce.jar path1 path2");
        }

        Producer producer = new LocalFileProducer();

        producer.setIn(new LocalFileDataIn(args[0]));
        producer.setOut(new LocalFileDataOut(args[1]));

        producer.produce();

        producer.close();
    }
}
