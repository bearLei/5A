package unit.moudle.eventregist.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.util.ViewUtils;

import java.util.ArrayList;

import unit.entity.StudentEntity;
import unit.moudle.eventregist.ChooseStuManager;
import unit.moudle.eventregist.callback.OprateStuCallBack;

/**
 * Created by lei on 2018/6/15.
 */

public class StudentAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<StudentEntity.Student> mData;
    private OprateStuCallBack mOprateStuCallBack;

    public StudentAdapter(Context mContext, ArrayList<StudentEntity.Student> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void setmOprateStuCallBack(OprateStuCallBack mOprateStuCallBack) {
        this.mOprateStuCallBack = mOprateStuCallBack;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder ;

        if (convertView == null){
            convertView = InflateService.g().inflate(R.layout.puti_student_list_adapter);
            int wid = ViewUtils.getScreenWid(mContext)-ViewUtils.dip2px(mContext,80);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(wid/4,wid/4);
            convertView.setLayoutParams(params);
            holder = new ViewHolder();
            holder.avatar = (SimpleDraweeView) convertView.findViewById(R.id.avatar);
            holder.checkedIcon = (ImageView) convertView.findViewById(R.id.checked);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        final StudentEntity.Student student = mData.get(position);
        if (ChooseStuManager.students.contains(student)){
            holder.checkedIcon.setVisibility(View.VISIBLE);
        }else {
            holder.checkedIcon.setVisibility(View.GONE);
        }
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ChooseStuManager.students.contains(student)){
                    holder.checkedIcon.setVisibility(View.GONE);
                    if (mOprateStuCallBack != null){
                        mOprateStuCallBack.removeStu(student);
                    }
                }else {
                    holder.checkedIcon.setVisibility(View.VISIBLE);
                    if (mOprateStuCallBack != null){
                        mOprateStuCallBack.chooseStu(student);
                    }
                }
            }
        });

        holder.avatar.setImageURI("");

        return convertView;
    }


    public class ViewHolder{
        public SimpleDraweeView avatar;
        public ImageView checkedIcon;
    }
}
