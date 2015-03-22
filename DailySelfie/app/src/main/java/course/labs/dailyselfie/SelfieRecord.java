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

	public void setName(String name) {
		this.mName = name;
	}

	public Bitmap getBitmap() {
		return mBitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.mBitmap = bitmap;
	}

	@Override
	public String toString(){
		return mName;
		
	}
}
