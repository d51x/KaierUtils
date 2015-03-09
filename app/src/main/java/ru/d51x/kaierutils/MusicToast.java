package ru.d51x.kaierutils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;

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

	MusicToast (Context context) {
		this.context = context;
		mLayout = (RelativeLayout) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.music_toast, null);
		tvTrackTitle = (TextView) this.mLayout.findViewById (R.id.tvTrackTitle);
		tvArtistAlbum = (TextView) this.mLayout.findViewById(R.id.tvArtistAlbum);
		ivAlbumArt = (ImageView) this.mLayout.findViewById (R.id.ivAlbumArt);

	}

	public void setTrackTitle(String trackTitle) {
		TrackTitle =  (trackTitle != null) ? trackTitle : "";
	}

	public void setArtistAlbum(String artist, String album) {
		AlbumArtist = "";
		if (artist != null) {
			AlbumArtist = artist;
		}
		if (album != null) {
			AlbumArtist += " (" + album + ")";
		}
	}

	public void setAlbumArt(Bitmap albumArt) {
		if (albumArt != null) {
			ivAlbumArt.setImageBitmap(albumArt);
		} else {
			ivAlbumArt.setImageResource ( R.drawable.toast_music );
		}
	}

	public void showToast() {

		tvTrackTitle.setText ( TrackTitle );
		tvArtistAlbum.setText ( AlbumArtist );
		if ( mToast != null) { mToast.cancel();	}
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
