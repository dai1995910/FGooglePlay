package com.example.gooleplay.fragment;

import java.util.Random;

import com.example.gooleplay.gloable.MyApplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 首页Fragment
 * @author admin
 *
 */
public class TopFragment extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	protected View createSuccessView() {
		TextView tv = new TextView(MyApplication.getContext());
		tv.setText("Top页面加载成功");
		return tv;
	}

	@Override
	protected int getStateAndDataFromService() {
		synchronized(this) {
			SystemClock.sleep(2000);
			return 4;
		}
	}
	 
}
