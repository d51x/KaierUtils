package ru.d51x.kaierutils;

import java.util.List;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {

	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.pref_heads, target);
	}

}
