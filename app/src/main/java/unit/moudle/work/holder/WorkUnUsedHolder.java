package unit.moudle.work.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.style.TypefaceSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.base.holder.BaseHolder;
import com.puti.education.util.ViewUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import unit.entity.PutiUnUsedEntity;

/**
 * Created by lei on 2018/7/22.
 */

public class WorkUnUsedHolder extends BaseHolder<PutiUnUsedEntity> {

    @BindView(R.id.week_container)
    FlexboxLayout weekContainer;
    @BindView(R.id.month_container)
    FlexboxLayout monthContainer;
    @BindView(R.id.term_container)
    FlexboxLayout termContainer;

    public WorkUnUsedHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        View view = InflateService.g().inflate(R.layout.work_unused_holder);
        ButterKnife.bind(this, view);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        weekContainer.setLayoutParams(param);
        monthContainer.setLayoutParams(param);
        termContainer.setLayoutParams(param);
        return view;
    }

    @Override
    protected void updateUI(Context context, PutiUnUsedEntity data) {
        if (data == null){
            return;
        }
        HashMap<String,Integer> monthList = data.getMonthList();
        HashMap<String,Integer>  termList = data.getTermList();
        HashMap<String,Integer>  weekList = data.getWeekList();
        monthContainer.removeAllViews();
        weekContainer.removeAllViews();
        termContainer.removeAllViews();
//
        Iterator<Map.Entry<String, Integer>> iterator = monthList.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            buildItem(next.getKey(),next.getValue(),monthContainer);
        }
        Iterator<Map.Entry<String, Integer>> iterator1 = weekList.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, Integer> next = iterator1.next();
            buildItem(next.getKey(),next.getValue(),weekContainer);
        }

        Iterator<Map.Entry<String, Integer>> iterator2 = termList.entrySet().iterator();
        while (iterator2.hasNext()){
            Map.Entry<String, Integer> next = iterator2.next();
            buildItem(next.getKey(),next.getValue(),termContainer);
        }

    }

    private void buildItem(String title, int status,ViewGroup container){
        View view = InflateService.g().inflate(R.layout.work_un_used_event_item);
        FlexboxLayout.LayoutParams param = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param.leftMargin = ViewUtils.dip2px(mContext,15);
        param.rightMargin = ViewUtils.dip2px(mContext,15);
        param.bottomMargin = ViewUtils.dip2px(mContext,15);
        view.setLayoutParams(param);
        TextView textView = (TextView) view.findViewById(R.id.title);
        LinearLayout root = (LinearLayout) view.findViewById(R.id.container);
        if (status >= 0){
            root.setBackgroundResource(R.drawable.bg_shape_work_unused_item);
        }else {
            root.setBackgroundResource(R.drawable.bg_shape_work_used_item);
        }
        textView.setText(title);
        container.addView(view);
    }

}
