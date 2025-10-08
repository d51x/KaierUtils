package ru.d51x.kaierutils.Settings;

import android.preference.PreferenceActivity;

import java.util.List;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsActivity extends PreferenceActivity {

	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.pref_heads, target);
	}

	@Override
	protected boolean isValidFragment(String fragmentName) {
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (App.GS.ui.showFloatingOnMinimize) {
			// hide floating panel
			App.floatingWindow.dismiss();
		}
	}
}
