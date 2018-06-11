package unit.moudle.eventregist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.base.holder.BaseHolder;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import unit.entity.EventDetail;
import unit.entity.EventMainTier;

/**
 * Created by lei on 2018/6/9.
 */

public class EventBaseHolder extends BaseHolder<EventMainTier> {
    private static final int Moral  = 1;//思想品德
    private static final int Study  = 2;//学习
    private static final int Body   = 3;//身心健康
    private static final int Action = 4;//实践

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.pull_down)
    ImageView pullDown;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.event_list)
    RecyclerView eventList;

    private boolean isPullDown;
    private EventDetailAdapter mAdapter;
    private List<EventDetail> mList;
    public EventBaseHolder(Context context) {
        super(context);
    }


    @NonNull
    @Override
    protected View initView(Context context) {
        mRootView = InflateService.g().inflate(R.layout.puti_event_holder);
        ButterKnife.bind(this, mRootView);
        isPullDown = false;
        pullDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPullDown =  ! isPullDown;
                eventList.setVisibility(isPullDown
                        ? View.VISIBLE
                        : View.GONE);
                pullDown.setImageResource(isPullDown
                        ? R.drawable.puti_up :
                        R.drawable.puti_down);
            }
        });
        if (mList == null){
            mList = new ArrayList<>();
        }
        if (mAdapter == null){
            mAdapter = new EventDetailAdapter(mContext,mList);
        }
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        eventList.setLayoutManager(manager);
        eventList.setAdapter(mAdapter);
        return mRootView;
    }

    @Override
    protected void updateUI(Context context, EventMainTier data) {
        if (data == null) return;

        String name = data.getGroupName().getGroupName();
        count.setText(String.valueOf(data.getTypes().size()));
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        switch (data.getIndexType()){
            case 1:
                builder.append("(思)");
                title.setTextColor(mContext.getResources().getColor(R.color.base_39BCA1));
                break;
            case 2:
                builder.append("(学)");
                title.setTextColor(mContext.getResources().getColor(R.color.base_f03c28));
                break;
            case 3:
                builder.append("(身)");
                title.setTextColor(mContext.getResources().getColor(R.color.base_5a5a5a));
                break;
            case 4:
                builder.append("(实)");
                title.setTextColor(mContext.getResources().getColor(R.color.base_4577dc));
                break;
            default:
                builder.append("(思)");
                title.setTextColor(mContext.getResources().getColor(R.color.base_39BCA1));
                break;
        }
        title.setText(builder.toString());

        mList.addAll(data.getTypes());
        mAdapter.setType(data.getIndexType());
        mAdapter.notifyDataSetChanged();
    }

}
