package course.labs.dailyselfie;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DailySelfieActivity extends ListActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private SelfieViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ListView selfieListView = getListView();
        mAdapter = new SelfieViewAdapter(getApplicationContext());
        setListAdapter(mAdapter);
        mAdapter.loadAllSelfies();

        selfieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(DailySelfieActivity.this, ImageViewActivity.class);
                SelfieRecord record = (SelfieRecord) mAdapter.getItem((int) id);
                Bitmap bitmap = record.getBitmap();
                intent.putExtra(ImageViewActivity.BITMAP_IMAGE, bitmap);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroy() {
        mAdapter.saveAllSelfies();
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daily_selfie, menu);
        menu.findItem(R.id.action_camera).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_camera) {
            dispatchTakePictureIntent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format
                    (Calendar.getInstance().getTime());

            SelfieRecord selfieRecord = new SelfieRecord(timestamp, imageBitmap);
            mAdapter.add(selfieRecord);
        }
    }
}
