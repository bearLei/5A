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

import butterknife.BindView;
import butterknife.ButterKnife;
import unit.entity.StuCharacterInfo;
import unit.entity.Stup;
import unit.moudle.record.adapter.StuRecordCharacterAdapter;

/**
 * Created by ${lei} on 2018/8/1.
 */
public class StuMoralCharacterHolder extends BaseHolder<StuCharacterInfo> {


    @BindView(R.id.pull_down)
    ImageView pullDown;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.character_list)
    ListViewForScrollView characterList;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;

    private boolean hide;
    private StuRecordCharacterAdapter mAdapter;

    public StuMoralCharacterHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        View view = InflateService.g().inflate(R.layout.stu_moral_character_holder);
        ButterKnife.bind(this, view);
        title.setText("思想品德");
        titleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide = !hide;
                characterList.setVisibility(hide ? View.GONE : View.VISIBLE);
            }
        });
        return view;
    }

    @Override
    protected void updateUI(Context context, StuCharacterInfo data) {
        if (data == null) {
            return;
        }
        if (data.getStuCharInfo().size() > 0) {
            ArrayList<Stup> stupArrayList = new ArrayList<>();
            for (int i = 0; i < data.getStuCharInfo().size(); i++) {
                StuCharacterInfo.StuCharInfo stuCharInfo = data.getStuCharInfo().get(i);
                stupArrayList.addAll(stuCharInfo.getStuP());
            }
            mAdapter = new StuRecordCharacterAdapter(mContext, stupArrayList);
        }
        characterList.setAdapter(mAdapter);
    }


}
