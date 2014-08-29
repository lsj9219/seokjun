package com.example.search;

import com.example.test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActivity extends Activity {

	public static final String PARAM_NAME = "name";
	public static final String PARAM_MYDATA = "mydata";
	MyData d;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.search_myactivity_layout);
		Intent i = getIntent();
		((ImageView) findViewById(R.id.img))
				.setImageResource(R.drawable.d);
		((TextView) findViewById(R.id.c1)).setText("200/30");
		((TextView) findViewById(R.id.c2)).setText("김연아");
		((TextView) findViewById(R.id.c3)).setText("복대2동 586번지");
		((TextView) findViewById(R.id.c4)).setText("010-1234-5678");
		((TextView) findViewById(R.id.c5)).setText("애완동물 금지");
	}
}
