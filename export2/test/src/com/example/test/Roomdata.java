package com.example.test;

import android.os.Parcel;
import android.os.Parcelable;

public class Roomdata implements Parcelable{
	private String index;
	private String price;
	private String name;
	private String phone;
	private String location;
	private String option;
	private String imgname;
	private int lat;
	private int lng;
	public PictureGet pg;
	public Roomdata(){
		this.index = "";
		this.price = "";
		this.name = "";
		this.phone = "";
		this.location = "";
		this.option = "";
		this.imgname = "";
		this.lat = 0;
		this.lng = 0;
	}
	public Roomdata(String index,String price, String name, String phone, String location, String option, int lat, int lng,String imgname){
		this.index = index;
		this.price = price;
		this.name = name;
		this.phone = phone;
		this.location = location;
		this.option = option;
		this.lat = lat;
		this.lng = lng;
		this.imgname = imgname;
	}
	public String getIndex(){
		return index;
	}
	public String getPrice(){
		return price;
	}
	public String getName(){
		return name;
	}
	public String getPhone(){
		return phone;
	}
	public String getLocation(){
		return location;
	}
	public String getOption(){
		return option;
	}
	public int getlat(){
		return lat;
	}
	public int getlng(){
		return lng;
	}
	public String getImgname(){
		return imgname;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeString(price);
		arg0.writeString(name);
		arg0.writeString(phone);
		arg0.writeString(location);
		arg0.writeString(option);
		arg0.writeString(imgname);
	}
	public static final Parcelable.Creator<Roomdata> CREATOR = new Creator<Roomdata>(){
		public Roomdata createFromParcel(Parcel source){
			Roomdata a = new Roomdata();
			a.price = source.readString();
			a.name = source.readString();
			a.phone = source.readString();
			a.location = source.readString();
			a.option = source.readString();
			a.imgname = source.readString();
			return a;
		}

		@Override
		public Roomdata[] newArray(int arg0) {
			// TODO Auto-generated method stub
			return new Roomdata[arg0];
		}
	};
}
