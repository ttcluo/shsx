package com.shsx.freshmarket.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.shsx.freshmarket.R;
import com.shsx.freshmarket.bean.FlashSale;
import com.shsx.freshmarket.bean.Floor;
import com.shsx.freshmarket.bean.HomeBean;
import com.shsx.freshmarket.bean.ItemFloor;
import com.shsx.freshmarket.utils.HttpURLConnHelper;
import com.shsx.freshmarket.utils.JsonParase;
import com.shsx.freshmarket.utils.LoadAsyncTask;

public class TabHomeActivity extends Activity implements OnClickListener{

	private PullToRefreshListView listviewHome;
	private ArrayList<ImageView> items;
	private String path = "http://bjwxinter.34580.cn/SHWeixinData.asmx/GetIndexFlashSale?json={%22api%22%3A%221%22%2C%22device%22%3A%222%22%2C%22os%22%3A%229.1%22%2C%22deviceid%22%3A%2268DE36D4-4276-43CB-9DD2-521E127464D1%22%2C%22size_discounts%22%3A160%2C%22version%22%3A%223.2.2%22}";
	private String url = "http://bjwxinter.34580.cn/SHWeixinData.asmx/GetIndexFloor2?json=%7B%22device%22%3A%222%22%2C%22os%22%3A%229.1%22%2C%22deviceid%22%3A%2268DE36D4-4276-43CB-9DD2-521E127464D1%22%2C%22api%22%3A%221%22%2C%22size%22%3A160%2C%22version%22%3A%223.2.2%22%2C%22size_adv%22%3A640%7D";
	private HomeBean homeBean;
	private List<Floor> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_home);
		items = new ArrayList<ImageView>();
		ImageView imageView = new ImageView(TabHomeActivity.this);
		imageView.setImageResource(R.drawable.ic_launcher);
		items.add(imageView);
		LoadAsyncTask homeTask = new LoadAsyncTask();
		try {
			String jsonHome = new String(homeTask.execute(url).get());
			homeBean = JsonParase.getHomeBean(jsonHome);
			data = homeBean.getData();
			Log.i("homeBean", data.size() + "");

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			homeTask.cancel(true);
		}
		InitView();
	}

	public void InitView() {
		listviewHome = (PullToRefreshListView) findViewById(R.id.listviewHome);
		listviewHome.setAdapter(new BaseAdapter() {

			private View itemView;
			private View gvtwo;
			private View gvthree;
			private View gvone;
			private String jsonSale;
			private ArrayList<FlashSale> flashSaleList;
			private FlashSale flashSale;
			private Bitmap bitmap;
			private Bitmap bp;
			private byte[] imBytes;
			private String imageUrl2;
			private TextView tvTitle;
			private TextView tvDescription;

			@Override
			public View getView(int position, View view, ViewGroup arg2) {
				switch (position) {
				case 0:
					itemView = items.get(0);
					// itemView = view.inflate(TabHomeActivity.this,
					// R.layout.widget_banner_layout, null);
					// ViewPager viewPager = (ViewPager) itemView
					// .findViewById(R.id.viewPager);
					// viewPager.setAdapter(new PagerAdapter() {
					//
					// @Override
					// public boolean isViewFromObject(View arg0, Object arg1) {
					// // TODO Auto-generated method stub
					// return false;
					// }
					//
					// @Override
					// public int getCount() {
					// // TODO Auto-generated method stub
					// return 0;
					// }
					// });

					break;
				case 1:
					itemView = view.inflate(TabHomeActivity.this,
							R.layout.list_item_home_flashsale, null);
					flashSaleList = new ArrayList<FlashSale>();
					LoadAsyncTask task = new LoadAsyncTask();
					try {
						jsonSale = new String(task.execute(path).get());
						flashSaleList = JsonParase.getFlashSaleList(jsonSale);
						Log.i("jsonSale", jsonSale);

					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					Log.i("flashSaleList", "" + flashSaleList.size());
					for (int i = 0; i < flashSaleList.size(); i++) {
						HorizontalScrollView hsvMian = (HorizontalScrollView) itemView
								.findViewById(R.id.hsvMian);
						LayoutInflater inflater = LayoutInflater
								.from(TabHomeActivity.this);
						View sale = inflater.inflate(
								R.layout.list_item_home_flash_sale, null);
						LinearLayout llMian = (LinearLayout) hsvMian
								.findViewById(R.id.llMian);

						ImageView ivFlashSale = (ImageView) sale
								.findViewById(R.id.ivFlashSale);
						TextView tvSaleName = (TextView) sale
								.findViewById(R.id.tvSaleName);
						TextView tvSalePrice = (TextView) sale
								.findViewById(R.id.tvSalePrice);
						TextView tvSaleWeight = (TextView) sale
								.findViewById(R.id.tvSaleWeight);
						TextView tvSaleDiscount = (TextView) sale
								.findViewById(R.id.tvSaleDiscount);
						flashSale = flashSaleList.get(i);
						Log.i("name", flashSale.getProductName());
						tvSaleName.setText(flashSale.getProductName());
						tvSaleDiscount.setText(flashSale.getDiscount());
						tvSalePrice.setText("¥" + flashSale.getPeriodMoney());
						tvSaleWeight.setText("/" + flashSale.getWeight());
						ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(TabHomeActivity.this));
						ImageLoader.getInstance().displayImage(flashSale.getImage(), ivFlashSale);
						llMian.addView(sale);
						
					}

					break;
				case 2:
					itemView = view.inflate(TabHomeActivity.this,
							R.layout.list_item_home_icon, null);
					LinearLayout llHomeIcon = (LinearLayout) itemView
							.findViewById(R.id.llHomeIcon);

					break;
				case 3:
					itemView = view.inflate(TabHomeActivity.this,
							R.layout.list_item_home_floor_gridview, null);
					tvTitle = (TextView) itemView.findViewById(R.id.tvTitel);
					tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
					tvTitle.setText(data.get(0).getTitle());
					tvDescription.setText(data.get(0).getDescription());
					
					GridView gvMian = (GridView) itemView
							.findViewById(R.id.gvMian);
					

					gvMian.setAdapter(new BaseAdapter() {
						Floor floor = data.get(0);
						List<ItemFloor> floorItem = floor.getFloorItem();
						@Override
						public View getView(int arg0, View view, ViewGroup arg2) {
							LayoutInflater inflater2 = LayoutInflater
									.from(TabHomeActivity.this);
							view = inflater2.inflate(
									R.layout.list_item_home_gridview_two, null);
							TextView tvProductName = (TextView) view.findViewById(R.id.tvProductName);
							TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
							TextView tvWeight = (TextView) view.findViewById(R.id.tvWeight);
							tvPrice.setText(floorItem.get(arg0).getPRICE()+"");
							tvProductName.setText(floorItem.get(arg0).getProductName());
							tvWeight.setText(floorItem.get(arg0).getWeight());
							ImageView ivHome4 = (ImageView) view.findViewById(R.id.ivHome4);
							ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(TabHomeActivity.this));
							ImageLoader.getInstance().displayImage(floorItem.get(arg0).getImage(), ivHome4);
							
							return view;
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
							return 2;
						}
					});
					break;
				case 4:
					Floor floor1 = data.get(1);
					List<ItemFloor> floorItem1 = floor1.getFloorItem();
					String imageUrl = floorItem1.get(0).getUrl();
					Log.i("imageUrl", imageUrl);
					
					itemView = view.inflate(TabHomeActivity.this,
							R.layout.widget_banner_item, null);
					ImageView imgBanner = (ImageView) itemView
							.findViewById(R.id.imgBanner);
					ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(TabHomeActivity.this));
					ImageLoader.getInstance().displayImage(imageUrl, imgBanner);
					imgBanner.setClickable(true);
					imgBanner.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							Intent intent = new Intent(TabHomeActivity.this,GoodsViewActivity.class);
							startActivity(intent);
						}
					});
					break;
				case 5:
					itemView = view.inflate(TabHomeActivity.this,
							R.layout.list_item_home_floor_gridview, null);
					tvTitle = (TextView) itemView.findViewById(R.id.tvTitel);
					tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
					tvTitle.setText(data.get(2).getTitle());
					tvDescription.setText(data.get(2).getDescription());
					GridView gvMian2 = (GridView) itemView
							.findViewById(R.id.gvMian);
					gvMian2.setNumColumns(3);
					
					gvMian2.setAdapter(new BaseAdapter() {
						Floor floor = data.get(2);
						List<ItemFloor> floorItem = floor.getFloorItem();
						
						@Override
						public View getView(int arg0, View view, ViewGroup arg2) {
							LayoutInflater inflater3 = LayoutInflater
									.from(TabHomeActivity.this);
							view = inflater3.inflate(
									R.layout.list_item_home_gridview_three, null);
							ItemFloor itemFloor = floorItem.get(arg0);
							String image = itemFloor.getImage();
							Log.i("image", image);
							ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(TabHomeActivity.this));
							ImageView ivHome3 = (ImageView) view
									.findViewById(R.id.ivHome3);
							ImageLoader.getInstance().displayImage(image, ivHome3);
							TextView tvTitel = (TextView) view.findViewById(R.id.tvTitel);
							TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
							TextView tvWeight = (TextView) view.findViewById(R.id.tvWeight);
							tvTitel.setText(itemFloor.getProductName());
							tvPrice.setText(""+itemFloor.getPRICE());
							Log.i("price", itemFloor.getPRICE()+"");
							tvWeight.setText("/"+itemFloor.getWeight());
							return view;
						}

						@Override
						public long getItemId(int arg0) {
							return arg0;
						}

						@Override
						public Object getItem(int arg0) {
							return floorItem.get(arg0);
						}

						@Override
						public int getCount() {
							return floorItem.size();
						}
					});

					break;
				case 6:
					itemView = view.inflate(TabHomeActivity.this,
							R.layout.list_item_home_floor_gridview, null);
					tvTitle = (TextView) itemView.findViewById(R.id.tvTitel);
					tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
					tvTitle.setText(data.get(3).getTitle());
					tvDescription.setText(data.get(3).getDescription());
					GridView gvMian3 = (GridView) itemView
							.findViewById(R.id.gvMian);
			
					gvMian3.setAdapter(new BaseAdapter() {
						Floor floor = data.get(3);
						List<ItemFloor> floorItem = floor.getFloorItem();
						
						@Override
						public View getView(int arg0, View view, ViewGroup arg2) {
							LayoutInflater inflater4 = LayoutInflater
									.from(TabHomeActivity.this);
							view = inflater4.inflate(
									R.layout.list_item_home_gridview_two, null);
							
							TextView tvProductName = (TextView) view.findViewById(R.id.tvProductName);
							TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
							TextView tvWeight = (TextView) view.findViewById(R.id.tvWeight);
							tvPrice.setText(floorItem.get(arg0).getPRICE()+"");
							tvProductName.setText(floorItem.get(arg0).getProductName());
							tvWeight.setText(floorItem.get(arg0).getWeight());
							ImageView ivHome4 = (ImageView) view.findViewById(R.id.ivHome4);
							ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(TabHomeActivity.this));
							ImageLoader.getInstance().displayImage(floorItem.get(arg0).getImage(), ivHome4);
							return view;
						}

						@Override
						public long getItemId(int arg0) {
							return arg0;
						}

						@Override
						public Object getItem(int arg0) {
							return floorItem.get(arg0);
						}

						@Override
						public int getCount() {
							return floorItem.size();
						}
					});
					break;
				case 7:
					Floor floor2 = data.get(4);
					List<ItemFloor> floorItem2 = floor2.getFloorItem();
					imageUrl2 = floorItem2.get(0).getUrl();
					Log.i("imageUr2", imageUrl2);
					itemView = view.inflate(TabHomeActivity.this,
							R.layout.widget_banner_item, null);
					ImageView imgBanner2 = (ImageView) itemView
							.findViewById(R.id.imgBanner);
					ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(TabHomeActivity.this));
					ImageLoader.getInstance().displayImage(imageUrl2, imgBanner2);
					break;
				case 8:
					itemView = view.inflate(TabHomeActivity.this,
							R.layout.list_item_home_floor_gridview, null);
					tvTitle = (TextView) itemView.findViewById(R.id.tvTitel);
					tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
					tvTitle.setText(data.get(5).getTitle());
					tvDescription.setText(data.get(5).getDescription());
					GridView gvMian4 = (GridView) itemView
							.findViewById(R.id.gvMian);
			
					gvMian4.setAdapter(new BaseAdapter() {
						Floor floor = data.get(5);
						List<ItemFloor> floorItem = floor.getFloorItem();
						
						@Override
						public View getView(int arg0, View view, ViewGroup arg2) {
							LayoutInflater inflater4 = LayoutInflater
									.from(TabHomeActivity.this);
							view = inflater4.inflate(
									R.layout.list_item_home_gridview_two, null);
							
							TextView tvProductName = (TextView) view.findViewById(R.id.tvProductName);
							TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
							TextView tvWeight = (TextView) view.findViewById(R.id.tvWeight);
							tvPrice.setText(floorItem.get(arg0).getPRICE()+"");
							tvProductName.setText(floorItem.get(arg0).getProductName());
							tvWeight.setText(floorItem.get(arg0).getWeight());
							ImageView ivHome4 = (ImageView) view.findViewById(R.id.ivHome4);
							ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(TabHomeActivity.this));
							ImageLoader.getInstance().displayImage(floorItem.get(arg0).getImage(), ivHome4);
							return view;
						}

						@Override
						public long getItemId(int arg0) {
							return arg0;
						}

						@Override
						public Object getItem(int arg0) {
							return floorItem.get(arg0);
						}

						@Override
						public int getCount() {
							return floorItem.size();
						}
					});
					break;
				case 9:
					itemView = view.inflate(TabHomeActivity.this,
							R.layout.list_item_home_floor_gridview, null);
					tvTitle = (TextView) itemView.findViewById(R.id.tvTitel);
					tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
					tvTitle.setText(data.get(6).getTitle());
					tvDescription.setText(data.get(6).getDescription());
					GridView gvMian5 = (GridView) itemView
							.findViewById(R.id.gvMian);
					gvMian5.setNumColumns(1);
					gvMian5.setAdapter(new BaseAdapter() {
						Floor floor = data.get(6);
						List<ItemFloor> floorItem = floor.getFloorItem();
						@Override
						public View getView(int arg0, View view, ViewGroup arg2) {
							LayoutInflater inflater4 = LayoutInflater
									.from(TabHomeActivity.this);
							view = inflater4.inflate(
									R.layout.list_item_home_gridview_one, null);
							ImageView ivHome5 = (ImageView) view.findViewById(R.id.ivHome5);
							ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(TabHomeActivity.this));
							ImageLoader.getInstance().displayImage(floorItem.get(arg0).getUrl(), ivHome5);
							
							return view;
						}
						
						@Override
						public long getItemId(int arg0) {
							return arg0;
						}
						
						@Override
						public Object getItem(int arg0) {
							return floorItem.get(arg0);
						}
						
						@Override
						public int getCount() {
							// TODO Auto-generated method stub
							return floorItem.size();
						}
					});
					break;
				default:
					break;
				}

				return itemView;
			}

			@Override
			public long getItemId(int arg0) {
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				return null;
			}

			@Override
			public int getCount() {
				return 10;
			}
		});
	}

	@Override
	public void onClick(View view) {
	
	}

}
