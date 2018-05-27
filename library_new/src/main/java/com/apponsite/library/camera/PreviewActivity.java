/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apponsite.library.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.apponsite.library.R;
import com.apponsite.library.base.BaseActivity;
import com.apponsite.library.utils.GlideUtil;

import java.io.File;

/**
 * @author wangs
 * @date 2018/3/4
 */

public class PreviewActivity extends BaseActivity implements View.OnClickListener {
	private Toolbar mToolbar;
	private ImageView mImageView;
	private Button btCancle, btSuccess;
	private Context mContext;
	private String path;

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_preview;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		mContext = this;
		initView();
		setupToolbar(mToolbar,"预览照片");
	}

	private void initView() {
		mToolbar = (Toolbar)findViewById(R.id.toolbar);
		mImageView = (ImageView) findViewById(R.id.img_preview);
		btCancle = (Button) findViewById(R.id.cancle);
		btSuccess = (Button) findViewById(R.id.success);
		btSuccess.setOnClickListener(this);
		btCancle.setOnClickListener(this);

		path = getIntent().getStringExtra("path");
		GlideUtil.loadImg_NoCache(path, mImageView);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		int i = v.getId();
		if (i == R.id.cancle) {
			File file = new File(path);
			file.delete();

			intent.putExtra("result", false);
			setResult(Activity.RESULT_OK, intent);
			finish();

		} else if (i == R.id.success) {
			intent.putExtra("result", true);
			setResult(Activity.RESULT_OK, intent);
			finish();

		} else {
		}
	}

	@Override
	public void onBackPressed() {

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return  false;
		}
		return super.onKeyDown(keyCode, event);
	}
}
