package tr.common.bean;

import java.io.Closeable;

/**
 * 生产者接口，包括设置源/目的对象和生产过程
 */
public interface Producer extends Closeable {
    public void setIn(DataIn in);
    public void setOut(DataOut out);
    public void produce();
}
