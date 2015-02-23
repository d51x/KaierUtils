package ru.d51x.kaierutils;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.app.ListActivity;

import android.content.pm.PackageManager;

import java.io.BufferedReader;
import java.io.FileReader;
import android.view.LayoutInflater;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import java.util.Collections;
import java.util.Comparator;

public class AppActivity extends ListActivity {

	ArrayList appList;
	String className;
	String packageName;
	List <ResolveInfo> mApps;
	PackageManager mPackageManager;
	private String mPath;
	private AppsListAdapter appsListAdapter;
	private ArrayAdapter<AppInfo> mAdapter;


	public AppActivity () {
		this.mPath = "";
		this.mPackageManager = null;
		this.mApps = null;
		this.appList = new ArrayList();
		this.className = null;
		this.packageName = null;

	}


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		Intent intent;
		BufferedReader bufferedReader;



		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_assign_program);

		Bundle bundle = getIntent().getExtras();
		String pname = bundle.getString("name");
		if ( pname == null )
		{
			return;
		}
		mPath = "/data/tw/" + pname + "_name";

		mPackageManager = getPackageManager();
		intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		mApps = mPackageManager.queryIntentActivities(intent, 0);
		// mApps[0].activityInfo.taskAffinity   com.android.settings
		// mApps[0].activityInfo.name                       com.android.settings.Settings
		// mApps[0].activityInfo.packageName                com.android.settings
		// mApps[0].activityInfo.applicationInfo.icon  2130903040
		// mApps[0].activityInfo.applicationInfo.ClassName
		// mApps[0].activityInfo.applicationInfo.packageName
		try {
			BufferedReader bufferedReader2 = new BufferedReader(new FileReader(mPath));
			try {
				this.packageName = bufferedReader2.readLine();
				this.className = bufferedReader2.readLine();
				if (bufferedReader2 != null) bufferedReader2.close();
			} catch (Exception e2) {
				if (bufferedReader2 != null) bufferedReader2.close();
			}
			mPackageManager = getPackageManager();
			intent = new Intent("android.intent.action.MAIN", null);
			intent.addCategory("android.intent.category.LAUNCHER");
			this.mApps = this.mPackageManager.queryIntentActivities(intent, 0);
			for (ResolveInfo resolveInfo : this.mApps) {
				appList.add (new AppInfo(this, resolveInfo, null, null));
			}
			this.appsListAdapter = new AppsListAdapter(this, this);
			this.setListAdapter(this.appsListAdapter);



		} catch (Exception e) {
			DebugLogger.ToLog ("AppActivity", "could not open file " + mPath);
		}
	}


	private void getApps() {
		Intent intent = new Intent("android.intent.action.MAIN", null);
		intent.addCategory("android.intent.category.LAUNCHER");
		mApps = mPackageManager.queryIntentActivities(intent, 0);
		Collections.sort(mApps, new Comparator<ResolveInfo>() {
			@Override
			public int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
				return resolveInfo.activityInfo.loadLabel(getPackageManager()).toString().compareToIgnoreCase(resolveInfo2.activityInfo.loadLabel(getPackageManager()).toString());
			}
		});
	}

	protected void onNewIntent(Intent intent) {
		setIntent(intent);
	}

	protected void onResume() {
		Intent intent = getIntent();
		if (intent != null) {
			Object stringExtra = intent.getStringExtra("name");
			//setTitle(stringExtra);
			this.mPath = "/data/tw/" + stringExtra + "_name";
		}
		super.onResume();
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Toast.makeText(getApplicationContext(),
				"Вы выбрали " + l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
	}
}

class AppInfo {
	final AppActivity appActivity;
	Drawable mDrawable;
	String mName;
	ResolveInfo resolveInfo;

	AppInfo (AppActivity appActivity, ResolveInfo resolveInfo, String str, Drawable drawable) {
		this.appActivity = appActivity;
		this.resolveInfo = resolveInfo;
		this.mName = str;
		this.mDrawable = drawable;
	}
}

class AppsListAdapter extends BaseAdapter {
	final AppActivity appActivity;
	private Context mContext;

	public AppsListAdapter(AppActivity appActivity, Context context) {
		this.appActivity = appActivity;
		this.mContext = context;
	}

	private View makeView(ViewGroup viewGroup) {
		View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.track_list_item, viewGroup, false);
		AppItem appItem = new AppItem ();
		appItem.appTitle = (TextView) inflate.findViewById(R.id.line);
		appItem.markIcon = (ImageView) inflate.findViewById(R.id.play_indicator);
		appItem.appIcon = (ImageView) inflate.findViewById(R.id.icon);
		appItem.appIcon.setPadding (0, 0, 1, 0);
		inflate.setTag(appItem);
		return inflate;
	}

	private void makeView(View view, int i, ViewGroup viewGroup) {
		AppItem appItem = (AppItem) view.getTag();
		AppInfo appInfo = (AppInfo) getItem(i);
		if (appInfo != null) {
			ResolveInfo resolveInfo = appInfo.resolveInfo;
			if (appInfo.mName == null) {
				appInfo.mName = resolveInfo.loadLabel(this.appActivity.mPackageManager).toString();
			}
			if (appInfo.mDrawable == null) {
				appInfo.mDrawable = resolveInfo.loadIcon(this.appActivity.mPackageManager);
			}
			appItem.appIcon.setBackgroundDrawable (appInfo.mDrawable);
			appItem.appTitle.setText (appInfo.mName);
			if (resolveInfo.activityInfo.packageName.equals(this.appActivity.packageName) && resolveInfo.activityInfo.name.equals(this.appActivity.className)) {
				appItem.markIcon.setImageResource (R.drawable.indicator_ic_mp_playing_list);
				return;
			} else {
				appItem.markIcon.setImageDrawable (null);
				return;
			}
		}
		appItem.appIcon.setBackgroundResource (R.drawable.ic_launcher_home);
		appItem.markIcon.setImageDrawable (null);
	}

	public int getCount() {
		return this.appActivity.appList.size();
	}

	public Object getItem(int index) {
		return this.appActivity.appList.get(index);
	}

	public long getItemId(int index) {
		return (long) index;
	}

	public View getView(int index, View view, ViewGroup viewGroup) {
		if (view == null) {
			view = makeView (viewGroup);
		}
		makeView(view, index, viewGroup);
		return view;
	}
}

class AppItem {
	ImageView appIcon;
	TextView appTitle;
	ImageView markIcon;
	//final AppsListAdapter appListAdapter;

//	public AppItem (AppsListAdapter item) {
//		this.appListAdapter = item;
//
//	}
}