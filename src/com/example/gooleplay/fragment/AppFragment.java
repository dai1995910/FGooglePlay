package com.example.gooleplay.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gooleplay.activity.DetailActivity;
import com.example.gooleplay.adapter.AppRecyclerViewAdapter;
import com.example.gooleplay.bean.AppDataBean;
import com.example.gooleplay.gloable.PageStateCode;
import com.example.gooleplay.protocol.AppProtocol;

/**
 * 首页Fragment
 * 
 * @author admin
 *
 */
public class AppFragment extends BaseFragment {
	private ArrayList<AppDataBean> dataList;

	/* 当成果获取数据的时候，构建成功页面 */
	@Override
	protected View createSuccessView() {

		RecyclerView recyclerView = new RecyclerView(getActivity());
		AppRecyclerViewAdapter adapter = new AppRecyclerViewAdapter(dataList,
				getActivity());
		recyclerView.setAdapter(adapter);
		
		adapter.setOnItemClickListener(new com.example.gooleplay.adapter.BaseRecyclerViewAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View v, int position) {
				int realPosition = position - 1;
				// 处理单击事件，并且传递被单击的数据
				handleOnClickEvent(realPosition , dataList.get(realPosition).getPackageName());
			}
		});
		
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		return recyclerView;
	}

	/**
	 * 获取数据从服务器
	 */
	@Override
	protected int getStateAndDataFromService() {
		AppProtocol appProtocol = new AppProtocol();
		dataList = appProtocol.load(1);
		if (dataList == null) {
			return PageStateCode.STATE_ERROR;
		} else {
			return dataList.size() == 0 ? PageStateCode.STATE_EMPTY
					: PageStateCode.STATE_SUCCESS;
		}
	}
	
	//处理单击事件,实现单击打开下一个页面
		private void handleOnClickEvent(int positon, String packageName) {
			Intent intent = new Intent(getActivity() , DetailActivity.class);
			intent.putExtra("pkg", packageName);
			startActivity(intent);
		}

}
