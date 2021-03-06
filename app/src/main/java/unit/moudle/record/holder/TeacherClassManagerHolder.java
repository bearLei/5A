package unit.moudle.record.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.base.holder.BaseHolder;
import com.puti.education.widget.ListViewForScrollView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import unit.moudle.record.adapter.TeacherRecordAdapter;

/**
 * Created by lei on 2018/6/18.
 */

public class TeacherClassManagerHolder extends BaseHolder<ArrayList<ClaRecordInfo>> {

    @BindView(R.id.listview)
    ListViewForScrollView listview;
    @BindView(R.id.pull_down)
    ImageView pullDown;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;

    private boolean hide;
    private TeacherRecordAdapter mAdapter;

    public TeacherClassManagerHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        mRootView = InflateService.g().inflate(R.layout.puti_tea_class_manager_holder);
        ButterKnife.bind(this, mRootView);
        titleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide = !hide;
                listview.setVisibility(hide ? View.GONE : View.VISIBLE);
            }
        });
        return mRootView;
    }

    @Override
    protected void updateUI(Context context, ArrayList<ClaRecordInfo> data) {
        if (data == null || data.size() == 0) return;

        if (mAdapter == null) {
            mAdapter = new TeacherRecordAdapter(mContext, data);
        }
        listview.setAdapter(mAdapter);
    }

}
