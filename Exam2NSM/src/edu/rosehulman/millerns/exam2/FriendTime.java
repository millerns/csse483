package edu.rosehulman.millerns.exam2;

public class FriendTime {
	private String mName;
	private int mHour;
	private int mMinute;

	public FriendTime(String name, int hour, int minute) {
		mName = name;
		mHour = hour;
		mMinute = minute;
	}

	public FriendTime(String s) {
		mName = s.substring(0, s.indexOf("&"));
		mHour = Integer.parseInt(s.substring(s.indexOf("&")+1, s.indexOf(":")));
		mMinute = Integer.parseInt(s.substring(s.indexOf(":")+1));
	}

	@Override
	public String toString() {
		return mName + " - " + mHour + ":" + mMinute;
	}

	public String getName() {
		return mName;
	}

	public String getTime() {
		String hour = "" + mHour;
		if (mHour < 10) {
			hour = "0" + hour;
		}
		String minute = "" + mMinute;
		if (mMinute < 10) {
			minute = "0" + minute;
		}
		return hour + ":" + minute;
	}

}
