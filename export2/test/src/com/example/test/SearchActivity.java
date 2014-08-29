package com.example.test;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.test.RangeSeekBar.OnRangeSeekBarChangeListener;

public class SearchActivity extends Activity {
	Context mcontext;
	ArrayList<String> noteList;
	TextView tv1;
	TextView tv2;
	Button btn1;
	RadioGroup rg;
	RangeSeekBar<Integer> seekBar1;
	RangeSeekBar<Integer> seekBar2;
	boolean hasukCheck=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) 	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchactivity);
		
		mcontext = this;
		
		noteList = new ArrayList<String>();
		noteList.add("정문");
		noteList.add("중문");
		noteList.add("서문");
		noteList.add("후문");
		
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub	
				if(checkedId==R.id.radio0)
				{
					tv1.setText("보증금(" + seekBar1.getSelectedMinValue() + " ~ " + seekBar1.getSelectedMaxValue() +")");  
					seekBar1.setVisibility(View.VISIBLE);	
				}
				else if(checkedId==R.id.radio1)
					{
						tv1.setText("");
						seekBar1.setVisibility(View.INVISIBLE);
					}
			}
		});
		tv1 = (TextView)findViewById(R.id.monthmoney);
		tv2 = (TextView)findViewById(R.id.bojeung);
		
		btn1 = (Button)findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder chooseDlg = new AlertDialog.Builder(SearchActivity.this);
				chooseDlg.setTitle("위치선택");
				ArrayAdapter<String> arrayAdt = new ArrayAdapter<String>(SearchActivity.this,android.R.layout.select_dialog_item,noteList);
				
				chooseDlg.setAdapter(arrayAdt, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if(which==0)
							btn1.setText("정문선택");
						else if(which==1)
							btn1.setText("중문선택");
						else if(which==2)
							btn1.setText("서문선택");
						else if(which==3)
							btn1.setText("후문선택");
						}
				});
				chooseDlg.setCancelable(true);
				chooseDlg.show();
			}
		});
		
		Button btn2 = (Button)findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  Intent intentSubActivity = 	new Intent(getBaseContext(), com.example.search.ListActivity.class);   
			      startActivity(intentSubActivity);
			}
		});
		ViewGroup layout = (ViewGroup) findViewById(R.id.layout);

		tv1.setText("보증금(" + 0 + " ~ " + 500 +")");  
		seekBar1 = new RangeSeekBar<Integer>(0, 500, mcontext);
		seekBar1.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener<Integer>() {
		        @Override
		        public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
		                // handle changed range values
		            tv1.setText("보증금(" + minValue + " ~ " + maxValue +")");  
		        	Log.i("a", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
		        }
		});

		layout.addView(seekBar1,4);
		
		tv2.setText("월세(" + 0 + " ~ " + 100 +")");  
		
				// add RangeSeekBar to pre-defined layout
		
		seekBar2 = new RangeSeekBar<Integer>(0, 100, mcontext);
		seekBar2.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener<Integer>() {
		        @Override
		        public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
		                // handle changed range values
		        	 tv2.setText("월세(" + minValue + " ~ " + maxValue +")");
		             Log.i("a", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
		        }
		});

		// add RangeSeekBar to pre-defined layout
		layout.addView(seekBar2,3);
		
		
	}
	
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
	}

}

