package com.wy.tools.file;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 名称：  GetDirSize
 * 作者:   rain.wang
 * 日期:   2016/5/9
 * 简介:
 */
public class GetDirSize {
    public static void main(String[] args) {
        File cDir = new File("C:\\Users\\rain");
        File[] files = cDir.listFiles();
        BigDecimal total = BigDecimal.ZERO;
        for(int i=0;i<files.length;i++){
            if(files[i].isDirectory()){
                long size = getDirSize(files[i]);
                float sizeF = getGb(size);
                BigDecimal sizeB = new BigDecimal(sizeF);
                if(sizeB.setScale(2,BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.ZERO) == 0){
                    continue;
                }
                total.add(sizeB.setScale(2,BigDecimal.ROUND_HALF_UP));
                int maxLen = 100;
                char[] chars = files[i].getName().toCharArray();
                char[] spaceChars = new char[maxLen - chars.length];
                Arrays.fill(spaceChars,' ');
                System.out.println(new StringBuffer(new String(chars)).append(new String(spaceChars)).toString()+sizeB.setScale(2,BigDecimal.ROUND_HALF_UP)+"GB");
            }
        }
        System.out.println("total size:"+total.toString());
    }

    public static long getDirSize(File file){
        long size = 0;
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files == null){
                return 0;
            }
            for (int i=0;i<files.length;i++){
                size += getDirSize(files[i]);
            }
        }else{
            size += file.length();
        }
        return size;
    }

    public static long getMb(long b){
        return b/(1024*1024);
    }

    public static float getGb(long b){
        float i = 1024f;
        return b/(i*i*i);
    }
}
