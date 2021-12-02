package ir.smmh.fy;

import android.os.Bundle;

import ir.smmh.fy.home.HomeTab;
import ir.smmh.fy.ui.TabbedActivity;

public class MainActivity extends TabbedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setMainActivity(this); // let everyone be able to find me
        addTab(new HomeTab());
    }
}
