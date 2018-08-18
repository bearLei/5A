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
import unit.entity.StuBehaviorInfo;
import unit.entity.StuMor;
import unit.entity.StuMoraInfo;
import unit.entity.StuScoreInfo;
import unit.moudle.record.adapter.StuRecordActivityAdapter;
import unit.moudle.record.adapter.StuRecordCharacterAdapter;

/**
 * Created by ${lei} on 2018/8/2.
 */
public class StuActivityHolder extends BaseHolder<StuBehaviorInfo> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.pull_down)
    ImageView pullDown;
    @BindView(R.id.character_list)
    ListViewForScrollView activityList;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;

    private boolean hide;

    private StuRecordCharacterAdapter mAdapter;

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
    protected void updateUI(Context context, StuBehaviorInfo data) {

        if (data == null) {
            return;
        }
        if (data.getStuScoreInfo().size() > 0) {
            ArrayList<StuScoreInfo.IndexInfo> stupArrayList = new ArrayList<>();
            for (int i = 0; i < data.getStuScoreInfo().size(); i++) {
                StuScoreInfo stuScoreInfo = data.getStuScoreInfo().get(i);
                stupArrayList.addAll(stuScoreInfo.getIndex());
            }
            mAdapter = new StuRecordCharacterAdapter(mContext, stupArrayList);
        }
        activityList.setAdapter(mAdapter);
    }
}
