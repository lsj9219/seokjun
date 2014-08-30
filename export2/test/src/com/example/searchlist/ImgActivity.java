package com.example.searchlist;

import com.example.test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImgActivity extends Activity {

	public static final String PARAM_NAME = "name";
	public static final String PARAM_MYDATA = "mydata";
	MyData d;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.search_myactivity_layout);
		Intent i = getIntent();

		d = (MyData) i.getParcelableExtra(PARAM_MYDATA);

		((ImageView) findViewById(R.id.imageView1))
				.setImageResource(d.imageId);
		//((TextView) findViewById(R.id.detailTitle)).setText(d.title);
		//((TextView) findViewById(R.id.detailDesc)).setText(d.description);

	}
}
