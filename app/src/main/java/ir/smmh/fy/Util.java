package ir.smmh.fy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import ir.smmh.fy.net.Network;

public class Util {

    public static final Paint HIGHLIGHTER = new Paint();

    static {
        HIGHLIGHTER.setColor(colorAddAlpha(Color.YELLOW, 127));
    }

    public static final float GOLDEN_RATIO = 1.618034f;

    private static final float[] INTERVALS = {1, 4};
    public static final PathEffect DASH = new DashPathEffect(INTERVALS, 0);

    public static <T> Iterable<T> getEmptyIterable() {
        return Util::getEmptyIterator;
    }

    public static <T> Iterator<T> getEmptyIterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    // THANKS_TO: https://stackoverflow.com/questions/9723106/get-activity-instance
    private static WeakReference<MainActivity> wr_ma;

    public static void setMainActivity(MainActivity mainActivity) {
        wr_ma = new WeakReference<>(mainActivity);
    }

    public static MainActivity getMainActivity() {
        return wr_ma.get();
    }

    public static final float DENSITY = Resources.getSystem().getDisplayMetrics().density;

    public static int dipToPixel(float dip) {
        return Math.round(dip * DENSITY);
    }

    public static int dipToPixel(int dip) {
        // THANKS_TO: https://stackoverflow.com/questions/2238883/what-is-the-correct-way-to-specify-dimensions-in-dip-from-java-code#comment11885008_2683771
        return Math.round(dip * DENSITY);
    }

    public static int greatestNearDivisor(int totalWidth, int itemWidth) {

        int divisor = 1;

        for (float tolerance = 0.1f; tolerance < 1f; tolerance += 0.1f) {

            int start = Math.round(itemWidth * (1 - tolerance));
            int end = Math.round(itemWidth * (1 + tolerance));

            for (int i = end; i >= start; i--) {
                if (totalWidth % i == 0) {
                    int d = totalWidth / i;
                    if (divisor < d) {
                        divisor = d;
                    }
                }
            }
            if (divisor == 1) {
                tolerance += 0.1f;
            } else {
                return divisor;
            }
        }
        return 1;
    }

    public static void grayscale(Drawable d) {
        d.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
    }

    public static void unGrayscale(Drawable d) {
        d.clearColorFilter();
    }

