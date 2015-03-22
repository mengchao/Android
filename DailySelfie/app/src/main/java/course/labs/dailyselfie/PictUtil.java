package course.labs.dailyselfie;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.TreeMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Log;

class PictUtil {

    private static String TAG = "PictUtil";

    public static File getSavePath() {
        File path;
        if (hasSDCard()) {
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        } else {
            path = Environment.getDataDirectory();
        }
        return path;
    }

    public static Bitmap loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) { return null; }
        Bitmap tmp = BitmapFactory.decodeFile(filename);
        return tmp;
    }

    public static Map<String, Bitmap> getAllPics() {
        File dir = getSavePath();
        Map<String, Bitmap> allPics = new TreeMap<String, Bitmap>();

        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                allPics.put(child.getName(), loadFromFile(child.getAbsolutePath()));
            }
        }

        return allPics;
    }

    public static void saveToFile(String filename, Bitmap bmp) {
        try {
            File file = new File(getSavePath().getAbsolutePath(), filename);
            if (file.exists()) {
                return;
            }
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch(Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

}