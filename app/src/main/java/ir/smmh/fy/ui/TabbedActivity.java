package ir.smmh.fy.ui;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.code.CodeTab;
import ir.smmh.fy.code.PlainText;

@SuppressWarnings("SuspiciousNameCombination")
public class TabbedActivity extends Activity {

    // have persistent unique IDs for elements
    private static final int ID_LAYOUT = 100;
    private static final int ID_TAB_LAYOUT = 101;
    private static final int ID_FRAME = 102;
    // THANKS_TO: https://stackoverflow.com/a/42457028/

    // settings
    private static final int SHADOW_PRE_DIPPED = 8;
    private static final int TOP_SPACE = Util.dipToPixel(SHADOW_PRE_DIPPED) + 1;
    private static final int TAB_BAR_HEIGHT = Util.dipToPixel(40);
    private static final boolean CURVED_OUTLINE = true;
    private static final boolean DRAW_OUTLINE_FOR_INACTIVE_TABS = false;
    private static final float TAB_SLANT_FACTOR = 1;
    private static final int BUTTON_SIZE = Util.dipToPixel(20);

    private final Paint tabFillPaint = new Paint();
    private final Paint tabStrokePaint = new Paint();

    private int index = -1;
    private LinearLayout tabs;
    private FrameLayout frame;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get screen size
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;

        // declare and create the elements
        final LinearLayout layout = new LinearLayout(this);
        tabs = new LinearLayout(this);
        frame = new FrameLayout(this);

        // set their IDs
        layout.setId(ID_LAYOUT);
        tabs.setId(ID_TAB_LAYOUT);
        frame.setId(ID_FRAME);

        // set their styles and colors
        layout.setBackgroundColor(Util.getColor(this, R.color.active_back));
        tabs.setBackgroundColor(Util.getColor(this, R.color.passive_back));
        frame.setBackgroundColor(Util.getColor(this, R.color.passive_back));

        tabFillPaint.setStyle(Paint.Style.FILL);
        tabFillPaint.setColor(Util.getColor(this, R.color.active_back));
        tabStrokePaint.setStyle(Paint.Style.STROKE);
        tabStrokePaint.setColor(Util.colorAddAlpha(Color.GRAY, 127));
        tabStrokePaint.setStrokeWidth(1);

        // set their layout parameters
        layout.setLayoutParams(new LinearLayout.LayoutParams(w_screen, h_screen));
        tabs.setLayoutParams(new ViewGroup.LayoutParams(w_screen, TAB_BAR_HEIGHT + TOP_SPACE));
        frame.setLayoutParams(new FrameLayout.LayoutParams(w_screen, h_screen - TAB_BAR_HEIGHT - Util.getStatusBarHeight()));

        // set orientation, gravity and alignment
        layout.setOrientation(LinearLayout.VERTICAL);
        tabs.setOrientation(LinearLayout.HORIZONTAL);
        tabs.setGravity(Gravity.CENTER_VERTICAL);

        // add the 'add tab' button
        ImageButton addTabButton = Util.makeBitmapButton(this, Util.Drawable_to_Bitmap(ContextCompat.getDrawable(this, R.drawable.ic_baseline_add_24), BUTTON_SIZE, BUTTON_SIZE), BUTTON_SIZE, BUTTON_SIZE);
        addTabButton.setOnClickListener(v -> addTab(new CodeTab(new PlainText(""))));
        addTabButton.setLayoutParams(new ViewGroup.LayoutParams(TAB_BAR_HEIGHT, TAB_BAR_HEIGHT));
        tabs.addView(addTabButton);

        // make tabs horizontally scrollable
        HorizontalScrollView hsv = new HorizontalScrollView(this);
        hsv.setLayoutParams(tabs.getLayoutParams());
        hsv.addView(tabs);

        // make frame vertically scrollable
        ScrollView sv = new ScrollView(this);
        sv.setLayoutParams(frame.getLayoutParams());
        sv.addView(frame);

        // THANKS_TO: https://stackoverflow.com/a/12258874/
        hsv.setHorizontalScrollBarEnabled(false);

        // THANKS_TO: https://stackoverflow.com/a/19448610/
        hsv.setFillViewport(true);
        hsv.setMeasureAllChildren(false);

        layout.addView(hsv);
        layout.addView(sv);

