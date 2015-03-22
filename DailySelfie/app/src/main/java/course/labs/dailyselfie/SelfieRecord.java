package course.labs.dailyselfie;

import android.graphics.Bitmap;

public class SelfieRecord {
	private String mName;
	private Bitmap mBitmap;

	public SelfieRecord(String name, Bitmap bitmap) {
		this.mName = name;
		this.mBitmap = bitmap;
	}

	public String getName() {
		return mName;
	}

	public Bitmap getBitmap() {
		return mBitmap;
	}

	@Override
	public String toString(){
		return mName;
	}
}
