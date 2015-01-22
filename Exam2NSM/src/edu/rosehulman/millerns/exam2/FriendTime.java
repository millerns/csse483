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

	@Override
	public String toString() {
		return mName + " - " + mHour + ":" + mMinute;
	}

	public String getName() {
		return mName;
	}

	public String getTime() {
		return mHour + ":" + mMinute;
	}

}
