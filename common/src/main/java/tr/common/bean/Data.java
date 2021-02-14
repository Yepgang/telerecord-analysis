package tr.common.bean;

public class Data implements Val {
    public String content;

    public void setValue(Object val) {
        content = (String)val;
    }

    public String getVaule() {
        return content;
    }
}
