package com.example.searchlist;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter implements
		ItemView.OnImageClickListener {

	ArrayList<MyData> mList;
	Context mContext;
	OnItemImageClickListener mListener;
	MyPopupWindow myPopup;

	public interface OnItemImageClickListener {
		public void onImageClick(MyData data);
	}

	public void setOnItemImageClickListener(OnItemImageClickListener listener) {
		mListener = listener;
	}

	public MyAdapter(Context context, ArrayList<MyData> list) {
		mContext = context;
		mList = list;
		myPopup = new MyPopupWindow(context);
	}

	public void add(MyData data) {
		mList.add(data);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ItemView v;
		if (convertView == null) {
			v = new ItemView(mContext);
			v.setOnImageClickListener(this);
		} else {
			v = (ItemView) convertView;
		}
		MyData item = mList.get(position);

		v.setData(item);

		return v;
	}

	@Override
	public void onImageClick(MyData data) {
		// TODO Auto-generated method stub
		if (mListener != null) {
			mListener.onImageClick(data);
		}
	}
}
