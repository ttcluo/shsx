package com.shsx.freshmarket.activity;

import java.util.List;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shsx.freshmarket.R;

public class MainTabActivity extends TabActivity {

	private String cityName;
	private Button btnCity;
	private FrameLayout tabcontent;
	private PullToRefreshListView listviewHome;
	private List<ImageView> items;
	private TabWidget tabs;
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maintab);
		cityName = getIntent().getStringExtra("CityName");
		tabHost = getTabHost();
		InitView();
	}

	public void InitView() {
		/**
		 * 初始化菜单栏
		 */
		tabs = (TabWidget) findViewById(android.R.id.tabs);
		addTab();
		tabHost.setCurrentTab(0);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String arg0) {
				if("tab2".equals(arg0)){
					LinearLayout lineHome = (LinearLayout) findViewById(R.id.lineHome);
					RelativeLayout rlMain = (RelativeLayout) findViewById(R.id.rlMain);
					rlMain.setVisibility(lineHome.GONE);
					//rlMain.removeViews(0, 5);
				}
				if("tab1".equals(arg0)){
					LinearLayout lineHome = (LinearLayout) findViewById(R.id.lineHome);
					RelativeLayout rlMain = (RelativeLayout) findViewById(R.id.rlMain);
					rlMain.setVisibility(lineHome.VISIBLE);;
				}
				
			}
		});
	}

	private void addTab() {
		
		TabSpec tabSpec = tabHost.newTabSpec("tab1");
		LayoutInflater tabInflater = LayoutInflater.from(MainTabActivity.this);
		View tabnav = tabInflater.inflate(R.layout.tab_main_nav, null);
		TextView textHome = (TextView) tabnav.findViewById(R.id.tab_main_nav_tvTitle);
		textHome.setText("首页");
		tabSpec.setIndicator(tabnav);
		tabSpec.setContent(new Intent(this,TabHomeActivity.class));
		tabHost.addTab(tabSpec);
		
		TabSpec tabSpec2 = tabHost.newTabSpec("tab2");
		LayoutInflater tabEventInflater = LayoutInflater.from(MainTabActivity.this);
		View tabEvent = tabEventInflater.inflate(R.layout.tab_event_nav, null);	
		tabSpec2.setIndicator(tabEvent);
		Intent itent = new Intent(MainTabActivity.this,TabEventActivity.class);
		tabSpec2.setContent(itent);
		tabHost.addTab(tabSpec2);
		
		TabSpec tabSpec3 = tabHost.newTabSpec("tab3");
		LayoutInflater tabSortInflater = LayoutInflater.from(MainTabActivity.this);
		View tabSort = tabSortInflater.inflate(R.layout.tab_sort_nav, null);
		tabSpec3.setIndicator(tabSort);
		Intent itent2 = new Intent(MainTabActivity.this,TabSortActivity.class);
		tabSpec3.setContent(itent2);
		tabHost.addTab(tabSpec3);
		
		TabSpec tabSpec4 = tabHost.newTabSpec("tab4");
		LayoutInflater tabCartInflater = LayoutInflater.from(MainTabActivity.this);
		View tabCart = tabSortInflater.inflate(R.layout.tab_cart_nav, null);
		tabSpec4.setIndicator(tabCart);
		Intent itent3 = new Intent(MainTabActivity.this,TabCartActivity.class);
		tabSpec4.setContent(itent2);
		tabHost.addTab(tabSpec4);
		
		TabSpec tabSpec5 = tabHost.newTabSpec("tab5");
		LayoutInflater tabuserInflater = LayoutInflater.from(MainTabActivity.this);
		View tabUser = tabSortInflater.inflate(R.layout.tab_my_nav, null);
		tabSpec5.setIndicator(tabUser);
		Intent itent4 = new Intent(MainTabActivity.this,TabUserActivity.class);
		tabSpec5.setContent(itent2);
		tabHost.addTab(tabSpec5);
		
	}

}