        setContentView(layout);
    }

    public void setCurrentTabIndex(int i) {
        if (index != i) {

            // refresh the previous tab so it can clear its head
            final TabHead prev = getCurrentTab();
            if (prev != null)
                prev.invalidate();

            // changing the index
            index = i;

            // refresh the current tab so it can draw its head
            final TabHead tab = getCurrentTab();
            if (tab != null)
                tab.invalidate();

            // TODO fetch tab bottom bar buttons and show them

            // refresh tabs
            tabs.invalidate();

            // place at most one view in frame
            frame.removeAllViews();
            if (tab != null)
                frame.addView(tab.src.getView());

            // refresh frame
            frame.invalidate();
        }
    }

    public int getCurrentTabIndex() {
        return index;
    }

    public void setCurrentTab(TabHead tab) {
        setCurrentTabIndex(tabs.indexOfChild(tab));
    }

    public TabHead getCurrentTab() {
        if (index == -1) {
            return null;
        } else {
            View v = tabs.getChildAt(index);
            if (v instanceof TabHead) {
                return (TabHead) v;
            } else {
                return null;
            }
        }
    }

    public void addTab(Tab tab) {
        tabs.addView(new TabHead(tab), index + 1);
        setCurrentTabIndex(index + 1);
    }

    private void closeTab(TabHead tab) {
        int i = tabs.indexOfChild(tab);
        if (i != -1) {
            if (i == 0) {
                if (tabs.getChildCount() > 2) {
                    setCurrentTabIndex(1);
                } else {
                    setCurrentTabIndex(-1);
                }
            } else {
                setCurrentTabIndex(i - 1);
            }
            tabs.removeView(tab);
        }
        if (index > i) {
            index--;
        }
    }

    private Bitmap closeButtonBitmap = null;

    // private final ViewGroup.LayoutParams closeButtonParams = new ViewGroup.LayoutParams(Util.dipToPixel(16), Util.dipToPixel(16));

    private class TabHead extends LinearLayout {

        final Tab src;

        public TabHead(Tab tab) {
            super(TabbedActivity.this);

            if (closeButtonBitmap == null) {
                closeButtonBitmap = Util.Drawable_to_Bitmap(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_close_24), BUTTON_SIZE, BUTTON_SIZE);
            }

            src = tab;

            setWillNotDraw(false);
            setOrientation(VERTICAL);
            setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, TAB_BAR_HEIGHT + TOP_SPACE));
            setOnClickListener(v -> setCurrentTab(this));

            Space s = new Space(getContext());
            s.setMinimumHeight(TOP_SPACE);
            addView(s);

            LinearLayout l = new LinearLayout(getContext());
            l.setOrientation(HORIZONTAL);
            l.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, TAB_BAR_HEIGHT));
            l.setGravity(Gravity.CENTER_VERTICAL);
            setGravity(Gravity.CENTER_VERTICAL);

            int slantSpace = Math.round(TAB_BAR_HEIGHT * TAB_SLANT_FACTOR);

            Space openingSlantSpace = new Space(getContext());
            openingSlantSpace.setMinimumWidth(slantSpace);
            l.addView(openingSlantSpace);

            ImageView iconView = new ImageView(getContext());
            iconView.setImageDrawable(ContextCompat.getDrawable(getContext(), src.getIconResource()));
            l.addView(iconView);

            TextView titleView = new TextView(getContext());
            titleView.setText(src.getTitle());
            titleView.setPadding(slantSpace / 2, 0, slantSpace / 2, 0);
            l.addView(titleView);

            ImageButton closeButton = Util.makeBitmapButton(getContext(), closeButtonBitmap, BUTTON_SIZE, BUTTON_SIZE);
            // closeButton.setLayoutParams(closeButtonParams);
            closeButton.setOnClickListener(v -> closeTab(this));
            l.addView(closeButton);

            Space closingSlantSpace = new Space(getContext());
            closingSlantSpace.setMinimumWidth(slantSpace);
            l.addView(closingSlantSpace);

            addView(l);

            Util.paintEnableShadow(tabFillPaint, 40, TOP_SPACE - 1, 0, 0);
            Util.viewDisableHardwareAcceleration(this);
        }

        boolean isCurrentTab() {
            return getCurrentTab() == this;
        }

        private final Path path = new Path();

        @Override
        protected void onDraw(final Canvas canvas) {
            super.onDraw(canvas);

            if (isCurrentTab() || DRAW_OUTLINE_FOR_INACTIVE_TABS) {
                int w = getWidth();
                int h = getHeight();
                float s = (h - TOP_SPACE) * TAB_SLANT_FACTOR;
                path.reset();

                // THANKS_TO: https://stackoverflow.com/a/11186887/
                path.moveTo(0, h);

                if (CURVED_OUTLINE) {
                    path.cubicTo(s / 2f, h, s / 2f, TOP_SPACE, s, TOP_SPACE);
                    path.lineTo(w - s, TOP_SPACE);
                    path.cubicTo(w - s / 2f, TOP_SPACE, w - s / 2f, h, w, h);
                } else {
                    path.lineTo(s, TOP_SPACE);
                    path.lineTo(w - s, TOP_SPACE);
                    path.lineTo(w, h);
                }

                canvas.drawPath(path, isCurrentTab() ? tabFillPaint : tabStrokePaint);
            }
        }
    }
}