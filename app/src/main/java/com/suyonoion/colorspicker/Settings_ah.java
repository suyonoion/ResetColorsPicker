package com.suyonoion.colorspicker;

/**
 * Created by Suyono on 1/11/2016.
 * Copyright (c) 2016 by Suyono (ion).
 * All rights reserved.
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and decompilation.
 */

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import net.margaritov.preference.colorpicker.ColorPickerPreference;

public class Settings_ah extends PreferenceActivity {

    private Preference resetDialogPreference;
    private Intent startIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(setResource("setting_colors","xml"));
        ((ColorPickerPreference) findPreference("color2")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
                return true;
            }

        });
        ((ColorPickerPreference) findPreference("color2")).setAlphaSliderEnabled(true);

        PreferenceManager.setDefaultValues(this, setResource("setting_colors","xml"), false);
        this.resetDialogPreference = getPreferenceScreen().findPreference("resetDialog");
        this.startIntent = getIntent();
        this.resetDialogPreference.setOnPreferenceChangeListener(new OnPreferenceChangeListener()
        {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {
                overridePendingTransition(0, 0);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(startIntent);
                return false;
            }
        });
    }

    public int setResource(String name, String Type)
    {
        return getBaseContext().getResources().getIdentifier(name, Type, getBaseContext().getPackageName());
    }

}
