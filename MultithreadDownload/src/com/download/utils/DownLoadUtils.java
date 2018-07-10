package com.download.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//Õº∆¨Õ¯÷∑http://n.sinaimg.cn/sinacn/w560h558/20180213/4c4b-fyrpeie3257932.jpg
public class DownLoadUtils {					
	
	/**
	 * ∂¡»°≈‰÷√Œƒº˛œ‡πÿ
	 */
	private static final String PROPERTIS_NAME="file.properties";
	public static String FILE_URL=null;
	public static String RENAME=null;
	public static String SAVE_PATH=null;
	
	static{
		InputStream inputStream=null;
		try {
			Properties properties=new Properties();
			inputStream = DownLoadUtils.class.getClassLoader().getResourceAsStream(PROPERTIS_NAME);
			properties.load(inputStream);
			FILE_URL=properties.getProperty("FILE_URL");
			RENAME=properties.getProperty("RENAME");
			SAVE_PATH=properties.getProperty("SAVE_PATH");
		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("∂¡»°≈‰÷√ ß∞‹");
		}finally {
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
