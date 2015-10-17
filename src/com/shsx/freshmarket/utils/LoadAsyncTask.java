package com.shsx.freshmarket.utils;

import android.os.AsyncTask;

public class LoadAsyncTask extends AsyncTask<String, Void, byte[]>{

	@Override
	protected byte[] doInBackground(String... arg0) {
		
		return HttpURLConnHelper.loadByteFromURL(arg0[0]);
	}

}
