package cn.shineiot.buglyfixdemo;


import android.content.Context;
import android.support.multidex.MultiDex;

import com.apponsite.library.base.BaseApplication;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * @author wangs
 */
public class App extends BaseApplication {

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		// you must install multiDex whatever tinker is installed!
		MultiDex.install(base);

		// 安装tinker
		//如果不继承tinkerApplication，不需要带this参数
		Beta.installTinker();
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
		// 调试时，将第三个参数改为true
		Bugly.init(getApplicationContext(), "36d59beffd", true);

	}
}