    public static void toast(Context context, java.lang.String text) {
        Toast.makeText(context, text, text.length() > 25 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    public static boolean storageExists(Context context, java.lang.String filename) {
        return storageGet(context, filename).exists();
    }

    public static File storageGet(Context context, java.lang.String filename) {
        // Resources.getSystem().openRawResource(R.raw.m);
        return new File(context.getFilesDir(), filename);
    }

    public static void storageWrite(Context context, java.lang.String filename, java.lang.String contents) throws IOException {
        context.openFileOutput(filename, Context.MODE_PRIVATE).write(contents.getBytes(StandardCharsets.UTF_8));
    }

    public static java.lang.String storageRead(Context context, java.lang.String filename) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(filename), StandardCharsets.UTF_8));
        java.lang.String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append('\n');
        }
        return stringBuilder.toString();
    }

    public static List<java.lang.String> storageReadLines(Context context, java.lang.String filename) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(filename), StandardCharsets.UTF_8));
        List<java.lang.String> list = new LinkedList<>();
        java.lang.String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    public static java.lang.String readWorld() {
        return "*************************\n*  x  *   *            x*\n* *** * * * ******* *** " + "*\n*   * *x*   *     *** * *\n* * * ***** * * *       *\n* * *   *x* * * ***** ***\n* * * *   * * *         *\n* * * ***** * **** **** *\n* *                  x*x*\n* *********** ********* *\n*  x* *   *   *         *\n***** * * * *** *********\n*x      *   *          x*\n*************************\n";
    }

    public static java.lang.String readFromFile(java.lang.String filename) {
        // https://stackoverflow.com/a/12421888/9115712
        //Find the directory for the SD Card using the API
        //*Don't* hardcode "/sdcard"
        File sdcard = Environment.getExternalStorageDirectory();

        //Get the text file
        File file = new File(sdcard, filename);

        //Read text from file
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            java.lang.String line;

            while ((line = br.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
        }
        return builder.toString();
    }

    public static Random random = new Random();

    public static float random(final float x) {
        return random.nextFloat() * x;
    }

    public static int randomInteger(final int i) {
        return (int) random(i);
    }

    public static int randomColor() {
        return randomInteger(16777215);
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = Resources.getSystem().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int clamp(final int x, final int min, final int max) {
        return Math.max(min, Math.min(max, x));
    }

    public static float clamp(final float x, final float min, final float max) {
        return Math.max(min, Math.min(max, x));
    }

    public static int colorAddAlpha(final int rgb, final int a) {
        return Color.argb(a, Color.red(rgb), Color.green(rgb), Color.blue(rgb));
    }

    public static void paintEnableShadow(Paint paint, int alpha, int radius, int offsetX, int offsetY) {
        paint.setShadowLayer(radius, offsetX, offsetY, Util.colorAddAlpha(Color.BLACK, alpha));
    }

    public static void viewDisableHardwareAcceleration(android.view.View view) {
        view.setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null);
    }

    public static void viewEnableHardwareAcceleration(android.view.View view) {
        view.setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null);
    }

    public static int getColor(final Context context, final int color) {
        return ContextCompat.getColor(context, color);
    }

    public static class String {
        private String() {
        }

        public static boolean isNumeric(java.lang.String string) {
            if (string.isEmpty())
                return false;
            for (char c : string.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }

        public static boolean isAlphabetic(java.lang.String string) {
            if (string.isEmpty())
                return false;
            for (char c : string.toCharArray()) {
                if (!Character.isAlphabetic(c)) {
                    return false;
                }
            }
            return true;
        }

        public static boolean isAlphanumeric(java.lang.String string) {
            if (string.isEmpty())
                return false;
            for (char c : string.toCharArray()) {
                if (!(Character.isDigit(c) || Character.isAlphabetic(c))) {
                    return false;
                }
            }
            return true;
        }
    }

    // THANKS_TO: http://www.java2s.com/example/android/android.graphics/draw-horizontal-and-vertical-aligned-text.html

    public enum TextVerticalAlignment {
        TOP, MIDDLE, BOTTOM, BASELINE
    }

    public static float Paint_getLineHeight(Paint paint) {
        Paint.FontMetrics m = paint.getFontMetrics();
        return m.bottom - m.top;
    }

    public static float Paint_getTextWidth(Paint paint, java.lang.String text) {
        return paint.measureText(text);
    }

    public static void drawAlignedText(Canvas canvas, float x, float y, java.lang.String s, Paint p, Paint.Align horizontalAlignment, TextVerticalAlignment verticalAlignment, boolean useLineHeight) {

        p.setTextAlign(horizontalAlignment);

        Rect r = new Rect();
        p.getTextBounds(s, 0, s.length(), r);

        float h = useLineHeight ? Paint_getLineHeight(p) : r.height();

        switch (verticalAlignment) {
            case TOP:
                y -= r.top;
                break;
            case MIDDLE:
                y -= r.top + h / 2f;
                break;
            case BOTTOM:
                y -= r.top + h;
                break;
            case BASELINE:
                break;
        }

        canvas.drawText(s, x, y, p);
    }

    public static class View {
        public static NestedScrollView wrapInScrollView(Context context, android.view.View view) {
            NestedScrollView scrollView = new NestedScrollView(context);
            scrollView.addView(view);
            return scrollView;
        }
    }

    public static <T> Iterable<T> over(Iterator<T> iterator) {
        return () -> iterator;
    }

    public static class JSONArray {
        public static Iterator<JSONObject> iterate(org.json.JSONArray array) {
            return new Iterator<JSONObject>() {

                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < array.length();
                }

                @Override
                public JSONObject next() {
                    try {
                        return array.getJSONObject(i++);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            };
        }
    }

    public static Bitmap Drawable_to_Bitmap(Drawable drawable, int w, int h) {

        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                return bitmap;
            }
        }

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static ImageButton makeBitmapButton(final Context context, final Bitmap bitmap, final int w, final int h) {
        ImageButton b = new ImageButton(context);
        b.setImageBitmap(bitmap);
        b.setBackgroundColor(Util.getColor(context, R.color.transparent));
        b.setLayoutParams(new ViewGroup.LayoutParams(w, h));
        return b;
    }

    public static TextView textViewOf(Context context, java.lang.String text) {
        final TextView tv = new TextView(context);
        tv.setText(text);
        return tv;
    }

    public static Locale LOCALE = Locale.US;

    public static TextView textViewOf(Context context, Number number) {
        return textViewOf(context, java.lang.String.format(LOCALE, "%.2f", number.doubleValue()));
    }

    private static Handler handler = null;

    public static Handler getHandler() {
        if (handler == null) {
            handler = new Handler();
        }
        return handler;
    }

    private static Network singleton = null;

    public static Network getNetwork() {
        if (singleton == null) {
            singleton = new Network(Util.getMainActivity());
        }
        return singleton;
    }

    public interface OnExceptionCaught {
        void onExceptionCaught(Exception e);
    }

    public static void later(long delayMillis, Runnable lateStuff) {
        getHandler().postDelayed(lateStuff, delayMillis);
    }

    public static void phew(Runnable heavyStuff) {
        new Thread(heavyStuff).start();
    }
}



