package com.shsx.freshmarket.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shsx.freshmarket.R;
/**
 * 此fragment加载在maintab上
 * @author luochuan
 *
 */
public class ContentFragment extends Fragment {

	private PullToRefreshListView listviewHome;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.activity_tab_home, container,
//				false);
//		listviewHome = (PullToRefreshListView) view
//				.findViewById(R.id.listviewHome);
//		
//		listviewHome.setAdapter(new BaseAdapter() {
//			
//			@Override
//			public View getView(int arg0, View arg1, ViewGroup arg2) {
//				LayoutInflater inflater = LayoutInflater.from(getActivity());
//				View adv = inflater.inflate(R.layout.list_item_home_banner, null, false);
//				View blHome = adv.findViewById(R.id.blHome);
//				View view2 = blHome.inflate(getActivity(), R.layout.widget_banner_layout, null);
//				ViewPager viewPager = (ViewPager) view2.findViewById(R.id.viewPager);
//				viewPager.setAdapter(new PagerAdapter() {
//					
//					@Override
//					public boolean isViewFromObject(View arg0, Object arg1) {
//						// TODO Auto-generated method stub
//						return arg0==arg1;
//					}
//					
//					@Override
//					public int getCount() {
//						// TODO Auto-generated method stub
//						return 1;
//					}
//					@Override
//					public Object instantiateItem(ViewGroup container,
//							int position) {
//						// TODO Auto-generated method stub
//						return super.instantiateItem(container, position);
//						
//						//container.addView(R.drawable.ic_launcher);
//					}
//					@Override
//					public void destroyItem(ViewGroup container, int position,
//							Object object) {
//						// TODO Auto-generated method stub
//						super.destroyItem(container, position, object);
//					}
//				});
				

//				return null;
//			}
//			
//			@Override
//			public Object getItem(int arg0) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//			
//			@Override
//			public int getCount() {
//				// TODO Auto-generated method stub
//				return 1;
//			}
//
//			@Override
//			public long getItemId(int arg0) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		});
		
		return null;
	}
	private void InitData(){
		
	}
	
}
