package com.suyonoion.colorspicker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

public class ResetPrefKeSemula extends DialogPreference
{
	protected Context context;

	public ResetPrefKeSemula(Context context, AttributeSet attrs)
	{
		super(context, attrs);
        this.context = context;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) 
	{
		super.onClick(dialog, which);
        // Jika tombol 'positif' telah ditekan
		if(which == DialogInterface.BUTTON_POSITIVE)
		{
			SharedPreferences.Editor preferencesEditor = PreferenceManager.getDefaultSharedPreferences(this.context).edit();
            // Hapus semua nilai preferensi yg disimpan.
			preferencesEditor.clear();
            // Baca nilai-nilai default dan menetapkannya sebagai nilai-nilai saat ini.
			PreferenceManager.setDefaultValues(context, setResource("setting_colors","xml"), true);
			//Commit semua perubahan
			preferencesEditor.commit();

			//Call this method to trigger the execution of the setOnPreferenceChangeListener() method at the PrefsActivity
            // Panggil metode ini untuk mengeksekusi metod setOnPreferenceChangeListener () di PrefsActivity
			getOnPreferenceChangeListener().onPreferenceChange(this, true);
		}
	}

    public int setResource(String name, String Type)
    {
        return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
    }
}