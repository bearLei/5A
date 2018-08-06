package unit.moudle.record.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.base.holder.BaseHolder;
import com.puti.education.widget.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import unit.entity.StuAcademicLevelInfo;
import unit.moudle.record.adapter.StuRecordAcademicLevelAdapter;

/**
 * Created by ${lei} on 2018/8/1.
 */
public class StuAcademicLevelHolder extends BaseHolder<StuAcademicLevelInfo> {

    @BindView(R.id.pull_down)
    ImageView pullDown;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.character_list)
    ListViewForScrollView academicList;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;

    private boolean hide;
    private StuRecordAcademicLevelAdapter mAdapter;

    public StuAcademicLevelHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        View view = InflateService.g().inflate(R.layout.stu_moral_character_holder);
        ButterKnife.bind(this, view);
        title.setText("学业水平");
        titleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide = !hide;
                academicList.setVisibility(hide ? View.GONE : View.VISIBLE);
            }
        });
        return view;
    }

    @Override
    protected void updateUI(Context context, StuAcademicLevelInfo data) {
        if (data == null) {
            return;
        }

        List<StuAcademicLevelInfo.StuAcadInfo> stuAcadInfo = data.getStuAcadInfo();

        mAdapter = new StuRecordAcademicLevelAdapter(mContext, (ArrayList<StuAcademicLevelInfo.StuAcadInfo>) stuAcadInfo);

        academicList.setAdapter(mAdapter);
    }
}
