package com.puti.education.ui.uiTeacher.chooseperson.mul;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.base.holder.BaseHolder;
import com.puti.education.ui.uiTeacher.chooseperson.ChoosePersonParameter;
import com.puti.education.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by lei at 2017/12/30
 */

public class MulChooseHolder extends BaseHolder<MulPersonBean> implements View.OnClickListener {

    @BindView(R.id.person_head_img)
    ImageView icon;
    @BindView(R.id.person_name_tv)
    TextView name;
    @BindView(R.id.person_class_tv)
    TextView TDesc;
    @BindView(R.id.iv_select)
    ImageView ISelect;
    @BindView(R.id.item_container)
    LinearLayout VContainer;

    private MulPersonBean mInfo;
    private boolean isSeleted;
    private MulSeletePersonCallBack mSeleteCallBack;
    public MulChooseHolder(Context context) {
        super(context);
    }

    public MulChooseHolder(Context context, MulSeletePersonCallBack mSeleteCallBack) {
        super(context);
        this.mSeleteCallBack = mSeleteCallBack;
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        ViewGroup viewGroup = (ViewGroup) InflateService.g(context).inflate(R.layout.item_teacher_and_student_layout);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this, viewGroup);
        VContainer.setLayoutParams(params);
        VContainer.setOnClickListener(this);
        return viewGroup;
    }

    @Override
    protected void updateUI(Context context, MulPersonBean data) {

        if (data != null){
            mInfo = data;
            isSeleted = data.isSelected();
//            icon.setVisibility(View.GONE);
//            ImgLoadUtil.displayCirclePic(R.mipmap.ic_avatar_default,data.getAvatar(),icon);
            if (!TextUtils.isEmpty(data.getRealName())){
                name.setText(data.getRealName());
            }
            if (!TextUtils.isEmpty(data.getPhone())){
                TDesc.setText(data.getPhone());
            }
            ISelect.setVisibility(View.VISIBLE);
            if (isSeleted){
                ISelect.setImageResource(R.mipmap.ic_item_selected);
            }else{
                ISelect.setImageResource(R.mipmap.ic_item_unselected);
            }


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_container:
                if (ChoosePersonParameter.LIMIT <= 0){
                    ToastUtil.show("您的选择已达上限");
                    return;
                }
                if (isSeleted){
                    ISelect.setImageResource(R.mipmap.ic_item_unselected);
                }else{
                    ISelect.setImageResource(R.mipmap.ic_item_selected);
                }
                isSeleted = !isSeleted;

                if (mSeleteCallBack != null){
                    mSeleteCallBack.seleted(isSeleted,mInfo);
                }
                break;
        }
    }
}
