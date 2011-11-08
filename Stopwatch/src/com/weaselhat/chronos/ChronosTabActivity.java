package com.weaselhat.chronos;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class ChronosTabActivity extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TabHost tabHost = getTabHost();  // The activity TabHost
		TabHost.TabSpec spec;  // Reusable TabSpec for each tab
		Intent intent;  // Reusable Intent for each tab

		// Initialize intents and tab specs
		intent = new Intent().setClass(this, TimerActivity.class);
		spec = tabHost.newTabSpec("timer")
			    .setIndicator("Timer")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, StopwatchActivity.class);
		spec = tabHost.newTabSpec("stopwatch")
			    .setIndicator("Stopwatch")
				.setContent(intent);
		tabHost.addTab(spec);

	    /*
		intent = new Intent().setClass(this, AlarmActivity.class);
		spec = tabHost.newTabSpec("alarm")
				.setIndicator("Alarm")
				.setContent(intent);
		tabHost.addTab(spec);
	 	*/

		tabHost.setCurrentTab(2);
	}
}
