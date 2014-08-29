package com.example.test;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.UUID;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UploadActivity extends Activity{
	private static final int REQUEST_PHOTO_ALBUM = 1;
	phpDown task;
	Button btn1;
	EditText edt1;
	EditText edt2;
	EditText edt3;
	Context mcontext;
	ImageButton imgview;
	
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) 	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uploadactivity);
		btn1 = (Button) findViewById(R.id.register);
		edt1 = (EditText) findViewById(R.id.editText1);
		edt2 = (EditText) findViewById(R.id.editText2);
		edt3 = (EditText) findViewById(R.id.editText3);
		imgview = (ImageButton) findViewById(R.id.uploadImage);
		mcontext = this;
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(edt1.getText().toString().equals(""))
					Toast.makeText(mcontext, "이름을 정확히 입력해주세요", Toast.LENGTH_SHORT).show();
				else if(edt2.getText().toString().equals(""))
					Toast.makeText(mcontext, "주소를 제대로 입력해주세요", Toast.LENGTH_SHORT).show();
				else if(edt3.getText().toString().equals(""))
					Toast.makeText(mcontext, "전화번호를 제대로 입력해주세요", Toast.LENGTH_SHORT).show();
				else{
					final Handler handler = new Handler();
					
					new Thread(){
						public void run(){
							handler.post(new Runnable(){
								public void run(){
									progressDialog = ProgressDialog.show(mcontext,"","업로드중입니다");
								}
							});
						
							task = new phpDown();
							task.execute("http://14.63.219.212/sj/jsonpost.php");
							//task.execute("http://14.63.219.212/sj/imagepost.php");
							//Toast.makeText(mcontext, "매물이 등록되었습니다.", Toast.LENGTH_SHORT).show();
							
							handler.post(new Runnable(){
								public void run(){
									progressDialog.cancel();
									
									finish();
								}
							});
						}
						
					}.start();
					
					
					
				}
			}
		});
		
		imgview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK);
				
				intent.setType(Images.Media.CONTENT_TYPE);
				intent.setData(Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent,REQUEST_PHOTO_ALBUM);
			}
		});
	}
	private String filePath;
	private String fileName;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		try{
		if(requestCode==REQUEST_PHOTO_ALBUM){
			Uri uri = getRealPathUri(data.getData());
			filePath = uri.toString();
			fileName = uri.getLastPathSegment();
			
			
			
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 4;
			Bitmap bitmap = BitmapFactory.decodeFile(filePath, options );
			imgview.setImageBitmap(bitmap);
		}
		} catch(Exception e){
			Log.e("test","사진 선택에서 취소누를때");
		}
	}
	 private Uri getRealPathUri(Uri uri) {
         Uri filePathUri = uri;
         if (uri.getScheme().toString().compareTo("content") == 0) {
                 Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null,null, null);
                 if (cursor.moveToFirst()) {
                         int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                         filePathUri = Uri.parse(cursor.getString(column_index));
                 }
         }
         return filePathUri;
 }
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
	}
	
private class phpDown extends AsyncTask<String, Integer,String>{
		private String boundary = "*****";
		private String twoHyphens = "--";
		private String lineEnd = "\r\n";
		private String getPostData(String key,String value){
			String result = twoHyphens + boundary + lineEnd;
			result += "Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd;
			result += lineEnd;
			result += value;
			result += lineEnd;
			return result;			
		}
        @Override
        protected String doInBackground(String... urls) {
        	final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        	 
        	final String tmDevice, tmSerial, androidId;
        	tmDevice = "" + tm.getDeviceId();
        	tmSerial = "" + tm.getSimSerialNumber();
        	androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        	 
        	UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        	String deviceId = deviceUuid.toString();
        	
            StringBuilder jsonHtml = new StringBuilder();
            try{
				// 연결 url 설정
				URL url = new URL(urls[0]);
				// 커넥션 객체 생성
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setUseCaches(false);
				conn.setDoOutput(true);
				conn.setDoInput(true);
				
				conn.setRequestProperty("Accept-Charset","UTF-8");
				conn.setRequestMethod("POST");
				//conn.setRequestProperty("Cache-Control", "no-cache");
				conn.setRequestProperty("Connection","Keep-Alive");
				//응답 데이터 많을 때 일정양 묶어서 보냄
				//conn.setRequestProperty("Transfer-Encoding", "chunked");
				conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
				DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
				
				dos.write(getPostData("name",edt1.getText().toString()).getBytes("UTF-8"));
				dos.write(getPostData("location",edt2.getText().toString()).getBytes("UTF-8"));
				dos.write(getPostData("phone",edt3.getText().toString()).getBytes("UTF-8"));
				dos.write(getPostData("deviceId",deviceId).getBytes("UTF-8"));
				dos.writeBytes(twoHyphens + boundary + lineEnd);
				dos.writeBytes("Content-Disposition:form-data; name=\"uploadedfile\"; filename=\""+fileName+"\""+lineEnd);
				dos.writeBytes(lineEnd);
				
				FileInputStream fis = new FileInputStream(filePath);
				int bytesAvailable = fis.available();
				int maxBufferSize =  1024;
				int bufferSize = Math.min(bytesAvailable, maxBufferSize);
				
				//byte[] buffer = new byte[bufferSize];
				ByteBuffer buffer = ByteBuffer.allocate(maxBufferSize);
	            int bytesRead = fis.read(buffer.array(), 0, bufferSize);
	            int uploadsize = 0;
	            float bytesize = (float)100/(bytesAvailable/bufferSize); //버퍼사이즈의 전체파일사이즈에 대한 퍼센트

				//int bytesRead = fis.read(buffer,0,bufferSize);
				
				Log.e("test","dataoutputstream write 진입점");
				while(bytesRead>0){
					   dos.write(buffer.array(), 0, bufferSize); 
		               bytesAvailable = fis.available();    
		               bufferSize = Math.min(bytesAvailable, maxBufferSize);    
		               bytesRead = fis.read(buffer.array(), 0, bufferSize);
		               uploadsize++;
		               if(uploadsize>2300)
		            	   uploadsize++;
				}
				dos.writeBytes(lineEnd);
				dos.writeBytes(twoHyphens+boundary+twoHyphens+lineEnd);
				
				fis.close();
				dos.flush();
				
				// get a response
				int ch;
				InputStream is = conn.getInputStream();
				StringBuffer b = new StringBuffer();
				while((ch=is.read())!= -1){
					b.append((char)ch);
				}
				
				dos.close();
				
				Toast.makeText(mcontext, fileName.toString(), Toast.LENGTH_SHORT).show();
				/* mysql OutputStreamWriter로 넘기는 부분
				String param = URLEncoder.encode("name","UTF-8") + "=" + URLEncoder.encode(edt1.getText().toString(),"utf-8");
				param+="&"+URLEncoder.encode("location","UTF-8") + "=" + URLEncoder.encode(edt2.getText().toString(),"UTF-8");
				param+="&"+URLEncoder.encode("phone","UTF-8") + "=" + URLEncoder.encode(edt3.getText().toString(),"UTF-8");
				Log.e("test","outputstream 진입");
				OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
				osw.write(param);
				osw.flush();
				*/
				/*
				Log.e("test","DataOutputStream 진입");
				DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
				dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + fileName + "\"" + "\r\n");
				
				Log.e("test","FileInputStream 진입");
				Toast.makeText(mcontext, fileName.toString(), Toast.LENGTH_SHORT).show();
*/                
               } catch(Exception e){
                  e.printStackTrace();
               }
               return jsonHtml.toString();
            
        }
        
        protected void onPostExecute(String str){
          
        }
    
    }
}
