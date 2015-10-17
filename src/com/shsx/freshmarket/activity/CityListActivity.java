package com.shsx.freshmarket.activity;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shsx.freshmarket.R;
import com.shsx.freshmarket.bean.City;
import com.shsx.freshmarket.utils.HttpURLConnHelper;
import com.shsx.freshmarket.utils.JsonParase;

public class CityListActivity extends Activity {
	private String path = "http://weixininter.34580.cn/SHWeixinData.asmx/GetCityServiceUrl?json={%22device%22%3A%225%22%2C%22os%22%3A%224.2.2%22%2C%22api%22%3A%221%22%2C%22deviceid%22%3A%22d4%3A6e%3A5c%3Af7%3A40%3A2d%22%2C%22version%22%3A%222.2.2%22}";
	private LinkedList<String> mListItems;
	private PullToRefreshListView mPullRefreshListView;
	private ArrayAdapter<String> mAdapter;
	private ListView actualListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_list);
		int tag = 1;
		if (tag == 1) {
			Intent intent = new Intent(CityListActivity.this,
					MainTabActivity.class);
			intent.putExtra("CityName", "asdf");
			startActivity(intent);
		}else{
			InitView();
			InitData();
			InitCtrl();	
		}
	
		//actualListView.setAdapter(mAdapter);
	}

	private void InitView() {
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.listView);

	}

	private void InitData() {
		mListItems = new LinkedList<String>();
		InitDataTask dataTask = new InitDataTask();
		try {
			byte[] bs = dataTask.execute(path).get();
			String js = new String(bs);
			List<City> list = JsonParase.GetCityList(js);
			Log.i("json", list.size() + "");
			for (int i = 0; i < list.size(); i++) {
				mListItems.add(list.get(i).getCityName());
			}
			Log.i("mlistitems", mListItems + "");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mListItems);
		actualListView = mPullRefreshListView.getRefreshableView();
	}

	private void InitCtrl() {
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						String label = DateUtils.formatDateTime(
								getApplicationContext(),
								System.currentTimeMillis(),
								DateUtils.FORMAT_SHOW_TIME
										| DateUtils.FORMAT_SHOW_DATE
										| DateUtils.FORMAT_ABBREV_ALL);

						// Update the LastUpdatedLabel
						refreshView.getLoadingLayoutProxy()
								.setLastUpdatedLabel(label);

						// Do work to refresh the list here.
						new GetDataTask().execute();
					}
				});
		mPullRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CityListActivity.this,
						MainTabActivity.class);
				switch (arg2) {
				case 0:
					Log.i("beijing", mListItems.get(0));
					intent.putExtra("CityName", mListItems.get(0));
					startActivity(intent);
					break;

				case 1:
					Log.i("beijing", mListItems.get(0));
					intent.putExtra("CityName", mListItems.get(0));
					startActivity(intent);
					break;
				case 2:
					Log.i("beijing", mListItems.get(1));
					intent.putExtra("CityName", mListItems.get(1));
					startActivity(intent);
					break;
				case 3:
					Log.i("beijing", mListItems.get(2));
					intent.putExtra("CityName", mListItems.get(2));
					startActivity(intent);
					break;
				}

			}
		});
	}

	/**
	 * 此方法为初始异步加载城市数据
	 * 
	 * @author luochuan
	 * 
	 */
	private class InitDataTask extends AsyncTask<String, Void, byte[]> {

		@Override
		protected byte[] doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return HttpURLConnHelper.loadByteFromURL(arg0[0]);
		}

	}

	private class GetDataTask extends AsyncTask<Void, Void, String> {

		// 后台处理部分
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			String str = "Added after refresh...I add";
			return str;
		}

		// 这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
		// 根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
		@Override
		protected void onPostExecute(String result) {
			// 在头部增加新添内容
			mListItems.addFirst(result);

			// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
			mAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
}
