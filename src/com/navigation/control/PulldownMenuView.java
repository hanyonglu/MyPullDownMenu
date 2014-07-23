package com.navigation.control;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.navigation.adapter.PulldownMenuAdapter;
import com.navigation.constant.ConstantCategoryMenu;
import com.navigation.utility.ImageUtility;

/**
 * 下拉导航选择菜单View
 * @Description: 下拉导航选择菜单View

 * @File: PulldownMenuView.java

 * @Package com.navigation.control

 * @Author Hanyonglu

 * @Date 2012-7-29 上午11:03:40

 * @Version V1.0
 */
public class PulldownMenuView {
	private Context context = null;
	// PopupWindow对象
	private PopupWindow popupWindow = null;
	// 菜单图片资源
	private int[] menuImageRes = new int[0];
	// 菜单文字项
	private String[] menuTexts = new String[0];
	// 菜单项背景
	private int menuBackground = 0;
	// 显示或隐藏菜单的动画样式
	private int menuAnimStyle = 0;
	// 文字大小
	private float menuTextSize = -1;
	// 文字颜色
	private int menuTextColor = -1;
	// 菜单项选中的效果
	private int menuSelector = -1;
	// 菜单项宽度
	private int menuWidth = 0;
	// 菜单项高度
	private int menuHeight = 0;
	// 存放菜单项的GridView对象
	private GridView menuGridView;
	// 菜单文字的最大长度，超过最大长度则显会显示"……"
	private int menuMaxStrLength = 4;
	// 是否对过长字符串采取优化，配合menuMaxStrLength工作
	private boolean isOptimizeText = true;
	// 菜单显示位置的相对于View
	private View anchorView = null;
	// 当前选中项
	private String currentItem = null;
	// 菜单项点击事件
	private OnMenuItemClickListener menuItemListener;
	// 显示位置，默认是从顶部显示出来
	// private int menuLocation = Gravity.BOTTOM | Gravity.CENTER;
	// 文字相对于图片的排列方式，默认是文字在图片下方
	private PulldownMenuAlign menuAlign = PulldownMenuAlign.TEXT_BOTTOM;
	// 菜单项集合
	private ArrayList<PulldownMenuItem> menuMenuItems = 
			new ArrayList<PulldownMenuItem>();
	
	/**
	 * 默认的构造器
	 */
	public PulldownMenuView() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 带Context的构造器
	 * @param context
	 */
	public PulldownMenuView(Context context) {
		// TODO Auto-generated constructor stub
		if (null == context){
			throw new IllegalArgumentException();
		}
		
		this.context = context;
	}
	
	/**
	 * 带多参的构造器
	 * @param context
	 * @param menuImageRes
	 * @param menuTexts
	 * @param menuBackground
	 * @param menuAnimStyle
	 */
	public PulldownMenuView(Context context,int[] menuImageRes,
							String[] menuTexts,int menuBackground,
							int menuAnimStyle){
		if (null == context){
			throw new IllegalArgumentException();
		}
		
		this.context = context;
		this.menuImageRes = menuImageRes;
		this.menuTexts = menuTexts;
		this.menuBackground = menuBackground;
		this.menuAnimStyle = menuAnimStyle;
	}
	
	/**
	 * 设置图片资源
	 * @param imageRes
	 */
	public void setImageRes(int[] imageRes){
		if (null != imageRes){
			menuImageRes = imageRes;
		}
	}
	
	/**
	 * 设置菜单背景
	 * @param backgroundRes
	 */
	public void setBackground(int backgroundRes){
		menuBackground = backgroundRes;
	}
	
	/**
	 * 设置菜单项的文字
	 * @param txtRes 资源数组
	 */
	public void setMenuText(int[] textRes){
		if (null == textRes){
			return;
		}
		
		final Resources res = context.getResources();
		final int length = textRes.length;
		menuTexts = new String[length];
		
		for (int i = 0; i < length; i++){
			menuTexts[i] = res.getString(textRes[i]);
		}
	}
	
	/**
	 * 设置菜单项的文字
	 * @param txtRes
	 */
	public void setMenuText(String[] texts){
		menuTexts = texts;
	}
	
