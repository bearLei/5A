package unit.moudle.classevent.ptr;

import android.content.Context;

import com.puti.education.base.BaseMvpPtr;
import com.puti.education.listener.BaseListener;
import com.puti.education.netFrame.response.PageInfo;
import com.puti.education.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import unit.api.PutiCommonModel;
import unit.entity.DealEntity;
import unit.entity.DealEventMain;
import unit.entity.Event2Involved;
import unit.entity.StudentEvent;
import unit.moudle.classevent.view.ClassEventDetailView;
import unit.moudle.classevent.view.ClassEventView;
import unit.moudle.eventdeal.holder.DealEventDetailHeadHolder;
import unit.moudle.eventdeal.view.EventDetailView;

/**
 * Created by lei on 2018/6/22.
 */

public class ClassEventDetailPtr implements BaseMvpPtr {

    private Context context;
    private ClassEventDetailView mView;
    private DealEventDetailHeadHolder dealEventDetailHeadHolder;

    public ClassEventDetailPtr(Context context, ClassEventDetailView mView) {
        this.context = context;
        this.mView = mView;
    }

    @Override
    public void star() {
        initDealEventDetailHeadHolder();
        queryData(mView.getEventId());
    }

    @Override
    public void stop() {

    }

    private void initDealEventDetailHeadHolder(){
        if (dealEventDetailHeadHolder == null){
            dealEventDetailHeadHolder = new DealEventDetailHeadHolder(context);
        }
        mView.getHeadHolderView(dealEventDetailHeadHolder.getRootView());
    }

    private void queryData(final String eventId){
        PutiCommonModel.getInstance().queryEventDetail(eventId,new BaseListener(DealEventMain.class){
            @Override
            public void responseListResult(Object infoObj, Object listObj, PageInfo pageInfo, int code, boolean status) {
                super.responseListResult(infoObj, listObj, pageInfo, code, status);
                List<DealEventMain> list = (List<DealEventMain>) listObj;
                if (list != null && list.size() > 0){
                    handleResult(list.get(0));
                }
                mView.hideLoading();
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                mView.hideLoading();
                mView.showErrorView();
                ToastUtil.show(errorMessage);
            }
        });
    }

    private void handleResult(DealEventMain eventMain){
        dealEventDetailHeadHolder.setData(eventMain);
        mView.setTitle(String.valueOf(eventMain.getEvent2Involveds().size()));

        mView.clearData();
        List<Event2Involved> list = eventMain.getEvent2Involveds();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String event2InvolvedUID = list.get(i).getEvent2InvolvedUID();
            queryEvent2InvolvedUID(event2InvolvedUID);
        }
    }


    private void queryEvent2InvolvedUID(String event2InvolvedUID){
        PutiCommonModel.getInstance().queryEvent2InvolvedUID(event2InvolvedUID,new BaseListener(StudentEvent.class){
            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                StudentEvent studentEvent = (StudentEvent) infoObj;
                if (studentEvent != null){
                    List<DealEntity> deals = studentEvent.getDeals();
                    if (deals != null && deals.size() > 0){
                        DealEntity entity = deals.get(0);
                        entity.setClassName(studentEvent.getInfo().getClassName());
                        entity.setStudentName(studentEvent.getInfo().getStudentName());
                        mView.addData(entity);
                    }
                }
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                ToastUtil.show(errorMessage);
            }
        });
    }

}
