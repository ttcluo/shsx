package com.shsx.freshmarket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.shsx.freshmarket.R;
/**
 * ��activityΪ����������ʾ3�뼴��ת��HomePageActivity
 * @author luochuan
 *
 */
public class WelcomeActivity extends Activity{
	
	 private ImageView welcomeImg = null;  
	  
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.welcome);  
	        welcomeImg = (ImageView) this.findViewById(R.id.welcome_img);  
	        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);  
	        anima.setDuration(3000);// ���ö�����ʾʱ��  
	        welcomeImg.startAnimation(anima);  
	        anima.setAnimationListener(new AnimationImpl()); 
	    }  
	  
	    private class AnimationImpl implements AnimationListener {  
	  
	        @Override  
	        public void onAnimationStart(Animation animation) {  
	            //welcomeImg.setBackgroundResource(R.drawable.loading_bg);  
	        }  
	  
	        @Override  
	        public void onAnimationEnd(Animation animation) {  
	            skip(); // ������������ת�����ҳ��  
	        }  
	  
	        @Override  
	        public void onAnimationRepeat(Animation animation) {  
	  
	        }  
	  
	    }  
	  
	    private void skip() {  
	        startActivity(new Intent(this, CityListActivity.class));  
	        finish();  
	    }  
	    
}
