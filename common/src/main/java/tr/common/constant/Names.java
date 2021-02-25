package tr.common.constant;

import tr.common.bean.Val;

/**
 * 名称常量枚举类
 */
public enum Names implements Val {
    NAMESPACE("ct")
    ,TABLE("ct:calllog")
    ,CF_CALLER("caller")
    ,CF_CALLEE("callee")
    ,CF_INFO("info")
    ,TOPIC("calllog");


    private final String name;

    private Names( String name ) {
        this.name = name;
    }

    public void setValue(Object val) {

    }

    public String getValue() {
        return name;
    }
}
