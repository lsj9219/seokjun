package com.example.test;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class TebActivity extends TabActivity{
	TabHost mTab;
	@Override
	protected void onCreate(Bundle savedInstanceState) 	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tebactivity);
		
		TabHost mTab = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		
		intent = new Intent(this,MainActivity.class);
		spec = mTab.newTabSpec("지도선택").setIndicator("지도선택").setContent(intent);
		mTab.addTab(spec);
		
		intent = new Intent(this,SearchActivity.class);
		spec = mTab.newTabSpec("방검색").setIndicator("방검색").setContent(intent);
		mTab.addTab(spec);
		
		intent = new Intent(this,SettingActivity.class);
		spec = mTab.newTabSpec("설정").setIndicator("설정").setContent(intent);
		mTab.addTab(spec);
		
	}
	
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
	}
}
