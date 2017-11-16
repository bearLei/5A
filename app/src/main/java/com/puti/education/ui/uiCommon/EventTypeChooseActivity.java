package com.puti.education.ui.uiCommon;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.puti.education.R;
import com.puti.education.adapter.BasicRecylerAdapter;
import com.puti.education.bean.EventType;
import com.puti.education.listener.BaseListener;
import com.puti.education.netFrame.netModel.CommonModel;
import com.puti.education.ui.BaseActivity;
import com.puti.education.ui.uiTeacher.TeacherAddEventActivity;
import com.puti.education.util.Constant;
import com.puti.education.util.DisPlayUtil;
import com.puti.education.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;

public class EventTypeChooseActivity extends BaseActivity {

    @BindView(R.id.title_textview)
    TextView mTitleTv;
    @BindView(R.id.tv_title)
    TextView mTvEventBack;
    @BindView(R.id.recyclerview)
    RecyclerView mRecycleView;

    private Context mContext;
    private ListAdapter mListAdapter;
    private List<EventType> mTopList = new ArrayList<>();
    private Stack<EventType> mSk = new Stack<EventType>();

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_event_type_choose;
    }

    @Override
    public void initVariables() {
        mContext = this;
    }

    @Override
    public void initViews() {
        mTitleTv.setText("新增事件");

        mListAdapter = new ListAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(mListAdapter);
        mListAdapter.setDataList(mTopList);

        mTvEventBack.setClickable(false);
        mTvEventBack.setText("请选择事件类型:");

        mTvEventBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSk.size() > 0) {
                    EventType tempEvent = mSk.pop();
                    if (mSk.size() <= 0) {
                        mListAdapter.setDataList(mTopList);
                    } else {
                        EventType parentEvent = mSk.pop();
                        mListAdapter.setDataList(parentEvent.child);
                    }
                }else{
                    mTvEventBack.setText("请选择事件类型:");
                    mTvEventBack.setCompoundDrawables(null, null, null,null);

                    mListAdapter.setDataList(mTopList);

                }

            }
        });
    }

    @Override
    public void loadData() {
        getEventTypeList();
    }

    //事件类型
    private void getEventTypeList(){

        disLoading();

        CommonModel.getInstance().getEventTypeList(new BaseListener(EventType.class){

            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                hideLoading();

                if (listObj != null){
                    List<EventType> typeList = (List<EventType>) listObj;
                    mTopList=typeList;
                    mListAdapter.setDataList(mTopList);
                }
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                hideLoading();
                ToastUtil.show(errorMessage == null ? Constant.REQUEST_FAILED_STR:errorMessage);
            }
        });
    }

    public class ListAdapter extends BasicRecylerAdapter {

        public ListAdapter(Context myContext){
            super(myContext);
        }

        @Override
        public int getLayoutId() {
            return R.layout.common_dropview_item_layout;
        }

        public void refershData(List<EventType> lists){
            setDataList(lists);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            EventType eventEntity = (EventType)mList.get(position);

            CommonViewHolder viewHolder = (CommonViewHolder) holder;
            TextView textView = (TextView) viewHolder.obtainView(R.id.item_name_tv);
            textView.setText(eventEntity.name);

            viewHolder.itemView.setTag(R.id.onclick_position, position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posi = (int)v.getTag(R.id.onclick_position);
                    EventType entity = (EventType)mList.get(posi);
                    if (entity.child == null || entity.child.size() <= 0){
                        newEvet(entity);
                    }else{
                        mTvEventBack.setClickable(true);
                        mTvEventBack.setText("返回");
                        Drawable tempIc = mContext.getResources().getDrawable(R.mipmap.ic_black_back);
                        tempIc.setBounds(0, 0, tempIc.getMinimumWidth(), tempIc.getMinimumHeight());
                        mTvEventBack.setCompoundDrawables(tempIc, null, null,null);
                        mTvEventBack.setCompoundDrawablePadding(DisPlayUtil.dip2px(mContext, 5));

                        mSk.push(entity);
                        setDataList(entity.child);
                    }

                }
            });
        }
    }

    private void newEvet(EventType et){
        if (et != null) {
            Intent intent= new Intent();
            intent.putExtra("eventtypeid", et.id);
            intent.putExtra("eventtypename", et.name);
            intent.putExtra("isabnormal", et.bAbnormal);
            intent.setClass(this, TeacherAddEventActivity.class);
            startActivity(intent);
            finish();
        }
    }
}