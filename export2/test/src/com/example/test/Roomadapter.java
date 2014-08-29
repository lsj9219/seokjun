package com.example.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Roomadapter extends ArrayAdapter<Roomdata>{
	private Context context;
	private int layoutResourceId;
	private ArrayList<Roomdata> roomData;
	
	public Roomadapter(Context context, int layoutResourceId, ArrayList<Roomdata> roomData){
		super(context,layoutResourceId,roomData);
		this.context=context;
		this.layoutResourceId=layoutResourceId;
		this.roomData=roomData;
	}
	@Override
	public View getView(int position,View convertView, ViewGroup parent){
		View row = convertView;
		if(row == null){
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent,false);
		}
		
		
		return row;
	}
}
