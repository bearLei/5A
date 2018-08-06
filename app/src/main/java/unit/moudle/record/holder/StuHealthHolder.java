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
import unit.entity.StuHealthInfo;
import unit.entity.StuHealthMor;
import unit.entity.StuHealthMoraInfo;
import unit.moudle.record.adapter.StuRecordHealthAdapter;

/**
 * Created by ${lei} on 2018/8/2.
 */
public class StuHealthHolder extends BaseHolder<StuHealthInfo> {
    @BindView(R.id.pull_down)
    ImageView pullDown;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.character_list)
    ListViewForScrollView healthList;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;

    private boolean hide;
    private StuRecordHealthAdapter mAdapter;

    public StuHealthHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        View view = InflateService.g().inflate(R.layout.stu_moral_character_holder);
        ButterKnife.bind(this, view);
        title.setText("身心健康");
        titleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide = !hide;
                healthList.setVisibility(hide ? View.GONE : View.VISIBLE);
            }
        });
        return view;
    }

    @Override
    protected void updateUI(Context context, StuHealthInfo data) {
        if (data == null) {
            return;
        }
        List<StuHealthMoraInfo> stuMoraInfo = data.getStuMoraInfo();

        ArrayList<StuHealthMor> stuHealthMors = new ArrayList<>();

        for (int i = 0; i < stuMoraInfo.size(); i++) {
            stuHealthMors.addAll(stuMoraInfo.get(i).getStuMor());
        }

        mAdapter = new StuRecordHealthAdapter(mContext, stuHealthMors);

        healthList.setAdapter(mAdapter);
    }
}
