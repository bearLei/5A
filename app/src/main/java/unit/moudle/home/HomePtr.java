package unit.moudle.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.puti.education.base.BaseMvpPtr;
import com.puti.education.base.holder.BaseHolder;
import com.puti.education.listener.BaseListener;
import com.puti.education.netFrame.response.PageInfo;
import com.puti.education.util.ToastUtil;
import com.puti.education.util.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import unit.api.PutiCommonModel;
import unit.api.PutiTeacherModel;
import unit.entity.ClaRecordEntity;
import unit.entity.Event;
import unit.entity.PushInfo;
import unit.entity.PutiEvents;
import unit.entity.QuesInfo;
import unit.eventbus.AvatarChangeEvent;
import unit.eventbus.PutiEventBus;
import unit.eventbus.PutiMsgNotice;
import unit.eventbus.TokenErrorEvent;
import unit.moudle.home.holder.HomeCountHolder;
import unit.moudle.home.holder.HomeFeedBackHolder;
import unit.moudle.home.holder.HomeHeadHolder;
import unit.moudle.home.holder.HomePowerHolder;
import unit.moudle.home.holder.HomeToolHolder;
import unit.sp.DataStorage;
import unit.util.UserInfoUtils;

/**
 * Created by lei on 2018/6/5.
 * 首页 Ptr
 */

public class HomePtr implements BaseMvpPtr {

    private static final int Msg_event = 1;//事件通知
    private static final int Msg_Report = 2;//家长举报
    private static final int Msg_Ques = 3;//问卷

    private Context mContext;
    private HomeView mView;

    private HomeHeadHolder mHeadHolder;//顶部个人信息
    private HomeCountHolder mCountHolder;//个人统计信息
    private HomePowerHolder mPowerHolder;//能力栏
    private HomeFeedBackHolder mFeedBackHolder;//有奖反馈
    private HomeToolHolder mToolHolder;//工具栏
    public HomePtr(Context mContext, HomeView mView) {
        this.mContext = mContext;
        this.mView = mView;
        if (!PutiEventBus.g().isRegistered(this)){
            PutiEventBus.g().register(this);
        }
    }

    @Override
    public void star() {
        initHeadHolder();
        initCountHolder();
        initPowerHolder();
        initFeedBackHolder();
        initToolHolder();
    }

    @Override
    public void stop() {

    }
    public void onResume(){
        queryTeaClassRecord();
//        queryQues();
    }

    //初始化头部个人信息Holder
    private void initHeadHolder(){
        if (mHeadHolder == null){
            mHeadHolder = new HomeHeadHolder(mContext);
            mHeadHolder.setData(true);
        }
        View rootView = mHeadHolder.getRootView();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewUtils.dip2px(mContext,48));
        rootView.setLayoutParams(params);
        mView.addHeadLayout(rootView);
    }

    //初始化统计信息Holder
    private void initCountHolder(){
        if (mCountHolder == null){
            mCountHolder = new HomeCountHolder(mContext);
            mCountHolder.queryData();
        }
        mView.addCountLayout(operateSize(mCountHolder));
    }

    //初始化能力栏Holder
    private void initPowerHolder(){
        if (mPowerHolder == null){
            mPowerHolder = new HomePowerHolder(mContext);
            mPowerHolder.setData(true);
        }
        mView.addPowerLayout(operateSize(mPowerHolder));
    }

    //初始化有奖反馈Holder
    private void initFeedBackHolder(){
        if (mFeedBackHolder == null){
            mFeedBackHolder = new HomeFeedBackHolder(mContext);
            mFeedBackHolder.setData(true);
        }

        mView.addFeedBackLayout(operateSize(mFeedBackHolder));
    }

    //初始化工具栏
    private void initToolHolder(){
        if (mToolHolder == null){
            mToolHolder = new HomeToolHolder(mContext);
            mToolHolder.setData(true);
        }
        mView.addToolLayout(operateSize(mToolHolder));
    }

    private View operateSize(BaseHolder holder){
        View rootView = holder.getRootView();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(params);
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void on3EventMainThread(AvatarChangeEvent event) {
        if (mHeadHolder != null){
            mHeadHolder.updateAvatar();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void on3EventMainThread(PutiMsgNotice event) {
        int category = event.getCategory();
        switch (category){
            case Msg_event:
                mHeadHolder.setRedDog(true);
                DataStorage.putUserHasNotice(true);
                break;
            case Msg_Ques:
//                mPowerHolder.setmQuesHolderRedDog(true);
                DataStorage.putUserQues(true);
                mHeadHolder.setRedDog(true);
                DataStorage.putUserHasNotice(true);
                break;
            case Msg_Report:
                mPowerHolder.setmReportHolderRedDog(true);
                DataStorage.putUserHasReport(true);
                mHeadHolder.setRedDog(true);
                DataStorage.putUserHasNotice(true);
                break;
        }
    }

    //仅仅是做红点展示
        private void queryTeaClassRecord(){
            PutiTeacherModel.getInstance().queryTeacherRecords(UserInfoUtils.getUid(),new BaseListener(ClaRecordEntity.class){
                @Override
                public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                    ClaRecordEntity entity = (ClaRecordEntity) infoObj;
                    List<ClaRecordEntity.ClaHeadRecord> claHeadRecords = entity.getClaHeadRecords();
                    if (claHeadRecords.size() > 0){
                        queryEvent(claHeadRecords.get(0).getClassUID());
                    }
                }

                @Override
                public void requestFailed(boolean status, int code, String errorMessage) {
                    mView.hideLoading();
                    mView.showErrorView();
                }
            });
        }
        public void queryEvent(String classUid){
            PutiCommonModel.getInstance().queryEvent(classUid,1,1, Integer.MAX_VALUE,new BaseListener(PutiEvents.class){
                @Override
                public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                    PutiEvents events = (PutiEvents) infoObj;
                    ArrayList<Event> eventList = (ArrayList<Event>) events.getEvents();
                    if (eventList.size() > 0){
                        mPowerHolder.setEventSurewHolderRedDog(true);
                    }
                }

                @Override
                public void requestFailed(boolean status, int code, String errorMessage) {
                    mView.hideLoading();
                    mView.showErrorView();
                    mPowerHolder.setEventSurewHolderRedDog(false);
                }
            });
        }


//    private void queryQues() {
//        PutiTeacherModel.getInstance().getQuesList(new BaseListener(QuesInfo.class) {
//            @Override
//            public void responseListResult(Object infoObj, Object listObj, PageInfo pageInfo, int code, boolean status) {
//                ArrayList<QuesInfo> list = (ArrayList<QuesInfo>) listObj;
//                for (int i = 0; i < list.size(); i++) {
//                    if (list.get(i).getStatus() == 0){
//                        mPowerHolder.setmQuesHolderRedDog(true);
//                    }
//                }
//            }
//
//            @Override
//            public void requestFailed(boolean status, int code, String errorMessage) {
//            }
//        });
//    }

}
