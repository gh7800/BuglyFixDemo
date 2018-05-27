package cn.shineiot.buglyfixdemo.http;


import com.apponsite.library.base.BaseResponse;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * @author wangs
 */
public interface HttpService {
	@GET("api/user")
	Observable<BaseResponse> getUser(@Header("token")String token);
}
