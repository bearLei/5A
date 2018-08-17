package unit.moudle.eventdeal.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.base.holder.BaseHolder;
import com.puti.education.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unit.entity.Event2Involved;

/**
 * Created by lei on 2018/6/25.
 * 事件确认详情-扣分holder
 */

public class DealEventDetailDeductHolder extends BaseHolder<Event2Involved> {

    @BindView(R.id.score_one)
    TextView scoreOne;
    @BindView(R.id.score_two)
    TextView scoreTwo;
    @BindView(R.id.score_three)
    TextView scoreThree;
    @BindView(R.id.edit_score)
    EditText editScore;
    @BindView(R.id.refer_score)
    TextView referScore;
    @BindView(R.id.sign)
    TextView TSign;

    private HashMap<TextView,Boolean> mViewSelectedMap;

    public DealEventDetailDeductHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        mRootView = InflateService.g().inflate(R.layout.puti_deal_event_deduct_holder);
        ButterKnife.bind(this, mRootView);
        editScore.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    editScore.setCursorVisible(true);
                }else {
                    editScore.setCursorVisible(false);
                }
            }
        });
        return mRootView;
    }

    @Override
    protected void updateUI(Context context, Event2Involved data) {
        if (data == null) {
            return;
        }

        TSign.setText(data.getSign() > 0 ? "加分" : "扣分");
        int defaultDownScore = Math.abs(data.getDefaultDownScore() * data.getSign());
        int defaultUpScore = Math.abs(data.getDefaultUpScore() * data.getSign());
        scoreOne.setText(String.valueOf(defaultDownScore));
        scoreThree.setText(String.valueOf(defaultUpScore));
        if (Math.abs(defaultUpScore  - defaultDownScore) > 2){
            scoreTwo.setVisibility(View.VISIBLE);
            scoreTwo.setText(String.valueOf((defaultDownScore + defaultUpScore)/2));
        }else {
            scoreTwo.setVisibility(View.GONE);
        }
        if (defaultDownScore == defaultUpScore){
            scoreThree.setVisibility(View.GONE);
        }else {
            scoreThree.setVisibility(View.VISIBLE);
        }

       StringBuilder builder = new StringBuilder();
        builder.append("参考")
                .append(TSign.getText().toString())
                .append("区间 (")
                .append(defaultDownScore)
                .append("分到")
                .append(defaultUpScore)
                .append("分)");
        referScore.setText(builder.toString());


        mViewSelectedMap = new HashMap<>();
        mViewSelectedMap.put(scoreOne,false);
        mViewSelectedMap.put(scoreTwo,false);
        mViewSelectedMap.put(scoreThree,false);

    }

    @OnClick({R.id.score_one, R.id.score_two, R.id.score_three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.score_one:
                oprateView(scoreOne);
                break;
            case R.id.score_two:
                oprateView(scoreTwo);
                break;
            case R.id.score_three:
                oprateView(scoreThree);
                break;
        }
    }
    private void oprateView(final TextView view){
        editScore.clearFocus();
        Iterator<Map.Entry<TextView, Boolean>> iterator = mViewSelectedMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<TextView, Boolean> entry = iterator.next();
            TextView item = entry.getKey();
            if (view == item){
                Boolean isSelected = mViewSelectedMap.get(item);
                if (isSelected){
                    mViewSelectedMap.put(item,false);
                    item.setSelected(false);
                    item.setTextColor(mContext.getResources().getColor(R.color.base_39BCA1));
                }else {
                    mViewSelectedMap.put(item,true);
                    item.setSelected(true);
                    item.setTextColor(mContext.getResources().getColor(R.color.base_ffffff));
                }

            }else {
                mViewSelectedMap.put(item,false);
                item.setSelected(false);
                item.setTextColor(mContext.getResources().getColor(R.color.base_39BCA1));
            }
        }
    }

    public int getScore() {
        int defaultDownScore = Math.abs(getData().getDefaultDownScore() * getData().getSign());
        int defaultUpScore = Math.abs(getData().getDefaultUpScore() * getData().getSign());
        if (!TextUtils.isEmpty(editScore.getText().toString())){
            int score = Integer.parseInt(editScore.getText().toString());
            if (defaultDownScore<= score && score <= defaultUpScore){
                if (getData().getSign() > 0) {
                    return score;
                }else {
                    return score - 2*score;
                }
            }
        }else {
            Iterator<Map.Entry<TextView, Boolean>> iterator = mViewSelectedMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<TextView  , Boolean> entry = iterator.next();
                Boolean value = entry.getValue();
                View view = entry.getKey();
                if (value == true && view instanceof TextView){
                    int score = Integer.parseInt(((TextView) view).getText().toString());
                    if (getData().getSign() > 0){
                        return score;
                    }else {
                        return score - 2*score;
                    }
                }
            }
        }
        return 0;
    }


}
