package ru.d51x.kaierutils.Settings;

import java.util.List;
import android.preference.PreferenceActivity;

import ru.d51x.kaierutils.R;

public class SettingsActivity extends PreferenceActivity {

	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.pref_heads, target);
	}

	@Override
	protected boolean isValidFragment(String fragmentName) {
		return true;
	}
}
