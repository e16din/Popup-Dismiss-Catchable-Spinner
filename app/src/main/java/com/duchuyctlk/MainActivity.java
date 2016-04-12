package com.duchuyctlk;

import com.duchuyctlk.popupdismisscatchablespinner.BuildConfig;
import com.duchuyctlk.popupdismisscatchablespinner.R;
import com.duchuyctlk.widget.PopupDismissCatchableSpinner;
import com.duchuyctlk.widget.PopupDismissCatchableSpinner.PopupDismissListener;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity implements PopupDismissListener {

    private TextView tvDismissCount;
    private TextView tvOpenCount;
    private int mDismissCount;
    private int mOpenCount;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock keyguardLock = km.newKeyguardLock(MainActivity.class.getSimpleName());
            keyguardLock.disableKeyguard();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }

        setContentView(R.layout.activity_main);

        tvDismissCount = (TextView) findViewById(R.id.tv_count_dismiss);
        tvOpenCount = (TextView) findViewById(R.id.tv_count_open);

        PopupDismissCatchableSpinner spinnerDropdown = (PopupDismissCatchableSpinner)
                findViewById(R.id.spinner_dropdown);
        spinnerDropdown.setOnPopupDismissListener(this);

        PopupDismissCatchableSpinner spinnerDialog = (PopupDismissCatchableSpinner)
                findViewById(R.id.spinner_dialog);
        spinnerDialog.setOnPopupDismissListener(this);

        mDismissCount = 0;
        mOpenCount = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mDismissCount++;
        tvDismissCount.setText(String.valueOf(mDismissCount));
    }

    @Override
    public void onShow() {
        mOpenCount++;
        tvOpenCount.setText(String.valueOf(mOpenCount));
    }
}
