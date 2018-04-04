package com.wy.test;

import java.math.BigDecimal;

/**
 * 名称：  StringTest
 * 作者:   rain.wang
 * 日期:   2016/5/9
 * 简介:
 */
public class StringTest {
    public static void main(String[] args) {

        /**
        List<String> list = Arrays.asList("0","x","a","3","6","5","4","1","2","y");
        list.forEach(e-> System.out.print(e));
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("\n"+("a".compareTo("a")>0));
        list.forEach(e-> System.out.print(e));

        String nowDateStr = DateTimeUtils.getNowDateStr(DateTimeUtils.DATE_FORMAT_YYYYMMDD);
        String ftpPath = nowDateStr.substring(0,4)+ File.separatorChar+nowDateStr.substring(4,6)+File.separatorChar+nowDateStr.substring(6);
        System.out.println(ftpPath);
        File file1 = new File("C:\\Users\\rain\\work\\code\\umbpay_code\\dev_2gPay\\branches\\branch_20170411_qp_pob_check\\dbscript\\encrypt\\README.txt");
        System.out.println(file1.getAbsolutePath());
        File file2 = new File("..\\util\\DateTimeUtils.class");
        System.out.println(file2.getPath());
        System.out.println();
         */

//    String info = "0024|9999004|100000000057|2200004568|1|52012119860826297X|大禹测试001||6226193300168004|10244|0|中国民生银行|110000|110000||BNK0001|0000|绑定成功";
//        String[] message = info.split("\\|", -1);

        String bankAmtStr_Value="\"(3,000.00)\"";
        bankAmtStr_Value = bankAmtStr_Value.replace("\"","").replace("\'","").replace(",","");
        if(bankAmtStr_Value.contains("(")){
            bankAmtStr_Value = bankAmtStr_Value.replace("(","").replace(")","");
            bankAmtStr_Value = "-"+bankAmtStr_Value;
        }
        System.out.println(bankAmtStr_Value);
        System.out.println(new BigDecimal(bankAmtStr_Value));
    }

}
