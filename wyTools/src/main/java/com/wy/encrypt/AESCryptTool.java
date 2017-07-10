package com.wy.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;



/*
 * remark:使用AES算法对文件或者字符串进行加密，
 * author：rain.wang
 * date: 2017-04-25
 */

public class AESCryptTool {
    private static final String CHARSET = "UTF-8";
    /**
     * AES加密
     * 
     * @param key
     *            密钥信息
     * @param content
     *            待加密信息
     */
    public static byte[] encodeAES(byte[] key, byte[] content) throws Exception {
        // 不是16的倍数的，补足
        int base = 16;
        if (key.length % base != 0) {
            int groups = key.length / base + (key.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(key, 0, temp, 0, key.length);
            key = temp;
        }

        SecretKey secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(new byte[] { 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] tgtBytes = cipher.doFinal(content);
        return tgtBytes;
    }

    /**
     * 先通过AES加密，再将密文byte[]转换为16进制后输出
     * @param key
     * @param content
     * @return
     * @throws Exception
     */
    public static String encodeAESAndHex(String key,String content) throws Exception{
        if(key == null || content == null){
            return "";
        }
        return parseByte2HexStr(encodeAES(key.getBytes(CHARSET),content.getBytes(CHARSET)));
    }

    /**
     * AES解密
     * 
     * @param key
     *            密钥信息
     * @param content
     *            待加密信息
     * @return
     * @throws Exception
     */
    public static byte[] decodeAES(byte[] key, byte[] content) throws Exception {
        // 不是16的倍数的，补足
        int base = 16;
        if (key.length % base != 0) {
            int groups = key.length / base + (key.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(key, 0, temp, 0, key.length);
            key = temp;
        }

        SecretKey secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(new byte[] { 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] tgtBytes = cipher.doFinal(content);
        return tgtBytes;
    }

    /**
     * 先将密文转换为byte[]，在使用AES解密
     * @param key
     * @param content
     * @return
     * @throws Exception
     */
    public static String decodeAESAndHex(String key,String content) throws Exception{
        if(key == null || content == null){
            return "";
        }
        return new String(decodeAES(key.getBytes(CHARSET),parseHexStr2Byte(content)),CHARSET);
    }
    /**
     * 将二进制转换成16进制
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * 
     * @method parseHexStr2Byte
     * @param hexStr
     * @return
     * @throws
     * @since v1.0
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public  static  void  main(String [] args) throws Exception{
        String key ;
        String content ;
        String type ;
        //加密密钥
        if(args.length == 3){
            type = args[0];
            key = args[1];
            content = args[2];
            if("encrypt".equals(type)){
                String encryptResult = encodeAESAndHex(key, content);// 解密
                System.out.println("加密后：" + encryptResult);
            }else if("decrypt".equals(type)){
                String decryptResult = decodeAESAndHex(key, content);// 加密
                System.out.println("解密后：" + decryptResult);
            }else{
                System.out.println("加密类型错误");
            }

        }else {
            key = "1234567890123456";
            content = "6222080403003807801";
            System.out.println("key:["+key+"],content:["+content+"]");
            String encryptResult = encodeAESAndHex(key, content);// 加密
            System.out.println("加密后：" + encryptResult);
            String decryptResult = decodeAESAndHex(key, encryptResult);// 解密
            System.out.println("解密后：" + decryptResult);
        }
    }
}
