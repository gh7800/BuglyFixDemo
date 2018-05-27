package cn.shineiot.buglyfixdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apponsite.library.base.BaseResponse;

import cn.shineiot.buglyfixdemo.http.HttpClient;
import rx.Subscriber;

/**
 * @author wangs
 */
public class MainActivity extends AppCompatActivity {
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = findViewById(R.id.textview);
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				HttpClient.getInstance(MainActivity.this).getUser(new Subscriber<BaseResponse>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onNext(BaseResponse response) {
						if(response.getSuccess()) {
							tv.setText("base-1.0.7.2");
							Intent intent = new Intent(MainActivity.this, BugActivity.class);
							startActivity(intent);
						}
					}
				});
			}
		});
	}

}
