package cn.com.mymodule.utils;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class Random {
	
	public static String r32() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        
		for(int i=0;i<100;i++){
//			System.out.println(format.format(new Date()));
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			System.out.println(uuid);
		}

	}

}
