package com.weaselhat.chronos;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchActivity extends Activity {
	private int elapsed = 0;
	private Ticker ticker;
	
	/* TODO list of start times
	 * 
	 *  keep just locally at first, but then put in bundle or SQL database
	 *  	(are bundles kept forever, or...?)
	 *  grab state in onPause, store in database, reinstate timers in onCreate
	 */
	
	// TODO lap times/markers, access to start time per stopwatch
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);
        
        final Button start = (Button) findViewById(R.id.new_stopwatch);
        start.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// only one at at time for now
				start.setClickable(false);
					
				// start the timer
				ticker = new Ticker(new Handler());
			}
		});        
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		
		final Button start = (Button) findViewById(R.id.new_stopwatch);
        start.setClickable(true);

        updateStatus("No timer");		
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		if (ticker != null)
			ticker.stop();
	}

	private void updateStatus(String text) {
        final TextView status = (TextView) findViewById(R.id.status);
        status.setText(text);    	
    }
    
	private void tick() {
		elapsed++;
		updateStatus(String.valueOf(elapsed));
	}
	
    private class Ticker implements Runnable {
    	private static final int INTERVAL = 1000; // ??? will we slowly lose time?
    	private Handler handler;
    	private boolean stopped = false;
    	
    	public Ticker(Handler h) {
    		handler = h;
    		post();
    	}
    	
		public void run() {
			tick();
    		post();
		}
		
		public void stop() {
			stopped = true;
		}
		
		private void post() {
			if (!stopped)
				handler.postDelayed(this, INTERVAL);
		}
    }
}