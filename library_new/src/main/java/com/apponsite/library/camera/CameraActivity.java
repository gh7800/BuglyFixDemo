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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.apponsite.library.R;
import com.apponsite.library.base.BaseActivity;
import com.apponsite.library.utils.ImageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author wangs
 */
public class CameraActivity extends BaseActivity implements View.OnClickListener {
	public final int CAMERACODE = 1000;
	private CameraView mCameraView;
	private Toolbar mToolbar;
	private Button mBtakePhoto, mFlash;
	private ImageView mPreview, mImgPreview;
	private RelativeLayout rl, rl_preview;
	private Button bt_cancle, bt_success;
	public String TAG = "tag";
	private File file;
	/**
	 * 是否开启闪光灯
	 */
	private boolean isFlash = false;
	private String path;
	private String srcPath;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Intent intent = new Intent(CameraActivity.this, PreviewActivity.class);
			intent.putExtra("path", path);
			startActivityForResult(intent, CAMERACODE);
			overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);

			/*mCameraView.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
            rl_preview.setVisibility(View.VISIBLE);
            GlideUtil.loadRectangleCorners(path, mImgPreview);*/
		}

	};

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_camera;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {

		srcPath = getIntent().getStringExtra("srcpath");
		path = getIntent().getStringExtra("path");
		initView();
		setupToolbar(mToolbar,"拍摄照片");
	}

	private CameraView.Callback mCallback
			= new CameraView.Callback() {

		@Override
		public void onCameraOpened(CameraView cameraView) {
			Log.e(TAG, "onCameraOpened");
		}

		@Override
		public void onCameraClosed(CameraView cameraView) {
			Log.e(TAG, "onCameraClosed");
		}

		@Override
		public void onPictureTaken(CameraView cameraView, final byte[] data) {

			mHandler.post(new Runnable() {
				@Override
				public void run() {
//                    file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
//                            System.currentTimeMillis()+".png");
//                    srcPath = FileManager.UPLOAD_iMAGE_ROOT_PATH + File.separator
//                            + System.currentTimeMillis() + ".png";
					file = new File(srcPath);
					OutputStream os = null;
					try {
						os = new FileOutputStream(file);
						os.write(data);
						os.close();

//                        path = FileManager.UPLOAD_iMAGE_ROOT_PATH + File.separator + "img_"
//                                + System.currentTimeMillis() + ".png";
						ImageUtil.compressImage(CameraActivity.this, srcPath, path, 360, 450);

						mHandler.sendEmptyMessage(1);

					} catch (IOException e) {
						Log.w(TAG, "Cannot write to " + file, e);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (os != null) {
							try {
								os.close();
							} catch (IOException e) {
								Log.e("tag", "" + e.getMessage());
							}
						}
					}
				}
			});
		}

	};

	private void initView() {
		mToolbar = (Toolbar)findViewById(R.id.toolbar);
		mCameraView = (CameraView) findViewById(R.id.camera);
		mBtakePhoto = (Button) findViewById(R.id.takephoto);
		mPreview = (ImageView) findViewById(R.id.preview);
		mImgPreview = (ImageView) findViewById(R.id.img_preview);
		mFlash = (Button) findViewById(R.id.flash);
		rl = (RelativeLayout) findViewById(R.id.rl);
		rl_preview = (RelativeLayout) findViewById(R.id.rl_preview);
		bt_cancle = (Button) findViewById(R.id.cancle);
		bt_success = (Button) findViewById(R.id.success);

		mBtakePhoto.setOnClickListener(this);
		mPreview.setOnClickListener(this);
		mFlash.setOnClickListener(this);
		bt_success.setOnClickListener(this);
		bt_cancle.setOnClickListener(this);

		if (mCameraView != null) {
			mCameraView.addCallback(mCallback);
		}
		//默认关闭闪光灯
		mCameraView.setFlash(CameraView.FLASH_OFF);
		mCameraView.setAutoFocus(true);
	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.preview) {
		} else if (i == R.id.takephoto) {
			if (mCameraView != null) {
				Toast.makeText(mCameraView.getContext(), "正在拍照", Toast.LENGTH_SHORT)
						.show();
				mCameraView.takePicture();
			}

		} else if (i == R.id.flash) {
			if (!isFlash) {
				isFlash = true;
				Toast.makeText(CameraActivity.this, "闪光灯已打开", Toast.LENGTH_SHORT).show();
				mCameraView.setFlash(CameraView.FLASH_ON);
				mFlash.setBackgroundResource(R.drawable.ic_flash_on);
			} else {
				isFlash = false;
				Toast.makeText(CameraActivity.this, "闪光灯已关闭", Toast.LENGTH_SHORT).show();
				mCameraView.setFlash(CameraView.FLASH_OFF);
				mFlash.setBackgroundResource(R.drawable.ic_flash_off);
			}

		} else if (i == R.id.cancle) {
			rl_preview.setVisibility(View.GONE);
			mCameraView.setVisibility(View.VISIBLE);
			rl.setVisibility(View.VISIBLE);

			if (null != file) {
				file.delete();
			}
			if (null != path) {
				File file = new File(path);
				file.delete();
			}

			Log.e("tag", mCameraView.isCameraOpened() + "");
			if (!mCameraView.isCameraOpened()) {
				mCameraView.start();
				mCameraView.setAutoFocus(true);
			}


		} else if (i == R.id.success) {
			finish();
			Intent intent = new Intent();
			intent.putExtra("path", file.getAbsolutePath());
			setResult(Activity.RESULT_OK, intent);

		} else {
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK) {
			return;
		} else if (requestCode == CAMERACODE) {
			boolean isSuccess = data.getBooleanExtra("result", false);
			if (isSuccess) {
				Intent intent = new Intent();
				intent.putExtra("path",path);
				setResult(Activity.RESULT_OK,intent);
				finish();
			}
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	protected void onResume() {
		mCameraView.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		mCameraView.stop();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (null != mHandler) {
			mHandler.removeCallbacksAndMessages(null);
		}
	}

}
