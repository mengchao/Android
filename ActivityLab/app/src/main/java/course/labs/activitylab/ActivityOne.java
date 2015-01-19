package course.labs.activitylab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityOne extends ActivityCommon {

    static {
        TAG = "Lab-ActivityOne";
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        onCreate(savedInstanceState, R.layout.activity_one);

		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo);
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Launch Activity Two
				Intent intent = new Intent(getApplicationContext(), ActivityTwo.class);
                startActivity(intent);
			}
		});

	}

}
