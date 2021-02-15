package tr.common.bean;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * 源接口，包括设置源路径和读取数据
 */
public interface DataIn extends Closeable {
    public void setPath(String path);


    public <T extends Data> List<T> read(Class<T> clazz) throws IOException;
}
