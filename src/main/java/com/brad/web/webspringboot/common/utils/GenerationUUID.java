package com.brad.web.webspringboot.common.utils;

import java.util.UUID;

public class GenerationUUID {

	
	private static final String DEFAULT_PREFIX ="0000000000000000";
	/** 
     * 获得一个UUID 
     * @param prefix 字符串前缀
     * @return String UUID 
     */ 
    public static String getUUIDBy16(String prefix){ 
        String uuid = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return uuid.replaceAll("-", "").substring(16); 
    } 
    /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUIDBy16(){ 
        String uuid = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return uuid.replaceAll("-", "").substring(16); 
    } 
    /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUIDBy32(){ 
        String uuid = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return uuid.replaceAll("-", ""); 
    } 
    /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String prototype = UUID.randomUUID().toString().replaceAll("-", "").substring(16); 
        StringBuffer uuid = new StringBuffer(DEFAULT_PREFIX); 
        //去掉“-”符号 
        return uuid.append(prototype).toString(); 
    } 
//    /** 
//     * 获得指定数目的UUID 
//     * @param number int 需要获得的UUID数量 
//     * @return String[] UUID数组 
//     */ 
//    public static String[] getUUID(int number){ 
//        if(number < 1){ 
//            return null; 
//        } 
//        String[] ss = new String[number]; 
//        for(int i=0;i<number;i++){ 
//            ss[i] = getUUIDBy32(); 
//        } 
//        return ss; 
//    } 
    
    public static void main(String[] args){ 
    	System.out.println(getUUID());
    } 
	
}
