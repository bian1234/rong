package com.byk.rong.commen.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

/**
 * @Author: ykbian
 * @Date: 2018/7/27 16:24
 * @Todo: 盐值生成器
 */
@Component
public class SaltUtil {


    public String getSlat(String string) {
        String str = System.currentTimeMillis()+"";
        String m =str.substring(str.length()-10, str.length());
        String salt = samllSalt(string+str+m);
        return salt;
    }

    //生成MD5
    public  String samllSalt(String message) {
        String md5 = "";
        try {
            // 创建一个md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageByte = message.getBytes("UTF-8");
            // 获得MD5字节数组,16*8=128位
            byte[] md5Byte = md.digest(messageByte);
            // 转换为16进制字符串
            md5 = bytesToHex(md5Byte);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/7/27 16:22
     *@Description:  二进制转十六进制
     *@param:
     */
    public  String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if(num < 0) {
                num += 256;
            }
            if(num < 16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }
}
