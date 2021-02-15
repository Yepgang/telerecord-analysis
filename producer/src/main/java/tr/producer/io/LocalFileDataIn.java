package tr.producer.io;

import tr.common.bean.Data;
import tr.common.bean.DataIn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取数据，存在特定的List中
 */
public class LocalFileDataIn implements DataIn {
    private BufferedReader reader = null;

    public LocalFileDataIn( String path ) {
        setPath(path);
    }

    public void setPath(String path) {
        try {
            reader = new BufferedReader( new InputStreamReader( new FileInputStream(path), "UTF-8" ));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {
        List<T> tr = new ArrayList<T>();

        try {
            String line = null;
            while ((line = reader.readLine()) != null){
                T t = clazz.newInstance();
                t.setValue(line);
                tr.add(t);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return  tr;
    }

    public void close() throws IOException {
        if(reader != null){
            reader.close();
        }
    }
}
