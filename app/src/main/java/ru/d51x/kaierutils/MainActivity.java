package ru.d51x.kaierutils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {

	private Button btnSoundSettings;


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.main_activity);
		Log.d ("SettingsActivity", "onCreate");
		startService(new Intent(this, TWUtilService.class));
		Toast.makeText (this, "Service started", 0);

		btnSoundSettings = (Button) findViewById (R.id.id_button_sound_Settings);


		this.btnSoundSettings.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) // клик на кнопку
			{

				try {
					Intent it = new Intent();
					//Intent it = new Intent(this, SoundSettingsPreferenceActivity.class);
					it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.SoundSettingsPreferenceActivity");
					it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(it);
				} catch (Exception e) {
				}

			}
		});




	}
}


