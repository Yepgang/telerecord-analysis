package tr.common.bean;

import java.io.Closeable;

/**
 * 目的接口，包括设置目的路径和写入数据
 */
public interface DataOut extends Closeable {
    public void setPath(String path);

    public void write( Object data ) throws Exception;
    public void write(String data) throws Exception;
}
