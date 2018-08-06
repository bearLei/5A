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
import unit.entity.StuActivityInfo;
import unit.entity.StuMor;
import unit.entity.StuMoraInfo;
import unit.moudle.record.adapter.StuRecordActivityAdapter;

/**
 * Created by ${lei} on 2018/8/2.
 */
public class StuActivityHolder extends BaseHolder<StuActivityInfo> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.pull_down)
    ImageView pullDown;
    @BindView(R.id.character_list)
    ListViewForScrollView activityList;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;

    private boolean hide;

    private StuRecordActivityAdapter mAdapter;

    public StuActivityHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        View view = InflateService.g().inflate(R.layout.stu_moral_character_holder);
        ButterKnife.bind(this, view);
        title.setText("实际活动");
        titleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide = !hide;
                activityList.setVisibility(hide ? View.GONE : View.VISIBLE);
            }
        });
        return view;
    }

    @Override
    protected void updateUI(Context context, StuActivityInfo data) {

        if (data == null) {
            return;
        }

        List<StuMoraInfo> stuMoraInfo = data.getStuMoraInfo();
        ArrayList<StuMor> stuMors = new ArrayList<>();
        for (int i = 0; i < stuMoraInfo.size(); i++) {
            stuMors.addAll(stuMoraInfo.get(i).getStuMor());
        }

        mAdapter = new StuRecordActivityAdapter(mContext, stuMors);

        activityList.setAdapter(mAdapter);
    }
}
