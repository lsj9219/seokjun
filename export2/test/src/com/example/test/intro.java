package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class intro extends Activity {
	Handler h;
		@Override
		protected void onCreate(Bundle savedInstanceState) 	{
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.intro);
			h = new Handler();
			h.postDelayed(irun, 1500);
		}
		Runnable irun = new Runnable(){
			public void run(){
			Intent i = new Intent(intro.this,TebActivity.class);
			startActivity(i);
			finish();
			overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
			}
		};
		
		@Override
		public void onBackPressed(){
			super.onBackPressed();
			h.removeCallbacks(irun);
		}
}