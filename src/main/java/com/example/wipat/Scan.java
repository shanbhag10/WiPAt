package com.example.wipat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Scan extends Activity {

	private TextView warning;
	private TextView timeRemaining;
	private Button calibrate;
	private int readingCount = 30;
	private int currentCount;
	String currentPositionName;
	WifiManager wifi;
	Timer timer;
	TimerTask myTimerTask;
	
	public class ResultData {
		private Router router;

		public Router getRouter() {
			return this.router;
		}

		public List<Integer> values;

		public ResultData(Router router) {
			// TODO Auto-generated constructor stub
			this.router = router;
			values = new ArrayList<Integer>();
		}
	}

	private List<ResultData> resultsData;
	private List<PositionData> positionsData;
	private PositionData positionData;

	@SuppressWarnings("null")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// System.out.println("test");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		warning = (TextView) findViewById(R.id.TextView1);
		timeRemaining = (TextView) findViewById(R.id.TextView2);
		calibrate = (Button) findViewById(R.id.start);
		
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		Intent intent = getIntent();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm...");
        alertDialog.setMessage("Scanning requires WiFi.");
        alertDialog.setPositiveButton("Turn on WiFi",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        // Activity transfer to wifi settings
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                });
        alertDialog.setCancelable(false);
        if(!wifi.isWifiEnabled()) {
            alertDialog.show();
        }

		currentPositionName ="data";
		if(intent.getBooleanExtra("isLearning",true))
			currentPositionName = intent.getStringExtra("POSITION_NAME");

		calibrate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				calibrate.setEnabled(false);
				warning.setText("DO NOT MOVE FOR");
				resultsData = new ArrayList<ResultData>();
				currentCount = 0;
				timer = new Timer();
				myTimerTask = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						refresh();
					}
				};
				timer.schedule(myTimerTask, 0, 1000);
			}
		});

	}

	private void refresh() {
		// TODO Auto-generated method stub
		if (currentCount >= readingCount) {
			if (myTimerTask != null)
				myTimerTask.cancel();

		}
		currentCount++;
		wifi.startScan();
		List<ScanResult> results = wifi.getScanResults();
		for (int i = 0; i < results.size(); i++) {
			// System.out.println("test2");
			String ssid0 = results.get(i).SSID;		//putting currently scanned ssid from wifimanager into string ssid0
			String bssid = results.get(i).BSSID;

			int rssi0 = results.get(i).level;		//currently scanned level
		//	Log.d("Scan bssid and rssi: ",bssid+":"+rssi0);
			boolean found = false;
			for (int pos = 0; pos < resultsData.size(); pos++) { //resultsData is from router class
				if (resultsData.get(pos).getRouter().getBSSID().equals(bssid)) {
					found = true;
		//			Log.d("Found became true","true");
					resultsData.get(pos).values.add(rssi0);
					break;
				}
			}
			if (!found) {

				ResultData data = new ResultData(new Router(ssid0, bssid));
				data.values.add(rssi0);
				resultsData.add(data);

			}
		}

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				timeRemaining
						.setText(" " + (readingCount - currentCount) + "s");
				if (currentCount >= readingCount) {
					returnResults();
				}
			}
		});

	}

	private void returnResults() {
		// TODO Auto-generated method stub

		positionData = new PositionData(currentPositionName);
		for (int length = 0; length < resultsData.size(); length++) {

			int sum = 0;
			for (int l = 0; l < resultsData.get(length).values.size(); l++) {
				sum += resultsData.get(length).values.get(l);

			}
			int average = sum / resultsData.get(length).values.size();
			Log.d("values are: ",""+resultsData.get(length).getRouter()+ "  avg: "+average);
			positionData.addValue(resultsData.get(length).getRouter(), average);
		}

		Intent intent = new Intent(getApplicationContext(), Positions.class);
		intent.putExtra("PositionData", (Serializable) positionData);
		setResult(RESULT_OK,intent);
		finish();
		

	}



}
