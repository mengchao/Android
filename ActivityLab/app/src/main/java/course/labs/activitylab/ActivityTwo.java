package course.labs.activitylab;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityTwo extends ActivityCommon {

    {
        TAG = "Lab-ActivityTwo";
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		onCreate(savedInstanceState, R.layout.activity_two);
		
		Button closeButton = (Button) findViewById(R.id.bClose); 
		closeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// This function closes Activity Two
                finish();
			
			}
		});
		
	}

}
