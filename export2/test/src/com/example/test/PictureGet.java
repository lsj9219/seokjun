package com.example.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PictureGet {
	private static String root;
	Bitmap bitmap;
	public PictureGet(String root1){
		root = root1;
		bitmap = null;
	}
	public void getRemoteImage(final String imageName){
		
		new Thread(){
			public void run(){
				try{
					URL url = new URL(root + imageName);
					URLConnection conn = url.openConnection();
					conn.connect();
					
					BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize=12;
					bitmap = BitmapFactory.decodeStream(bis,null,options);
					bis.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public Bitmap getImage(){
		return bitmap;
	}
}
