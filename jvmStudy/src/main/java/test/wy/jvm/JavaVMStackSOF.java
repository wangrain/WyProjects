package test.wy.jvm;

/**
 * 名称：  JavaVMStackSOF
 * 作者:   rain.wang
 * 日期:   2017/7/14
 * 简介:  Java虚拟机栈内存溢出
 * VM args : -Xss128k
 */
public class JavaVMStackSOF {
    private int stackLength =1;
    public void stackLeak(){
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF stackSOF = new JavaVMStackSOF();
        try{
            stackSOF.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length = "+ stackSOF.stackLength);
            throw e;
        }
    }
}
