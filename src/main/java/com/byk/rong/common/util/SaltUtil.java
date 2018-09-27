package com.byk.rong.common.util;

import java.util.Random;

/**
 * @Author: ykbian
 * @Date: 2018/8/15 17:30
 * @Todo:   用户盐值生成
 */

public class SaltUtil {



    public static  String createSalt(){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //只需要循环32次即可
        for(int i=0; i<30; ++i){
            //产生0-61的数字
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }


}
