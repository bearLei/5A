package unit.moudle.work.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.base.holder.BaseHolder;
import com.puti.education.util.ViewUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import unit.entity.StuLessEvent;

/**
 * Created by ${lei} on 2018/8/8.
 */
public class WorkStuLessHolder extends BaseHolder<ArrayList<StuLessEvent>> {
    @BindView(R.id.event_container)
    FlexboxLayout eventContainer;

    public WorkStuLessHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        View view = InflateService.g().inflate(R.layout.puti_stu_event_less);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void updateUI(Context context, ArrayList<StuLessEvent> data) {
        if (data == null || data.size() == 0) return;
        int size = data.size();
        for (int i = 0; i < size; i++) {
            StuLessEvent stuLessEvent = data.get(i);
            String studentName = stuLessEvent.getStudentName();
            int eventCount = stuLessEvent.getEventCount();
            if (eventCount < 10) {
                buildItem(studentName+"X"+eventCount,eventContainer);
            }
        }
    }

    private void buildItem(String title,ViewGroup container){
        View view = InflateService.g().inflate(R.layout.work_un_used_event_item);
        FlexboxLayout.LayoutParams param = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param.leftMargin = ViewUtils.dip2px(mContext,15);
        param.rightMargin = ViewUtils.dip2px(mContext,15);
        param.bottomMargin = ViewUtils.dip2px(mContext,15);
        view.setLayoutParams(param);
        TextView textView = (TextView) view.findViewById(R.id.title);


        textView.setText(title);
        container.addView(view);
    }
}
