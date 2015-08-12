/*
 * Copyright 2015 Mustamara Labs, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yaftamobile.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.yaftamobile.sample.R;
import com.yaftamobile.sdk.AdError;
import com.yaftamobile.sdk.AdListener;
import com.yaftamobile.sdk.AdRequest;
import com.yaftamobile.sdk.RunMode;
import com.yaftamobile.sdk.ads.banner.YaftaMobileBannerView;

public class MainActivity extends Activity implements AdListener {

	YaftaMobileBannerView bannerView;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		bannerView = (YaftaMobileBannerView) findViewById(R.id.bannerView);

		// (Optional) Set refresh rate.
		bannerView.setRefreshRate(45);

		// Set an ad listener.
		bannerView.setAdListener(this);

		// Create a new AdRequest
		AdRequest adRequest = new AdRequest();

		/*
		 * While testing set mode to RunMode.TEST, Ensure that you set the Mode
		 * to RunMode.LIVE when distributing the app to users.
		 */

		adRequest.setRunMode(RunMode.TEST);

		bannerView.requestAd(adRequest);

	}

	// Called when an ad is successfully loaded.
	@Override
	public void OnAdLoaded() {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "Ad successfully loaded.",
				Toast.LENGTH_SHORT).show();

	}

	// Called when an ad is clicked.
	@Override
	public void onAdClicked() {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "Ad Clicked.",
				Toast.LENGTH_SHORT).show();

	}

	// Called when a failure occurred during ad loading.
	@Override
	public void onAdError(AdError error) {
		// TODO Auto-generated method stub
		Toast.makeText(
				getApplicationContext(),
				"Ad failed to load. Code: " + error.getErrorCode()
						+ ", Message: " + error.getErrorMessage(),
				Toast.LENGTH_SHORT).show();

	}

	@Override
	protected void onPause() {
		if (bannerView != null) {
			bannerView.setAutoRefresh(false);
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (bannerView != null) {
			bannerView.setAutoRefresh(true);
		}
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		if (bannerView != null) {
			bannerView.destroy();
		}
		super.onDestroy();
	}
}
