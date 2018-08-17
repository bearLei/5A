package unit.moudle.classevent.holder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.ActivityChooserView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.formatter.IFillFormatter;
import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.base.holder.BaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import unit.entity.Event;
import unit.moudle.classevent.PutiClassEventActivity;
import unit.moudle.classevent.PutiClassEventDetailActivity;
import unit.moudle.classevent.StatusChangeImpl;

/**
 * Created by lei on 2018/6/26.
 */

public class ClassEventHolder extends BaseHolder<Event> {

    @BindView(R.id.time_desc)
    TextView timeDesc;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.students)
    TextView students;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.deduct)
    TextView deduct;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.status_icon)
    TextView status;

    public ClassEventHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        mRootView = InflateService.g().inflate(R.layout.puti_class_event_holder);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void updateUI(Context context, final Event data) {
        if (data == null){
            return;
        }
        timeDesc.setText(data.getTime());
        type.setText("类型：   "+data.getEventTypeName());
        students.setText("学生：   "+data.getStudentNames());
        address.setText("地点：   "+data.getAddress());
        deduct.setText("扣分：   "+data.getScores());
        desc.setText("描述：   "+data.getDescription());

//        String statusArr = data.getStatusArr();
//        String[] split = statusArr.split(",");
//        String s = split[split.length - 1];
//        switch (s){
//            case "已拒绝":
//                status.setVisibility(View.VISIBLE);
//                status.setText("已拒绝");
//                status.setTextColor(mContext.getResources().getColor(R.color.base_f03c28));
//                status.setBackground(mContext.getResources().getDrawable(R.drawable.bg_puti_refuse));
//                break;
//            case "处理中":
//                status.setVisibility(View.VISIBLE);
//                status.setText("处理中");
//                status.setTextColor(mContext.getResources().getColor(R.color.base_666666));
//                status.setBackground(mContext.getResources().getDrawable(R.drawable.bg_puti_stroke_cdcdcd));
//                break;
//            case "审核中":
//                status.setVisibility(View.VISIBLE);
//                status.setText("审核中");
//                status.setTextColor(mContext.getResources().getColor(R.color.base_666666));
//                status.setBackground(mContext.getResources().getDrawable(R.drawable.bg_puti_stroke_cdcdcd));
//                break;
//            case "已完结":
//                status.setVisibility(View.VISIBLE);
//                status.setText("已完结");
//                status.setTextColor(mContext.getResources().getColor(R.color.base_666666));
//                status.setBackground(mContext.getResources().getDrawable(R.drawable.bg_puti_stroke_cdcdcd));
//                break;
//                default:
//                    status.setVisibility(View.GONE);
//                    break;
//        }

        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PutiClassEventDetailActivity.class);
                intent.putExtra(PutiClassEventDetailActivity.Parse_Intent,data);
                mContext.startActivity(intent);
            }
        });
    }
}
