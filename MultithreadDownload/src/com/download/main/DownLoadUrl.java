package com.download.main;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.download.utils.DownLoadUtils;

//ͼƬ��ַhttp://n.sinaimg.cn/sinacn/w560h558/20180213/4c4b-fyrpeie3257932.jpg
public class DownLoadUrl {

	public static void main(String[] args) {
		
		if(verification(DownLoadUtils.FILE_URL,DownLoadUtils.RENAME)){//ͨ��
			
			if(!DownLoadUtils.FILE_URL.contains(";")){//����
				downLoad(DownLoadUtils.SAVE_PATH,DownLoadUtils.FILE_URL,DownLoadUtils.RENAME);
			}else{//���
				String[] split = DownLoadUtils.FILE_URL.split(";");
				String[] re_split = DownLoadUtils.RENAME.split(";");
				
			ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(split.length);
			
			for (int i = 0; i < re_split.length; i++) {	
			String	url=split[i];
			String	rename=re_split[i];
			newFixedThreadPool.execute(new Runnable() {
					
					@Override
					public void run() {
						//for (int i = 0; i < re_split.length; i++) { //�˴�����ͬһ���߳���������������Ϊ��run������ִ��
							System.out.println(Thread.currentThread().getName());
							downLoad(DownLoadUtils.SAVE_PATH,url,rename);
						//}
						
					}
				});
			}
			
				newFixedThreadPool.shutdown();
			}
				
		}else{//��ͨ��
			System.out.println("���޸�file.properties");
		}
	}
	
	
	public static boolean verification(String key,String value){
		if(key.contains(";")){//����ַ�
			String[] url_split = key.split(";");
			int length = url_split.length;
			if(!value.contains(";")){
				System.out.println("·�������Ʋ���Ӧ");
				return false;
			}else{
				String[] rename_split = value.split(";");
				int re_length = rename_split.length;
				if(re_length==length){
					return true;
				}else{
					return false;
				}
			}
		}else{//�����ַ�
			System.out.println("�����urlΪ����·��");
			return true;
		}
		
	}
	
	public static void downLoad(String save_path,String url1,String rename){
		InputStream inputStream=null;
		FileOutputStream fileOutputStream=null;
			
		try {
			fileOutputStream = new FileOutputStream(save_path+rename);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			URL url=new URL(url1);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			inputStream = connection.getInputStream();
			byte[] buffer=new byte[1024];
			int hasRead=0;
			while ((hasRead=inputStream.read(buffer))!=-1) {
				fileOutputStream.write(buffer,0,hasRead);
				fileOutputStream.flush();
			}
		} catch (Exception e) {
			System.out.println("������Ч");
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
