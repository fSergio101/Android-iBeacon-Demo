package com.radiusnetworks.ibeaconreference;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.altbeacon.beacon.BeaconManager;

/**
 *
 * @author dyoung
 *
 */
public class MainActivity extends Activity {
	protected static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "oncreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onMonitoringClicked(View view) {

	}


}
