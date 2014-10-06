package com.example.apple;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable{

	private String title;
	private String date;
	private String link;
	private String description;
	
	public News(String title, String date, String description, String link) {
		super();
		this.title = title;
		this.date = date;
		this.description = description;
		this.link = link;
	}
	public News() {
	}
	public News(Parcel source) {
		title = source.readString();
		date = source.readString();
		description = source.readString();
		link = source.readString();
	}
	public String getDate() {
		return date;
	}
	public void setDate(String string) {
		this.date = string;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
		@Override
		public News createFromParcel(Parcel source) {
			return new News(source);
		}

		@Override
		public News[] newArray(int size) {
			return new News[size];
		}
	};
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(date);
		dest.writeString(description);
		dest.writeString(link);
	}
}
