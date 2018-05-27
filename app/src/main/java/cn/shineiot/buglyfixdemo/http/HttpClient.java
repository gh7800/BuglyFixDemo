package cn.shineiot.buglyfixdemo.http;

import android.content.Context;

import com.apponsite.library.base.BaseApplication;
import com.apponsite.library.base.BaseResponse;
import com.apponsite.library.manager.HttpManager;
import com.apponsite.library.utils.DialogUtils;
import com.apponsite.library.utils.HttpUtils;
import com.apponsite.library.utils.ToastUtils;

import cn.shineiot.buglyfixdemo.App;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author wangs
 */
public class HttpClient {

	private static HttpClient sInstance;
	private HttpService httpservice;

	/**
	 * 获取单例
	 *
	 * @param
	 * @return 实例
	 */
	public static HttpClient getInstance(Context context) {
		if (!HttpUtils.isNetworkConnected(App.context())) {
			DialogUtils.hintLoadingProgress();

			ToastUtils.showToast(BaseApplication.context(), "没有网络，请检查网络设置！");

		}

		if (sInstance == null) {
			sInstance = new HttpClient();

		}
		return sInstance;
	}

	private HttpClient() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://testxjwy.shineiot.cn/")
				.client(HttpManager.getOkHttpClient())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();

		httpservice = retrofit.create(HttpService.class);
	}
	public void getUser(Subscriber<BaseResponse> subscriber){
		httpservice.getUser("test-token")
				.subscribeOn(Schedulers.io())
				.unsubscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(subscriber);
	}
}
