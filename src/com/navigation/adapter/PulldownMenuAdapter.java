package com.navigation.adapter;

import java.util.ArrayList;

import com.navigation.control.PulldownMenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 下拉导航选择菜单数据适配器
 * @Description: 下拉导航选择菜单数据适配器

 * @File: PopupMenuAdapter.java

 * @Package com.navigation.adapter

 * @Author Hanyonglu

 * @Date 2012-7-29 上午09:51:12

 * @Version V1.0
 */
public class PulldownMenuAdapter extends BaseAdapter{
	private ArrayList<PulldownMenuItem> menuItems = null;
	
	public PulldownMenuAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	public PulldownMenuAdapter(ArrayList<PulldownMenuItem> menuItems){
		this.menuItems = menuItems;
	}
	
	@Override
	public int getCount(){
		return menuItems.size();
	}

	@Override
	public Object getItem(int position){
		return position;
	}

	@Override
	public long getItemId(int position){
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		/**
		 * 由于此处Item在可见屏幕中够用，所以不必缓存。
		 */
		if (null == convertView){
			convertView = menuItems.get(position).getView();
		}
		
		return convertView;
	}
}