	/**
	 * 设置文字大小
	 * @param size
	 */
	public void setMenuTextSize(float size){
		menuTextSize = size;
	}
	
	/**
	 * 设置文字颜色
	 * @param color
	 */
	public void setMenuTextColor(int color){
		menuTextColor = color;
	}
	
	/**
	 * 设置文本相对图片的排列方式
	 * @param align
	 */
	public void setMenuAlign(PulldownMenuAlign align){
		menuAlign = align;
	}
	
	/**
	 * 设置允许文本的最大长度
	 * @param length
	 */
	public void setMaxTextLength(int length){
		menuMaxStrLength = length;
	}
	
	/**
	 * 设置是否对过长文本进行优化
	 * @param isOptimize
	 */
	public void isOptimizeText(boolean isOptimize){
		isOptimizeText = isOptimize;
	}
	
	/**
	 * 设置菜单动画
	 * @param animStyle
	 */
	public void setAnimStyle(int animStyle){
		menuAnimStyle = animStyle;
	}
	
	/**
	 * 设置菜单的宽度
	 * @param width
	 */
	public void setWidth(int width){
		menuWidth = width;
	}
	
	/**
	 * 设置菜单的高度
	 * @param height
	 */
	public void setHeight(int height){
		menuHeight = height;
	}
	
	/**
	 * 设置菜单显示的位置
	 * @param location
	 */
//	public void setLocation(int location){
//		menuLocation = location;
//	}
	
	/**
	 * 设置菜单被项被选中的效果
	 * @param selector
	 */
	public void setSelector(int selector){
		menuSelector = selector;
	}
	
	/**
	 * 设置菜单项的GridView
	 * @param gridView
	 */
	public void setMenuGridView(GridView gridView){
		menuGridView = gridView;
	}
	
	/**
	 * 设置菜单的显示位置相对于View
	 * @param anchor
	 */
	public void setAnchorView(View anchor){
		anchorView = anchor;
	}
	
	/**
	 * 设置当前选中项
	 * @param currentItem
	 */
	public void setCurrentItem(String currentItem){
		this.currentItem = currentItem;
	}
	
	/**
	 * 显示菜单
	 * @return 显示成功返回true, 失败返回false
	 */
	public boolean show(){
		if (hide()){
			return false;
		}
		
		// final Context context = context;
		final int length = menuImageRes.length;
		final int txtLength = menuTexts.length;
		Point point = new Point();
		
		if (length != 0 && txtLength != 0){
			Point p1 = getTextMaxDimenstion(menuTexts);
			Point p2 = ImageUtility.getImageMaxDimension(context,menuImageRes);
			
			switch (menuAlign){
			case TEXT_BOTTOM:
			case TEXT_TOP:
				point.x = Math.max(p1.x, p2.x);
				point.y = p1.y + p2.y;
				break;
			case TEXT_LEFT:
			case TEXT_RIGHT:
				point.x = p1.x + p2.x;
				point.y = Math.max(p1.y, p2.y);
				break;
			}
		} else{
			if (length != 0){
				point = ImageUtility.getImageMaxDimension(context,menuImageRes);
			}
			else if (txtLength != 0){
				point = getTextMaxDimenstion(menuTexts);
			}
		}
		
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int width = menuWidth == 0 ? metrics.widthPixels : menuWidth;
		float density = metrics.density;
		int imgWidth = point.x;
		int height = point.y + 20;
		// 除去5dp的间距一行所能摆放图片的个数
		int columns = (int) ((width - 5 * density) / (imgWidth + 5 * density));
		
		int leng = length != 0 ? length : txtLength;
		int rows = columns == 0 ? 0 : leng / columns;
		
		if (columns * rows < leng){
			rows += 1;
		}
		
		final LinearLayout layout = initLayout(context);
		GridView gridView = menuGridView;
		
		if (null == gridView){
			gridView = getMenuGirdView(context, columns);
		} else{
			setMenuListener(gridView);
		}
		
		layout.addView(
				gridView, new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, 
					ViewGroup.LayoutParams.MATCH_PARENT
					)
				);
		
