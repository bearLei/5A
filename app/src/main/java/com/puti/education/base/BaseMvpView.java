package com.puti.education.base;

/**
 * Created by lenovo on 2017/12/30.
 */

public interface BaseMvpView<T> {
    void showLoading();
    void hideLoading();
    void showErrorView();
    void showEmptyView();
}
