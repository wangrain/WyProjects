package test.wy.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 名称：  HeapOOM
 * 作者:   rain.wang
 * 日期:   2017/7/14
 * 简介:  模拟Java堆内存 溢出场景
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while(true){
            list.add(new OOMObject());
        }
    }
}
