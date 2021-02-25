import bean.CalllogConsumer;
import tr.common.bean.Consumer;

import java.io.IOException;

/**
 * kafka采集flume收集的数据
 * 上传到hbase
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
        Consumer consumer = new CalllogConsumer();

        consumer.consume();

        consumer.close();
    }
}
