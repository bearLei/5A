package unit.moudle.eventdeal.ptr;

import android.content.Context;
import android.view.View;

import com.puti.education.base.BaseMvpPtr;
import com.puti.education.listener.BaseListener;
import com.puti.education.netFrame.response.PageInfo;
import com.puti.education.util.ToastUtil;
import com.puti.education.widget.CommonDropView;

import java.util.ArrayList;

import unit.api.PutiCommonModel;
import unit.api.PutiTeacherModel;
import unit.entity.ClassSimple;
import unit.entity.Event;
import unit.entity.PutiEvents;
import unit.moudle.eventdeal.view.EventListView;

/**
 * Created by lei on 2018/6/17.
 */

public class EventListPtr implements BaseMvpPtr {

    private static final String ImportEvent = "严重事件";

    private Context mContext;
    private EventListView mView;

    private ArrayList<ClassSimple> mClassList;

    public EventListPtr(Context mContext, EventListView mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void star() {
        queryClass();
    }

    @Override
    public void stop() {

    }

    public void queryClass(){
        PutiTeacherModel.getInstance().getClass("",new BaseListener(ClassSimple.class){
            @Override
            public void responseListResult(Object infoObj, Object listObj, PageInfo pageInfo, int code, boolean status) {
                super.responseListResult(infoObj, listObj, pageInfo, code, status);
                mClassList = (ArrayList<ClassSimple>) listObj;
                //默认拉取第一个班级的事件
                queryEvent(mClassList.get(0).getUID());
                mView.setClassName(mClassList.get(0).getName());
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                super.requestFailed(status, code, errorMessage);
                ToastUtil.show("拉取班级列表失败");
                mView.hideLoading();
                mView.showErrorView();
            }
        });
    }

    private void queryEvent(String classUid){
        PutiCommonModel.getInstance().queryEvent(classUid,-1,1, Integer.MAX_VALUE,new BaseListener(PutiEvents.class){
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
        int waitSureEventCount = 0;
        int importEventCount = 0;
        for (int i = 0; i < size; i++) {
            Event event = eventList.get(i);
            if (ImportEvent.equals(event.getCategories())){
                importEventCount++;
            }else {
                waitSureEventCount++;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("待确认事件 ")
                .append(String.valueOf(waitSureEventCount))
                .append(" 件")
                .append("    其中重点事件 ")
                .append(String.valueOf(importEventCount))
                .append(" 件");
        mView.setDesc(builder.toString());
    }
    //班级筛选列表
    public void showClassDialog(View view){
        ArrayList<String> list = new ArrayList<>();
        int size = mClassList.size();
        for (int i = 0; i < size; i++) {
            list.add(mClassList.get(i).getName());
        }
        final CommonDropView dropView = new CommonDropView(mContext,view,list);
        dropView.setPopOnItemClickListener(new CommonDropView.PopOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String uid = mClassList.get(position).getUID();
                String name = mClassList.get(position).getName();
                mView.setClassName(name);
                queryEvent(uid);
                dropView.dismiss();
            }
        });
        dropView.showAsDropDown(view);
    }

}
