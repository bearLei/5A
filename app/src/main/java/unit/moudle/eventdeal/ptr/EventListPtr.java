package unit.moudle.eventdeal.ptr;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.view.View;

import com.puti.education.base.BaseMvpPtr;
import com.puti.education.listener.BaseListener;
import com.puti.education.netFrame.response.PageInfo;
import com.puti.education.util.ToastUtil;
import com.puti.education.widget.CommonDropView;

import java.util.ArrayList;
import java.util.List;

import unit.api.PutiCommonModel;
import unit.api.PutiTeacherModel;
import unit.entity.ClaRecordEntity;
import unit.entity.ClassSimple;
import unit.entity.Event;
import unit.entity.PutiEvents;
import unit.moudle.eventdeal.view.EventListView;
import unit.util.UserInfoUtils;

/**
 * Created by lei on 2018/6/17.
 */

public class EventListPtr implements BaseMvpPtr {

    private static final String ImportEvent = "严重事件";

    private Context mContext;
    private EventListView mView;

    private ArrayList<ClaRecordEntity.ClaHeadRecord> mClassList;
    private String mClassUid;
    public EventListPtr(Context mContext, EventListView mView) {
        this.mContext = mContext;
        this.mView = mView;
        mClassList = new ArrayList<>();
    }

    @Override
    public void star() {
        queryTeaClassRecord();
    }

    @Override
    public void stop() {

    }
    private void queryTeaClassRecord(){
        PutiTeacherModel.getInstance().queryTeacherRecords(UserInfoUtils.getUid(),new BaseListener(ClaRecordEntity.class){
            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                ClaRecordEntity entity = (ClaRecordEntity) infoObj;
                List<ClaRecordEntity.ClaHeadRecord> claHeadRecords = entity.getClaHeadRecords();
                if (claHeadRecords.size() > 0){
                    mClassList.clear();
                    mClassList.addAll(claHeadRecords);
                    mClassUid = claHeadRecords.get(0).getClassUID();
                    queryEvent();
                    mView.setClassName(mClassList.get(0).getClassName());
                }
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                ToastUtil.show("拉取班级列表失败");
                mView.hideLoading();
                mView.showErrorView();
            }
        });
    }
    public void queryEvent(){
        if (TextUtils.isEmpty(mClassUid)){
            return;
        }
        PutiCommonModel.getInstance().queryEvent(mClassUid,1,1, Integer.MAX_VALUE,new BaseListener(PutiEvents.class){
            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                PutiEvents events = (PutiEvents) infoObj;
                ArrayList<Event> eventList = (ArrayList<Event>) events.getEvents();
                handleResult(eventList);
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                ToastUtil.show(errorMessage);
                mView.hideLoading();
                mView.showErrorView();
            }
        });
    }

    private void handleResult(ArrayList<Event> eventList){
        mView.success(eventList);
        int size = eventList.size();
        int waitSureEventCount = size;
        int importEventCount = 0;
        for (int i = 0; i < size; i++) {
            Event event = eventList.get(i);
            if (event.getCategories() == 1){
                importEventCount++;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("待确认事件 ")
                .append(String.valueOf(waitSureEventCount))
                .append(" 件").append("    其中重点事件 ").append(String.valueOf(importEventCount)).append("件");

//        StringBuilder importBuilder = new StringBuilder();
//        importBuilder.append(String.valueOf(importEventCount))
//                .append(" 件");
//        SpannableString normalString = new SpannableString(builder.toString());
//        normalString.setSpan(new StyleSpan(Typeface.NORMAL),0,normalString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        SpannableString spannableString = new SpannableString(importBuilder.toString());
//        spannableString.setSpan(new StyleSpan(Typeface.BOLD),0,importBuilder.toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(new ForegroundColorSpan(Color.RED),0,importBuilder.toString().length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mView.setDesc(builder.toString());
//        mView.addDesc(normalString);
//        mView.addDesc(spannableString);
    }
    //班级筛选列表
    public void showClassDialog(View view){
        ArrayList<String> list = new ArrayList<>();
        int size = mClassList.size();
        for (int i = 0; i < size; i++) {
            list.add(mClassList.get(i).getClassName());
        }
        final CommonDropView dropView = new CommonDropView(mContext,view,list);
        dropView.setPopOnItemClickListener(new CommonDropView.PopOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mClassUid = mClassList.get(position).getClassUID();
                String name = mClassList.get(position).getClassName();
                mView.setClassName(name);
                queryEvent();
                dropView.dismiss();
            }
        });
        dropView.showAsDropDown(view);
    }

}
