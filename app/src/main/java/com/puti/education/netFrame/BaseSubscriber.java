package com.puti.education.netFrame;


import android.content.Intent;

import com.alibaba.fastjson.JSONException;
import com.puti.education.App;
import com.puti.education.listener.BaseListener;
import com.puti.education.netFrame.response.ResponseInfo;
import com.puti.education.ui.uiCommon.LoginActivity;

import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public class BaseSubscriber extends Subscriber<ResponseInfo>{

    BaseListener baseListener;

    public BaseSubscriber(BaseListener baseListener){
        this.baseListener = baseListener;
    }

    //子线程回调
    @Override
    public void onStart() {
        this.baseListener.onstart();
    }

    @Override
    public void onCompleted() {
        this.baseListener.onCompleted();
    }

    //主线程回调
    @Override
    public void onNext(ResponseInfo responseInfo) {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException){
            this.baseListener.requestFailed(false, -1, "请求超时！");
        }else if (e instanceof JSONException){
            this.baseListener.requestFailed(false, -1, "json解析失败了！");
        }else if (e instanceof HttpException){
            int httpcode = ((HttpException) e).code();
            this.baseListener.requestFailed(false, -1, "网络请求失败" + (httpcode));
        }else{
            this.baseListener.requestFailed(false, -1, "网络出错！");
        }

    }
}
