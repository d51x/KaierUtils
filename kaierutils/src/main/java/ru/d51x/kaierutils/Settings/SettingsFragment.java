package ru.d51x.kaierutils.Settings;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;
import ru.d51x.kaierutils.R;

public class SettingsFragment extends PreferenceFragmentCompat {
	@Override
	public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
		setPreferencesFromResource (R.xml.preferences, rootKey);

	}

	@Override
	public void onResume() {
		super.onResume();
		if (getActivity() != null) {
			getActivity().setTitle(getString(R.string.header_settings_title));
		}
	}
}
