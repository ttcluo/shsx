package com.shsx.freshmarket.activity;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;

import com.shsx.freshmarket.R;
import com.shsx.freshmarket.bean.Goods;
import com.shsx.freshmarket.bean.Product;
import com.shsx.freshmarket.utils.JsonParase;
import com.shsx.freshmarket.utils.LoadAsyncTask;

public class GoodsViewActivity extends Activity {
	private int id = 120;
	private String path = "http://bjwxinter.34580.cn/SHWeixinData.asmx/GetProductDetail?json={%22device%22%3A%222%22%2C%22os%22%3A%229.1%22%2C%22deviceid%22%3A%2268DE36D4-4276-43CB-9DD2-521E127464D1%22%2C%22id%22%3A%22"
			+ id
			+ "%22%2C%22api%22%3A%221%22%2C%22size%22%3A640%2C%22version%22%3A%223.2.2%22%2C%22token%22%3A%22%22}";
	private ImageButton btnShare;
	private String goodsJson;
	private Goods goods;
	private ViewPager bannerGoods;
	private TextView textName;
	private TextView textContent;
	private TextView textAppraisal;
	private TextView textPrice;
	private TextView textNo;
	private TextView textCount;
	private TextView textAddress;
	private TextView textBrand;
	private TextView textMode;
	private ImageButton btnCart;

	public GoodsViewActivity() {
		super();
	}

	public GoodsViewActivity(int id) {
		this.id = id;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_view);
		InitMode();
		InitView();
		InitCtrl();
	}

	public void InitMode() {
		LoadAsyncTask loadTask = new LoadAsyncTask();
		try {
			goodsJson = new String(loadTask.execute(path).get());
			goods = JsonParase.getGoods(goodsJson);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public void InitView() {
		btnShare = (ImageButton) findViewById(R.id.btnShare);
		btnCart = (ImageButton) findViewById(R.id.btnCart);
		bannerGoods = (ViewPager) findViewById(R.id.bannerGoods);
		textName = (TextView) findViewById(R.id.TextName);
		textContent = (TextView) findViewById(R.id.TextContent);
		textAppraisal = (TextView) findViewById(R.id.textAppraisal);
		textPrice = (TextView) findViewById(R.id.textPrice);
		textNo = (TextView) findViewById(R.id.textNo);
		textCount = (TextView) findViewById(R.id.textCount);
		textAddress = (TextView) findViewById(R.id.textAddress);
		textBrand = (TextView) findViewById(R.id.textBrand);
		textMode = (TextView) findViewById(R.id.textMode);
	}

	public void InitCtrl() {
		Log.i("goodsJaon", goodsJson);
		Log.i("goodsSize", goods.getBanner().size() + "");
		Product product = goods.getProduct();
		textContent.setText(product.getLast_time_info());
		textName.setText(product.getName());
		textAppraisal.setText(product.getRate_price());
		textPrice.setText(product.getSx_price());
		textNo.setText(product.getPlace());
		textAddress.setText(product.getPlace());
		textCount.setText(product.getSell());
		textBrand.setText(product.getBrand());
		textMode.setText(product.getStore());
		btnShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showShare();
			}
		});
		btnCart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showSMS();
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ShareSDK.stopSDK(this);
	}

	private void showSMS() {
		SMSSDK.initSDK(this, "ae9e1a137ddf", " f9c4412a40411a0305391648b7b39a6d ");
		// 打开注册页面
		RegisterPage registerPage = new RegisterPage();
		registerPage.setRegisterCallback(new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
				// 解析注册结果
				if (result == SMSSDK.RESULT_COMPLETE) {
					@SuppressWarnings("unchecked")
					HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
					String country = (String) phoneMap.get("country");
					String phone = (String) phoneMap.get("phone");
					// 提交用户信息
					//registerUser(country, phone);
				}
			}
		});
		registerPage.show(this);

		// 打开通信录好友列表页面
		ContactsPage contactsPage = new ContactsPage();
		contactsPage.show(this);

	}

	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://bjwxinter.34580.cn/SHWeixinData.asmx/GetIndexFloor2?json=%7B%22device%22%3A%222%22%2C%22os%22%3A%229.1%22%2C%22deviceid%22%3A%2268DE36D4-4276-43CB-9DD2-521E127464D1%22%2C%22api%22%3A%221%22%2C%22size%22%3A160%2C%22version%22%3A%223.2.2%22%2C%22size_adv%22%3A640%7D");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("藤藤菜的测试应用FreshMarke接口分享");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		oks.setImagePath("/sdcard/test.jpg");// 确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://http://bjwxinter.34580.cn/images/ProductImages/SH_Image_11052_jpeg_160.jpeg");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://bjwxinter.34580.cn/SHWeixinData.asmx/GetIndexFloor2?json=%7B%22device%22%3A%222%22%2C%22os%22%3A%229.1%22%2C%22deviceid%22%3A%2268DE36D4-4276-43CB-9DD2-521E127464D1%22%2C%22api%22%3A%221%22%2C%22size%22%3A160%2C%22version%22%3A%223.2.2%22%2C%22size_adv%22%3A640%7D");

		// 启动分享GUI
		oks.show(this);
	}
}
