package tr.producer.io;


import tr.common.bean.DataOut;

import java.io.*;

/**
 * 输出数据对象，以callog对象的形式存储
 */
public class LocalFileDataOut implements DataOut {
    private PrintWriter writer = null;

    public LocalFileDataOut( String path ) {
        setPath(path);
    }

    public void setPath(String path) {
        try {
            writer = new PrintWriter( new OutputStreamWriter( new FileOutputStream(path), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(Object data) throws Exception {
        write(data.toString());
    }

    public void write(String data) throws Exception {
        writer.println(data);
        writer.flush();
    }

    public void close() throws IOException {
        if(writer != null){
            writer.close();
        }
    }
}
