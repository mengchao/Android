package course.labs.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;


public class ModernArtUIActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View rect1 = findViewById(R.id.rect1);
        final View rect2 = findViewById(R.id.rect2);
        final View rect3 = findViewById(R.id.rect3);
        final View rect4 = findViewById(R.id.rect4);
        final View rect5 = findViewById(R.id.rect5);

        final int steps = 101;
        final Resources resources = getResources();

        final int colorsForRect1[] = TransitionalColor.generateTransitionalColor
          (resources.getColor(R.color.light_blue), resources.getColor(R.color.light_yellow), steps);

        final int colorsForRect2[] = TransitionalColor.generateTransitionalColor
          (resources.getColor(R.color.pink), resources.getColor(R.color.Khaki), steps);

        final int colorsForRect3[] = TransitionalColor.generateTransitionalColor
          (resources.getColor(R.color.red), resources.getColor(R.color.yellow), steps);

        final int colorsForRect4[] = TransitionalColor.generateTransitionalColor
          (resources.getColor(R.color.white), resources.getColor(R.color.white), steps);

        final int colorsForRect5[] = TransitionalColor.generateTransitionalColor
          (resources.getColor(R.color.dark_blue), resources.getColor(R.color.light_green), steps);

        final SeekBar mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                rect1.setBackgroundColor(colorsForRect1[progress]);
                rect2.setBackgroundColor(colorsForRect2[progress]);
                rect3.setBackgroundColor(colorsForRect3[progress]);
                rect4.setBackgroundColor(colorsForRect4[progress]);
                rect5.setBackgroundColor(colorsForRect5[progress]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_more_information) {
            (new MoreInfoDialogFragment()).show
                    (getFragmentManager(), "More Information");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Class that creates the AlertDialog
    public class MoreInfoDialogFragment extends DialogFragment {

        static private final String URL = "http://www.moma.org/";
        static private final String message = "Inspired by the works of Modern Art masters " +
                "such as Piet Mondrian and Ben Nicholson.\nClick below to learn more!";

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage(message)

                            // User cannot dismiss dialog by hitting back button
                    .setCancelable(false)

                            // Set up Yes Button
                    .setPositiveButton("Visit MOMA",
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, int id) {
                                    Uri uri = Uri.parse(URL);
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
                                }
                            })

                            // Set up No Button
                    .setNegativeButton("Not Now",
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, int id) {
                                }
                            }).create();
        }
    }
}
