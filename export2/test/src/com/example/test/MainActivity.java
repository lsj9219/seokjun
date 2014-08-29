package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity{
	Context mcontext;
	@Override
	protected void onCreate(Bundle savedInstanceState) 	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mcontext = this;
		ImageButton btn1 = (ImageButton) findViewById(R.id.mainbtn1);
		
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			      Intent intentSubActivity = 	new Intent(getBaseContext(), MapActivity.class);
			      intentSubActivity.putExtra("lat", 36.632603);
			      intentSubActivity.putExtra("lng", 127.453067);
			      startActivity(intentSubActivity);
			}
		});
		
		ImageButton btn2 = (ImageButton) findViewById(R.id.mainbtn2);
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 Intent intentSubActivity = 	new Intent(getBaseContext(), MapActivity.class);
			      intentSubActivity.putExtra("lat",36.632707);
			      intentSubActivity.putExtra("lng",127.458283);
			      startActivity(intentSubActivity);
			}
		});
		ImageButton btn3 = (ImageButton) findViewById(R.id.mainbtn3);
		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			      Intent intentSubActivity = 	new Intent(getBaseContext(), MapActivity.class);
			      intentSubActivity.putExtra("lat", 36.627208);
			      intentSubActivity.putExtra("lng", 127.449883);
			      startActivity(intentSubActivity);
			}
		});
		ImageButton btn4 = (ImageButton) findViewById(R.id.mainbtn4);
		btn4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			      Intent intentSubActivity = 	new Intent(getBaseContext(), MapActivity.class);
			      intentSubActivity.putExtra("lat", 36.625413);
			      intentSubActivity.putExtra("lng", 127.465290);
			      startActivity(intentSubActivity);
			}
		});
	}
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
	}
}
