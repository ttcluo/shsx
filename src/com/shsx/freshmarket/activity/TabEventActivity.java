package com.shsx.freshmarket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shsx.freshmarket.R;

public class TabEventActivity extends Activity {

	private PullToRefreshListView listviewEvent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_event);
		InitView();

	}

	public void InitView() {
		listviewEvent = (PullToRefreshListView) findViewById(R.id.listviewEvent);
		listviewEvent.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int arg0, View view, ViewGroup arg2) {

				View view2 = view.inflate(TabEventActivity.this,
						R.layout.list_item_eventtwo, null);

				return view2;
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 4;
			}
		});

	}

}
