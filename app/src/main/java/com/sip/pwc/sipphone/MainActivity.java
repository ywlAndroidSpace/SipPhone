package com.sip.pwc.sipphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.csipsimple.api.SipManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent prefsIntent = new Intent(SipManager.ACTION_UI_PREFS_FAST);
        prefsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(prefsIntent);
    }
}
