package com.example.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.android.gcm.GCMRegistrar;
public class GcmActivity extends Activity{
	phpDown task;
	CheckBox cb;
	@Override 
	public void onCreate(Bundle savedInstanceState) {
	
	super.onCreate(savedInstanceState);
	 
	setContentView(R.layout.gcmactivity);
	 
	registerGcm();
	
	cb = (CheckBox)findViewById(R.id.cb1);
	cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			task = new phpDown();
		    task.execute("http://14.63.219.212/sj/gcm.php");
		   
		}
	});
	
	}

	public void registerGcm() {
	 
	GCMRegistrar.checkDevice(this);
	GCMRegistrar.checkManifest(this);
	 
	final String regId = GCMRegistrar.getRegistrationId(this);
	 //APA91bH2ATzkSTREfQFWb4B9nevgwFPcmyZmHPOtyrLrpapIH4ePnZOVeJBxU-x68et6YCm4ZEYAjmsnh9DapKLXFcZmvfm859K_bqD8kKPDjZKoXw8UD0Z3zEY0E3zBjfR6fsrk_fjc1srNnTn8HGrjpdAMDD8lXA
	if (regId.equals("")) {
		GCMRegistrar.register(this, "926497816162" );
	} else {
	Log.e("id", regId);
	}
	//////////////////////// http 	
	} 	
	public class phpDown extends AsyncTask<String, Integer,String>{
	   	 @Override
	        protected String doInBackground(String... urls) {
	            StringBuilder jsonHtml = new StringBuilder();
	            try{
	                      // ���� url ����
	                      URL url = new URL(urls[0]);
	                      // Ŀ�ؼ� ��ü ����
	                      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	                      // ����Ǿ�����.
	                      if(conn != null){
	                         conn.setConnectTimeout(10000); 
	                         //conn.setUseCaches(false);
	                         // ����Ǿ��� �ڵ尡 ���ϵǸ�.
	                         if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
	                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	                            for(;;){
	                                // ���� �������� �ؽ�Ʈ�� ���δ����� �о� ����.  
	                                String line = br.readLine();
	                                if(line == null) break;
	                                // ����� �ؽ�Ʈ ������ jsonHtml�� �ٿ�����
	                                jsonHtml.append(line + "\n");
	                             }
	                          br.close();
	                       }
	                        conn.disconnect();
	                     }
	                   } catch(Exception ex){
	                      ex.printStackTrace();
	                   }
	            return jsonHtml.toString();
	            
	        }
	        
	   }

}
