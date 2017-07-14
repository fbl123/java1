package com.kaishengit.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/7/14.
 */
public class StringUtils {

   static Logger logger = LoggerFactory.getLogger(StringUtils.class);
     public static String newString(String str){
         if(!org.springframework.util.StringUtils.isEmpty(str)){

            try{
                str = new String(str.getBytes("iso-8859-1"),"utf-8");
                return str;
            }catch (UnsupportedEncodingException e){
                logger.warn("{}转码失败",str);
                throw new RuntimeException(str+"转码失败");
            }
         }
        return null;
     }

}



