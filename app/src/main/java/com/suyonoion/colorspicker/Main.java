package com.suyonoion.colorspicker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * Created by Suyono on 1/12/2016.
 * Copyright (c) 2016 by Suyono (ion).
 * All rights reserved.
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and decompilation.
 */

@SuppressWarnings("ALL")
public class Main extends Activity {

    TextView textView1, textView2, textView3, textView4, margaritov, suyonoion;
    Button btn_set;
    int warna1, warna2, warna3, warna4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setResource("main","layout"));

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        warna1 = sharedPreferences.getInt("color1",0xff000000);
        warna2 = sharedPreferences.getInt("color2",0xff000000);
        warna3 = sharedPreferences.getInt("color3",0xff000000);
        warna4 = sharedPreferences.getInt("color4",0xff000000);

        btn_set = (Button) findViewById(setResource("btn_set","id"));
        textView1 = (TextView) findViewById(setResource("txt1","id"));
        textView2 = (TextView) findViewById(setResource("txt2","id"));
        textView3 = (TextView) findViewById(setResource("txt3","id"));
        textView4 = (TextView) findViewById(setResource("txt4","id"));
        margaritov = (TextView) findViewById(setResource("margaritov","id"));
        suyonoion = (TextView) findViewById(setResource("suyonoion","id"));


        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Settings_ah.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                v.getContext().startActivity(intent);
                try {
                    Object service = getBaseContext().getSystemService("statusbar");
                    Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
                    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                    if (currentapiVersion <= 16) {
                        Method collapse = statusbarManager.getMethod("collapse");
                        collapse.invoke(service);
                    } else {
                        Method collapse2 = statusbarManager.getMethod("collapsePanels");
                        collapse2.invoke(service);
                    }

                } catch (Exception ex) { }
            }
        });

        textView1.setTextColor(warna1);
        textView2.setTextColor(warna2);
        textView3.setTextColor(warna3);
        textView4.setTextColor(warna4);

        margaritov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToURL("https://github.com/attenzione/android-ColorPickerPreference");
            }
        });
        suyonoion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToURL("https://github.com/suyonoion/ResetColorsPicker");
                goToURL("https://facebook.com/suyono.ion");
            }
        });

    }
    private void goToURL (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }


    public int setResource(String name, String Type)
    {
        return getBaseContext().getResources().getIdentifier(name, Type, getBaseContext().getPackageName());
    }

    @Override
    protected void onResume(){
        super.onResume();
        onCreate(null);
    }
}
