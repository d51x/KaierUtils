package ru.d51x.kaierutils.Toasts;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

/**
 * Created by Dmitriy on 07.03.2015.
 */
public class MusicToast {
	private Context context;
	private Toast mToast;
	private RelativeLayout mLayout;
	private TextView tvTrackTitle;
	private TextView tvArtistAlbum;
	private ImageView ivAlbumArt;
	private String TrackTitle;
	private String AlbumArtist;

	public MusicToast (Context context) {
		this.context = context;
		mLayout = (RelativeLayout) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.music_toast, null);
		tvTrackTitle = (TextView) this.mLayout.findViewById (R.id.tvTrackTitle);
		tvArtistAlbum = (TextView) this.mLayout.findViewById(R.id.tvArtistAlbum);
		ivAlbumArt = (ImageView) this.mLayout.findViewById (R.id.ivAlbumArt);

	}

	public void setTrackTitle(String trackTitle) {
		TrackTitle =  trackTitle;
	}

	public void setArtistAlbum(String albumArtist) {
		AlbumArtist = albumArtist;
	}

	public void setAlbumArt(Bitmap albumArt) {
		if (albumArt != null) {
			ivAlbumArt.setImageBitmap(albumArt);
		} else {
			ivAlbumArt.setImageResource ( R.drawable.toast_music );
		}
	}

	public void showToast() {
        cancel();

		tvTrackTitle.setTextSize ( App.GS.popupWindowOption.musicToastLine1TextSize );
		tvArtistAlbum.setTextSize ( App.GS.popupWindowOption.musicToastLine2TextSize );

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams( App.GS.popupWindowOption.musicToastPictureWidth, App.GS.popupWindowOption.musicToastPictureHeight );
		ivAlbumArt.setLayoutParams(layoutParams);

/*
ViewGroup.LayoutParams iv_params_b = image_view.getLayoutParams();
iv_params_b.height = thumb_size;
iv_params_b.width = thumb_size;
image_view.setLayoutParams(iv_params_b);

android.view.ViewGroup.LayoutParams layoutParams = myImageView.getLayoutParams();
layoutParams.width = 30;
layoutParams.height = 30;
myImageView.setLayoutParams(layoutParams);
*/
		tvTrackTitle.setText (TrackTitle);
		tvArtistAlbum.setText ( AlbumArtist );

		mToast = new Toast(context);
		mToast.setGravity(55, 0, 0);
		mToast.setDuration(Toast.LENGTH_LONG);
		mToast.setView(this.mLayout);
		mToast.show();
	}

	public void cancel() {
		if ( mToast != null) {
			mToast.cancel();
			mToast = null;
		}
	}

}
