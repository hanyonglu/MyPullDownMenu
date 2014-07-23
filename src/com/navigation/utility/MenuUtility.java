package com.navigation.utility;

import com.navigation.acitvity.R;
import com.navigation.control.PulldownMenuView;

import android.content.Context;
import android.view.View;

/**
 * PulldownMenuView基本操作类
 * @Description: PulldownMenuView基本操作类

 * @File: PulldownMenuUtility.java

 * @Package com.navigation.utility

 * @Author Hanyonglu

 * @Date 2012-7-30 上午11:41:04

 * @Version V1.0
 */
public class MenuUtility {
	private Context context = null;
	// PulldownMenuView对象
	private PulldownMenuView menu = null;
	// 图片资源
	private int[] imageRes = null;
	// 文字内容
	private String[] texts = null;
	// 菜单高度
	private int height = 0;
	private View anchorView = null;
	
	/**
	 * 默认的构造器
	 */
	public MenuUtility() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 带Context的构造器
	 * @param context
	 */
	public MenuUtility(Context context) {
		// TODO Auto-generated constructor stub
		this(context,null,null,0,null);
	}
	
	/**
	 * 带多参的构造器
	 * @param context
	 * @param imageRes
	 * @param texts
	 */
	public MenuUtility(Context context,int[] imageRes,String[] texts,int height,View anchorView){
		this.context = context;
		this.imageRes = imageRes;
		this.texts = texts;
		this.height = height;
		this.anchorView = anchorView;
	}
	
	/**
	 * 设置图片资源
	 * @param imageRes
	 */
	public void setImageRes(int[] imageRes){
		this.imageRes = imageRes;
	}
	
	/**
	 * 设置文字内容
	 * @param texts
	 */
	public void setTexts(String[] texts){
		this.texts = texts;
	}
	
	/**
	 * 设置高度
	 * @param height
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * 设置显示的位置
	 * @param anchor
	 */
	public void setAnchorView(View anchor){
		anchorView = anchor;
	}
	
	/**
	 * 获取PulldownMenuView对象
	 * 以下拉的形式展现出来菜单
	 * @return
	 */
	public PulldownMenuView getPulldownMenuView(String currentItem){
		PulldownMenuView menu = new PulldownMenuView(context);
		menu.setImageRes(imageRes);
		menu.setMenuText(texts);
		menu.setHeight(height);
		menu.setAnchorView(anchorView);
		menu.setCurrentItem(currentItem);
		menu.setBackground(R.drawable.navigation_bg);
		
		return menu;
	}
	
	/**
	 * 获取PulldownMenuView对象
	 * 以向上弹出的方式展现出来菜单
	 * @return
	 */
	public PulldownMenuView getPopupMenuView(){
		PulldownMenuView menu = new PulldownMenuView(context);
		menu.setImageRes(imageRes);
		menu.setMenuText(texts);
		// menu.setLocation(Gravity.BOTTOM | Gravity.CENTER);
		menu.setAnimStyle(R.style.pulldown_in_out);
		menu.setBackground(R.drawable.navigation_bg);
		
		return menu;
	}
}
