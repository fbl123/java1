package com.kaishengit.dto;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 *字符转码工具类
 */
public class StringUtil {

    public static String utf8(String str){
        if(StringUtils.isNotBlank(str)){
            try{
                str=new String(str.getBytes("iso-8859-1"),"utf-8");
                return str;
            }catch (UnsupportedEncodingException e){
                throw new UnsupportedOperationException("转换"+str+"出现异常");
            }

        }
       return null;
    }
}
