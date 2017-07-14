package test.wy.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 名称：  JavaVMStackOOM
 * 作者:   rain.wang
 * 日期:   2017/7/14
 * 简介:  通过创建线程让栈内存溢出
 *  VM args: -Xss2m
 */
public class JavaVMStackOOM {

    public void stackLeakByThread(){
        List<Thread> list = new ArrayList<Thread>();
        while(true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            list.add(thread);
            thread.start();
            System.out.println(list.size());
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM stackOOM = new JavaVMStackOOM();
        stackOOM.stackLeakByThread();
    }
}
