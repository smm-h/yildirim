package ir.smmh.fy.code;

import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.ui.Tab;

public class CodeTab implements Tab {

    public final Code code;

    public CodeTab(final Code code) {
        this.code = code;
    }

    @Override
    public String getTitle() {
        return code.getFilename() + "." + code.getExt() + (code.isModified() ? "*" : "");
    }

    @Override
    public View getView() {
        return new CodeTabView(Util.getMainActivity(), this);
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_baseline_code_24;
    }
}
