package com.bilkentieee.mobilgunler;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.bilkentieee.mobilgunler.R;

public class MobilGunlerActivity extends TabActivity {
	/** Called when the activity is first created. */

	public static Context context;
	LocalActivityManager mLocalActivityManager;
	TabHost tabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		context = this;

		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
			ViewParent parent = titleView.getParent();
			if (parent != null && (parent instanceof View)) {
				View parentView = (View) parent;
				parentView.setBackgroundColor(Color.BLUE);
			}
		}

		tabHost = getTabHost();

		TabSpec mapSpec = tabHost.newTabSpec(getResources().getString(R.string.mobilgunler));
		mapSpec.setIndicator(getString(R.string.mobilgunler),
				getResources().getDrawable(R.drawable.mg));
		Intent mapIntent = new Intent(this, AboutMobilGunlerActivity.class);
		mapSpec.setContent(mapIntent);

		TabSpec listSpec = tabHost.newTabSpec(getResources().getString(R.string.oturumlar));
		listSpec.setIndicator(getString(R.string.oturumlar), getResources()
				.getDrawable(R.drawable.seminar));
		Intent listIntent = new Intent(this, TabGroupActivity.class);
		listSpec.setContent(listIntent);

		TabSpec videospec = tabHost.newTabSpec("ShoppingCart");
		videospec.setIndicator(getString(R.string.sponsor), getResources()
				.getDrawable(R.drawable.sponsors));
		Intent videosIntent = new Intent(this, SponsorlarActivity.class);
		videospec.setContent(videosIntent);

		TabSpec contactSpec = tabHost.newTabSpec(getResources().getString(R.string.iletisim));
		contactSpec.setIndicator(getString(R.string.iletisim), getResources()
				.getDrawable(R.drawable.phone));
		Intent contactIntent = new Intent(this, IletisimActivity.class);
		contactSpec.setContent(contactIntent);

		
		// Adding all TabSpec to TabHost
		tabHost.addTab(mapSpec);
		tabHost.addTab(listSpec);
		tabHost.addTab(videospec);
		tabHost.addTab(contactSpec);

	}
}