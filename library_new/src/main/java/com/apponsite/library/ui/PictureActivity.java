package com.apponsite.library.ui;

import android.os.Bundle;
import android.util.Log;

import com.apponsite.library.R;
import com.apponsite.library.base.BaseActivity;
import com.apponsite.library.utils.GlideUtil;

import uk.co.senab.photoview.PhotoView;


public class PictureActivity extends BaseActivity {

	private String path;


	@Override
	protected int provideLayoutId() {
		return R.layout.activity_picture;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {


		path = getIntent().getStringExtra("filepath");
		Log.e("tag", "" + path);

		PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);


		GlideUtil.loadImg(path, photoView);

//        if (path.contains("http://")) {

//            ivHeader.setImageURI(Uri.parse(path));
//            mPhotoDraweeView.setPhotoUri(Uri.parse(path));

//        } else {
//            ivHeader.setImageURI(Uri.parse("file://"+path));

//            mPhotoDraweeView.setPhotoUri(Uri.parse("file://"+path));
//        }

	}


}