		// TODO 对高度进行修正
		int h = 0;
		
		if (menuAlign == PulldownMenuAlign.TEXT_LEFT || menuAlign == PulldownMenuAlign.TEXT_RIGHT){
			h = (int) (height * rows + 5 * density);
		} else if (menuAlign == PulldownMenuAlign.TEXT_BOTTOM || menuAlign == PulldownMenuAlign.TEXT_TOP){
			h = (int) ((height + 5 * density) * rows);
		}
		
		if (txtLength != 0){
			h += 6 * density;
		}
		
		popupWindow = new PopupWindow(context);
		popupWindow.setWidth(width);
		popupWindow.setHeight(menuHeight == 0 ? h : menuHeight);
		popupWindow.setContentView(layout);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setTouchable(true);
		
		// 设置背景为null，按返回键PopupWindow就会隐藏
		popupWindow.setBackgroundDrawable(null);
		
		if (menuAnimStyle != 0){
			popupWindow.setAnimationStyle(menuAnimStyle);
		}
		
		// popupWindow.showAtLocation(layout, menuLocation, 0, 0);
		popupWindow.showAsDropDown(anchorView);
		
		return true;
	}
	
	/**
	 * 初始化LinearLayout
	 * @param context
	 * @return
	 */
	private LinearLayout initLayout(Context context){
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setFadingEdgeLength(0);
		layout.setGravity(Gravity.CENTER);
		// 设置整个布局的边距
		// layout.setPadding(0, 0, 0, 0);
		
		layout.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event){
				if (event.getAction() == MotionEvent.ACTION_DOWN){
					hide();
				}
				
				return false;
			}
		});
		
		return layout;
	}
	
	/**
	 * 初始化数据，将数据加载到对应的View中
	 */
	private void initData(){
		PulldownMenuItem item = new PulldownMenuItem(context);
		item.setMenuAlign(menuAlign);
		item.setMenuTextColor(menuTextColor);
		item.setMenuTextSize(menuTextSize);
		int txtLength = menuTexts.length;
		int imgLength = menuImageRes.length;
		
		if (txtLength != 0 && imgLength != 0){
			for (int i = 0; i < imgLength; i++){
				PulldownMenuItem menuItem = new PulldownMenuItem(context, item);
				
				if(!currentItem.equals(menuTexts[i])){
					menuItem.setImageRes(menuImageRes[i]);
					menuItem.setMenuText(menuTexts[i]);
				}else{
					menuItem.setMenuText(menuTexts[i]);
					menuItem.setMenuTextColor(Color.parseColor("#4FA7F9"));
					menuItem.setImageRes(ConstantCategoryMenu.newsImageResPress[i]);
				}
				
				menuMenuItems.add(menuItem);
			}
		} else{
			if (txtLength != 0){
				for (int i = 0; i < txtLength; i++){
					PulldownMenuItem menuItem = new PulldownMenuItem(context, item);
					menuItem.setMenuText(menuTexts[i]);
					menuMenuItems.add(menuItem);
				}
			}else if (imgLength != 0){
				for (int i = 0; i < imgLength; i++){
					PulldownMenuItem menuItem = new PulldownMenuItem(context, item);
					menuItem.setImageRes(menuImageRes[i]);
					menuMenuItems.add(menuItem);
				}
			}
		}
	}
	
	/**
	 * 初始化菜单内容
	 * @param context
	 * @param columns 菜单的列数
	 * @return
	 */
	private GridView getMenuGirdView(Context context, int columns){
		if (menuMenuItems.isEmpty()){
			initData();
		}
		
		if (null != menuGridView){
			return menuGridView;
		}
		
		GridView gridView = new GridView(context);
		gridView.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 
				ViewGroup.LayoutParams.MATCH_PARENT));
		// 设置事件监听器
		gridView.setAdapter(new PulldownMenuAdapter(menuMenuItems));
		gridView.setVerticalSpacing(1);
		gridView.setNumColumns(columns);
		gridView.setGravity(Gravity.CENTER);
		gridView.setVerticalScrollBarEnabled(false);
		
		if (menuBackground != 0){
			gridView.setBackgroundResource(menuBackground);
		}
		
		if (menuSelector != -1){
			gridView.setSelector(menuSelector);
		}
		
		gridView.setHorizontalScrollBarEnabled(false);
		setMenuListener(gridView);
		
		return gridView;
	}
	
	/**
	 * 注册菜单项监听事件
	 * @param gridView
	 */
	private void setMenuListener(GridView gridView){
		if (null == gridView.getOnItemClickListener()){
			gridView.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(
						AdapterView<?> parent, 
						View view,
						int position, 
						long id){
					if (null != menuItemListener){
						menuItemListener.onMenuItemClick(parent, view, position);
					}
					
					hide();
				}
			});
		}
		
		// 按返回键或菜单键隐藏菜单
		gridView.setOnKeyListener(new OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event){
				if (event.getAction() == KeyEvent.ACTION_DOWN){
					switch (keyCode){
					case KeyEvent.KEYCODE_BACK:
					case KeyEvent.KEYCODE_MENU:
						hide();
						break;
					}
				}
				
				return false;
			}
		});
	}
	
	/**
	 * 获取并计算文本的最大长度
	 * @param txts
	 * @return
	 */
	private Point getTextMaxDimenstion(String[] txts){
		final Point point = new Point();
		final Rect bounds = new Rect();
		final Paint paint = new Paint();
		float size = menuTextSize != -1 ? menuTextSize : context.getResources().getDisplayMetrics().density * 16;
		paint.setTextSize(size);
		paint.setColor(menuTextColor != -1 ? menuTextColor : Color.BLACK);
		
		if (isOptimizeText){
			for (int i = 0, length = txts.length; i < length; i++){
				String str = txts[i];
				
				if (null == str){
					str = "";
				} else if (str.length() > menuMaxStrLength){
					// 对字符串长度进行控制
					str = new StringBuilder()
						  .append(str.substring(0, menuMaxStrLength))
						  .append("……").toString();
				}
				
				txts[i] = str;
				paint.getTextBounds(str, 0, str.length(), bounds);
				compareDimension(point, bounds.width(), bounds.height());
			}
		} else{
			for (int i = 0, length = txts.length; i < length; i++){
				String str = txts[i];
				
				if (null == str){
					str = "";
				}
				
				txts[i] = str;
				paint.getTextBounds(str, 0, str.length(), bounds);
				compareDimension(point, bounds.width(), bounds.height());
			}
		}
		
		return point;
	}
	
	/**
	 * 比较并改变最大尺寸
	 * @param point 保存最大尺寸的对象
	 * @param width 宽
	 * @param height 高
	 */
	private void compareDimension(Point point, int width, int height){
		if (point.x < width){
			point.x = width;
		}
		
		if (point.y < height){
			point.y = height;
		}
	}
	
	/**
	 * 隐藏菜单
	 * @return 隐藏成功返回true，失败返回false
	 */
	public boolean hide(){
		if (null != popupWindow && popupWindow.isShowing()){
			popupWindow.dismiss();
			popupWindow = null;
			
			if (null != menuItemListener){
				menuItemListener.hideMenu();
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 将菜单清空
	 */
	public void dismiss(){
		menuMenuItems.clear();
		menuGridView = null;
		menuTexts = new String[0];
		menuImageRes = new int[0];
		menuWidth = 0;
		menuHeight = 0;
	}
	
	/**
	 * 释放PopupMenu资源
	 */
	public void releasePopupMenuView(){
		this.dismiss();
		this.hide();
	}
	
	/**
	 * 设置菜单项被选中监听器
	 * @param listener
	 */
	public void setOnMenuItemClickListener(OnMenuItemClickListener listener){
		menuItemListener = listener;
	}
	
	/**
	 * 菜单项目选中监听器
	 */
	public interface OnMenuItemClickListener{
		/**
		 * 菜单项被点击调用的方法
		 * @param parent
		 * @param view
		 * @param position
		 */
		public void onMenuItemClick(AdapterView<?> parent, View view, int position);
		
		/**
		 * 菜单隐藏调用的方法
		 */
		public void hideMenu();
	}
}
