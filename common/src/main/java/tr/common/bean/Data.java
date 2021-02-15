package tr.common.bean;

/**
 * 数据对象，处理一切数据
 */
public class Data implements Val {
    public String content;

    public void setValue(Object val) {
        content = (String)val;
    }

    public String getVaule() {
        return content;
    }
}
