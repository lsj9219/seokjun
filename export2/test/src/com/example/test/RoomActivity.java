package com.example.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RoomActivity  extends Activity{
	private ListView listView;
	private Roomdata roomData;
	private Bitmap img;
	@Override
	protected void onCreate(Bundle savedInstanceState) 	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.roomactivity);
		ImageView imgView = (ImageView)findViewById(R.id.imageView1);
		TextView tvText1 = (TextView)findViewById(R.id.textView1);
		TextView tvText2 = (TextView)findViewById(R.id.textView2);
		TextView tvText3 = (TextView)findViewById(R.id.textView3);
		TextView tvText4 = (TextView)findViewById(R.id.textView4);
		TextView tvText5 = (TextView)findViewById(R.id.textView5);
		
		Bundle bundle = getIntent().getExtras();
		roomData = bundle.getParcelable("parcel");
		img = bundle.getParcelable("image");
		
		imgView.setImageBitmap(img);
		tvText1.setText(roomData.getPrice());
		tvText2.setText(roomData.getName());
		tvText3.setText(roomData.getLocation());
		tvText4.setText(roomData.getPhone());
		tvText5.setText(roomData.getOption());
		
	    //roomData = new ArrayList<Roomdata>();
		//Roomdata room1 = new Roomdata(price,name,phone,location,option,1,2);
		//roomData.add(room1);
		//Toast.makeText(this, roomData.getName(), Toast.LENGTH_LONG).show();
		//Roomadapter roomAdapter = new Roomadapter(this,R.layout.room_details,roomData);
		//listView.setAdapter(roomAdapter);
		
	}
}
