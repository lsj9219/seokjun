package com.example.searchlist;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.test.PictureGet;
import com.example.test.R;
import com.example.test.Roomdata;

public class ListActivity extends Activity implements OnScrollListener {

	ListView listView;
	ArrayList<MyData> dataList = new ArrayList<MyData>();
	MyAdapter adapter;
	EditText editText1;
	private static final String LOG = "DynamicListViewActivity";
	private LayoutInflater mInflater;
	private boolean mLockListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout2);

		mLockListView = true;
		adapter = new MyAdapter(this, dataList);

		listView = (ListView) findViewById(R.id.listView1);

		mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		listView.addFooterView(mInflater.inflate(R.layout.search_footer, null));

		listView.setOnScrollListener(this);

		listView.setAdapter(adapter);

		addItems(20);

		adapter.setOnItemImageClickListener(new MyAdapter.OnItemImageClickListener() {
			@Override
			public void onImageClick(MyData data) {
				LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				View popupView = layoutInflater.inflate(R.layout.search_popup, null);
				final PopupWindow popupWindow = new PopupWindow(popupView,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

				ImageView popupImg = (ImageView) popupView
						.findViewById(R.id.popupImg);
				popupImg.setImageResource(data.imageId);
				Button btnDismiss = (Button) popupView
						.findViewById(R.id.dismiss);
				btnDismiss.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						popupWindow.dismiss();
					}
				});

				popupWindow.showAtLocation(listView, Gravity.CENTER, 0, 0);
			}
		});

		/**
		 * Enabling Search Filter
		 * */
		// editText1 = (EditText) findViewById(R.id.editText1);
		// editText1.addTextChangedListener(new TextWatcher() {
		//
		// @Override
		// public void onTextChanged(CharSequence cs, int arg1, int arg2,
		// int arg3) {
		// // When user changed the Text
		// // ((MyAdapter) MainActivity.this.adapter).getFilter().filter(cs);
		// }
		//
		// @Override
		// public void beforeTextChanged(CharSequence arg0, int arg1,
		// int arg2, int arg3) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void afterTextChanged(Editable arg0) {
		// // TODO Auto-generated method stub
		// }
		// });

		/**
		 * Search button function
		 **/
		// editText2 = (EditText) findViewById(R.id.editText2);
		// Button btnSearch = (Button) findViewById(R.id.btnSearch);
		// btnSearch.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// String title = editText1.getText().toString();
		// // String desc = editText2.getText().toString();
		// // MyData data = new MyData(R.drawable.ic_launcher, title,
		// // desc);
		// // adapter.add(data);
		// }
		// });

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Roomdata z = new Roomdata("0","200/30","±è¿¬¾Æ","010-1234-5678","º¹´ë2µ¿ 586¹øÁö","¾Ö¿Ïµ¿¹° ±ÝÁö",123,123,"asd");
				PictureGet picture = new PictureGet("http://14.63.219.212/sj/images/");
				picture.getRemoteImage("oneroom");
				//Bitmap a  = BitmapBitmapFactory.decodeResource(getResources(), R.drawable.d);
				//Resources r = getResources();
				//BitmapDrawable d = (BitmapDrawable) r.getDrawable(R.drawable.d);
				//Bitmap gg = d.getBitmap();
				Intent i = new Intent(ListActivity.this, com.example.searchlist.MyActivity.class);
			     
				
				//MyData d = dataList.get((int) adapter.getItemId(position));
				//i.putExtra(MyActivity.PARAM_MYDATA, d);
				startActivity(i);
			}
		});

	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		int count = totalItemCount - visibleItemCount;

		if (firstVisibleItem >= count && totalItemCount != 0
				&& mLockListView == false) {
			Log.i(LOG, "Loading next items");
			addItems(20);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	private void addItems(final int size) {
		mLockListView = true;

		Runnable run = new Runnable() {
			@Override
			public void run() {
				String str = new String();
				// ..
				dataList.add(new MyData(R.drawable.a, str, "Åõ·ë ¿ù¼¼ - 500/65"));
				dataList.add(new MyData(R.drawable.b, str, "ÇÏ¼÷ 35"));
				dataList.add(new MyData(R.drawable.c, str, "Åõ·ë ¿ù¼¼ - 300/70"));
				dataList.add(new MyData(R.drawable.d, str, "¿ø·ë 200/30"));
				dataList.add(new MyData(R.drawable.e, str, "¿ø·ë 220/35"));

				adapter.notifyDataSetChanged();
				mLockListView = false;
			}
		};
		Handler handler = new Handler();
		handler.postDelayed(run, 1000);
	}
}