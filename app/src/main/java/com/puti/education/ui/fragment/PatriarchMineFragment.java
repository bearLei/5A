package com.puti.education.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.puti.education.R;
import com.puti.education.bean.ParentInfo;
import com.puti.education.bean.TeacherPersonInfo;
import com.puti.education.listener.BaseListener;
import com.puti.education.netFrame.netModel.CommonModel;
import com.puti.education.netFrame.netModel.PatriarchModel;
import com.puti.education.ui.BaseFragment;
import com.puti.education.ui.uiCommon.LoginActivity;
import com.puti.education.ui.uiCommon.MsgListActivity;
import com.puti.education.ui.uiPatriarch.AnonymityListActivity;
import com.puti.education.ui.uiPatriarch.ParentInfoActivity;
import com.puti.education.ui.uiStudent.PracticeListActivity;
import com.puti.education.ui.uiStudent.StudentInofActivity;
import com.puti.education.util.ConfigUtil;
import com.puti.education.util.Constant;
import com.puti.education.util.Key;
import com.puti.education.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/18 0018.
 *
 *  家长端 "我的"
 */

public class PatriarchMineFragment extends BaseFragment{


    @BindView(R.id.mine_name_tv)
    TextView mMineNameTv;
    @BindView(R.id.tvVersion)
    TextView mTvVersion;

    private ParentInfo parentInfo;

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_parent_mine_layout;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews(View view) {
        String version = "当前版本:V" + ConfigUtil.getAllVersion(this.getActivity());
        mTvVersion.setText(version);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void loadData() {
        getParentInfo();
    }

    @OnClick(R.id.mine_msg_center_tv)
    public void gotoMsgCenterClick(){
        Intent intent = new Intent(getActivity(), MsgListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.mine_person_info_tv)
    public void personalInfoClick(){
        Intent intent = new Intent(getActivity(), ParentInfoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.mine_train_tv)
    public void gotoTrainList(){
        Intent intent = new Intent(getActivity(), PracticeListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.mine_anonymity_tv)
    public void gotoPraticeList(){
        Intent intent = new Intent(getActivity(), AnonymityListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.mine_loginout_btn)
    public void logout(){
        loginOutRequest();
    }

    //显示教师基本信息
    private void showSimpleInfo(ParentInfo info){
        mMineNameTv.setText(info.name);
    }

    private void getParentInfo(){

        disLoading();
        PatriarchModel.getInstance().getParnetInfo(new BaseListener(ParentInfo.class){

            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                super.responseResult(infoObj, listObj, code, status);

                hideLoading();
                if (infoObj != null){
                    parentInfo = (ParentInfo)infoObj;
                    showSimpleInfo(parentInfo);
                }
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                hideLoading();
                ToastUtil.show(errorMessage == null ? Constant.REQUEST_FAILED_STR : errorMessage);
                super.requestFailed(status, code, errorMessage);

            }
        });
    }

    //注销
    private void loginOutRequest(){

        disLoading("正在注销...");

        CommonModel.getInstance().logout(new BaseListener(){

            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoading();
                        ConfigUtil.getInstance(getActivity()).clearSearchParams();//清空登录信息
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                },2000);

            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                hideLoading();
                ToastUtil.show(errorMessage == null ? Constant.REQUEST_FAILED_STR : errorMessage);
                getActivity().finish();
            }
        });
    }
}