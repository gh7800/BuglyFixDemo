package com.apponsite.library.base;

import android.content.Context;

public class CommonPresenter extends BasePresenter<BaseView> {

    /**
     *
     */
    private Context mContext = null;


    public CommonPresenter(Context context) {

        mContext = context;
    }

}

