package com.navigation.utility;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.WindowManager;

/**
 * 图像基本操作类
 * @Description: 图像基本操作类

 * @File: ImageUtility.java

 * @Package com.navigation.utility

 * @Author Hanyonglu

 * @Date 2012-7-30 上午10:52:49

 * @Version V1.0
 */
public class ImageUtility {
	/**
	 * 获取图片的尺寸
	 * @param context
	 * @param resource
	 * @return
	 */
	public static Point getImageDimension(Context context, int resource){
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), resource);
		Point point = new Point();
		point.x = bm.getWidth();
		point.y = bm.getHeight();
		bm.recycle();
		bm = null;
		
		return point;
	}
	
	/**
	 * 获取所有图片的最大的宽和高
	 * @param context
	 * @param imgRes
	 * @return
	 */
	public static Point getImageMaxDimension(Context context,int[] imgRes){
		final Point point = new Point();
		
		for (int i = 0, length = imgRes.length; i < length; i++){
			Bitmap tmp = BitmapFactory.decodeResource(
					context.getResources(), 
					imgRes[i]);
			int width = tmp.getWidth();
			int height = tmp.getHeight();
			tmp.recycle();
			tmp = null;
			
			if (point.x < width){
				point.x = width;
			}
			
			if (point.y < height){
				point.y = height;
			}
		}
		
		return point;
	}
}
