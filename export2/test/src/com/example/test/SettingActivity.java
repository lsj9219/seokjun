package com.example.test;

import java.util.ArrayList;

import com.example.gcm.GcmActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SettingActivity extends Activity{
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ListView lv;
    Context mcontext;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.settingactivity);
	        mcontext = this;
	          list = new ArrayList<String>();
	          list.add("방등록");
	          list.add("알림등록");
	          list.add("도움말");
	          list.add("개발자정보");

	          adapter = new ArrayAdapter<String>(this,
	                    android.R.layout.simple_list_item_1, list);

	          lv = (ListView) findViewById(R.id.lv);
	          lv.setAdapter(adapter);
	          lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					if(arg2==0)					{
						 Intent intentSubActivity = 	new Intent(getBaseContext(), UploadActivity.class);
			             startActivity(intentSubActivity);
					}
					else if(arg2==1){
						 Intent intentSubActivity = 	new Intent(getBaseContext(), GcmActivity.class);
			             startActivity(intentSubActivity);
					}
					else if(arg3==2){
						Toast.makeText(mcontext, "b", Toast.LENGTH_SHORT).show();
					}
					else if(arg3==3){
						Toast.makeText(mcontext, "c", Toast.LENGTH_SHORT).show();
					}
					
				}
			});
	    }
}
