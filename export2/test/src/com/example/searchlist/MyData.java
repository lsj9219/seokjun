package com.example.searchlist;

import android.os.Parcel;
import android.os.Parcelable;

public class MyData implements Parcelable {
	public int imageId;
	public String title;
	public String description;

	public MyData(Parcel source) {
		imageId = source.readInt();
		title = source.readString();
		description = source.readString();
	}

	public MyData(int imageId, String str, String description) {
		this.imageId = imageId;
		this.title = str;
		this.description = description;
		
		
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(imageId);
		dest.writeString(title);
		dest.writeString(description);
	}

	public static Parcelable.Creator<MyData> CREATOR = new Parcelable.Creator<MyData>() {

		@Override
		public MyData createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new MyData(source);
		}

		@Override
		public MyData[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MyData[size];
		}

	};
}
