package tr.common.constant;

import tr.common.bean.Val;

public enum Names implements Val {

    ;
    private String name;

    private Names(String name){
        this.name = name;
    }

    public void setValue(Object val) {
        this.name = (String)val;
    }

    public String getVaule() {
        return name;
    }
}
