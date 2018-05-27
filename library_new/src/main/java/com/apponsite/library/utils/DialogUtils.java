package com.apponsite.library.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

/**
 * @ClassName: DialogUtils
 * @Description: 提示框工具类
 * @date 2015年7月22日 上午11:20:01
 */
public class DialogUtils {
    public static MaterialDialog dialog;

    /**
     *
     *    true 可取消，false不可取消
     */

    public static void showLoadingProgress(Context context) {
        showLoadingProgress(context, true);
    }

    public static void showLoadingProgress(Context context, boolean cancelable) {
        showLoadingProgress(context, cancelable, "加载中...");
    }

    public static void showLoadingProgress(Context context, boolean cancelable, String showContent) {
        dialog = new MaterialDialog.Builder(context).theme(Theme.LIGHT).content(showContent).progress(true, 0).progressIndeterminateStyle(false).show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(cancelable);
		Window window = dialog.getWindow();
		View view = window.getDecorView();
		setViewFontSize(view,22);
    }

    public static void showLoadingProgress(Context context,boolean cancelable,String showContent,MaterialDialog.OnDismissListener dismissListener) {
        dialog = new MaterialDialog.Builder(context).theme(Theme.LIGHT).dismissListener(dismissListener).content(showContent).progress(true, 0).progressIndeterminateStyle(false).show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(cancelable);
		Window window = dialog.getWindow();
		View view = window.getDecorView();
		setViewFontSize(view,22);
    }

    public static void showDialog(Context context, boolean cancelable, String showtitle,
                                  String positivetext,MaterialDialog.SingleButtonCallback positivecallback) {
        dialog = new MaterialDialog.Builder(context).theme(Theme.LIGHT).title(showtitle).positiveText(positivetext).onPositive(positivecallback).show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(cancelable);
		Window window = dialog.getWindow();
		View view = window.getDecorView();
		setViewFontSize(view,22);
    }

    public static void showDialog(Context context, boolean cancelable, String showtitle,
                                  String positivetext,String negative,MaterialDialog.SingleButtonCallback positivecallback) {
        dialog = new MaterialDialog.Builder(context).theme(Theme.LIGHT).title(showtitle).positiveText(positivetext).onPositive(positivecallback).show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(cancelable);
        Window window = dialog.getWindow();
        View view = window.getDecorView();
        setViewFontSize(view,22);
    }




    public static void showDialog(Context context, boolean cancelable, String showtitle, String showContent,
                                  String positivetext, String negative, MaterialDialog.SingleButtonCallback positivecallback, MaterialDialog.SingleButtonCallback negativecallback) {
        dialog = new MaterialDialog.Builder(context).theme(Theme.LIGHT).title(showtitle).content(showContent).positiveText(positivetext).negativeText(negative).onPositive(positivecallback).onNegative(negativecallback).show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(cancelable);
		Window window = dialog.getWindow();
		View view = window.getDecorView();
		setViewFontSize(view,22);
    }

    /**
     * 隐藏加载进度条
     */
    public static void hintLoadingProgress() {
        if (dialog != null ) {
            dialog.dismiss();
            dialog = null;
        }
    }

	/**
	 * 设置Dialog字体大小
	 */
	public static void setViewFontSize(View view, int size)
	{
		if(view instanceof ViewGroup)
		{
			ViewGroup parent = (ViewGroup)view;
			int count = parent.getChildCount();
			for (int i = 0; i < count; i++)
			{
				setViewFontSize(parent.getChildAt(i),size);
			}
		}
		else if(view instanceof TextView){
			TextView textview = (TextView)view;
			textview.setTextSize(size);
		}
	}
}
