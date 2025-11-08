package ru.d51x.kaierutils.Settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import ru.d51x.kaierutils.R;

public class SettingsActivity extends AppCompatActivity implements
		PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.settings_container, new SettingsFragment())
				.commit();
	}

	@Override
	public boolean onPreferenceStartFragment(@NonNull PreferenceFragmentCompat caller, Preference pref) {
		final Bundle args = pref.getExtras();
		final Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(
				getClassLoader(),
				pref.getFragment());
		fragment.setArguments(args);
		fragment.setTargetFragment(caller, 0);
		// Replace the existing Fragment with the new Fragment.
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.settings_container, fragment)
				.addToBackStack(null)
				.commit();
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		setTitle(getString(R.string.header_settings_title));
	}
}
