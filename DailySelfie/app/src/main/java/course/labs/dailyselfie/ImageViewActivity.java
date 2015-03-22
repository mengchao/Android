package course.labs.dailyselfie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {
    static final String BITMAP_IMAGE = "BitmapImage";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Get the Intent used to start this Activity
		Intent intent = getIntent();

		ImageView imageView = new ImageView(getApplicationContext());
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra(BITMAP_IMAGE);
		imageView.setImageBitmap(bitmap);

		setContentView(imageView);
	}
}