package ir.smmh.fy.code;

import android.content.Context;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import ir.smmh.fy.R;

public class CodeTabView extends TabView {

    private final CodeTab src;

    public CodeTabView(Context context, CodeTab src) {

        super(context);

        this.src = src;

        EditText e = new EditText(getContext());

        e.setText(src.code.getContents());

        e.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        e.setTextSize(12);
        e.setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO);
        e.setHint(R.string.sample_code_here);
        e.setGravity(Gravity.TOP | Gravity.START);

        // THANKS_TO: https://stackoverflow.com/a/36014316/
        e.setSingleLine(false);
        e.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
        e.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        e.setVerticalScrollBarEnabled(true);
        e.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        // THANKS_TO: https://stackoverflow.com/a/6078898/
        e.setTypeface(Typeface.MONOSPACE);

        addView(e);
    }

}