package cn.shineiot.buglyfixdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * @author wangs
 */
public class BugActivity extends Activity{
	private TextView tv;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = findViewById(R.id.textview);
		tv.setText("this is one bug!");
	}
}
