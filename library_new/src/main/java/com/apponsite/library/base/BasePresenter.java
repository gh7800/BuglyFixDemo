package com.apponsite.library.base;

public abstract class BasePresenter<T> {
    public T mView;//View
//    protected Subscription mSubscription;
//    protected CompositeSubscription mCompositeSubscription;//使用compositesubcription 管理Subcription


    public void attachView(T view) {


        this.mView = view;
//        if (mCompositeSubscription == null)
//        mCompositeSubscription = new CompositeSubscription();
   }


    public void detachView() {
//        if (mCompositeSubscription == null)
//        mCompositeSubscription.unsubscribe();//取消订阅
        mView = null;
    }

    public boolean isViewAttached(){
        return mView!= null;
    }


}

