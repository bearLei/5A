package unit.moudle.contacts.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.util.ViewUtils;

import java.util.ArrayList;

import unit.entity.ContactInfo;
import unit.entity.ParContactInfo;

/**
 * Created by lei on 2018/6/19.
 */

public class ParContactHolderAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ParContactInfo.ParContactDetailInfo> mData;

    public ParContactHolderAdapter(Context context, ArrayList<ParContactInfo.ParContactDetailInfo> mData) {
        this.context = context;
        this.mData = mData;
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
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = InflateService.g().inflate(R.layout.puti_par_contact_holder_adapter_item);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewUtils.dip2px(context,60));
            convertView.setLayoutParams(params);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.fName = (TextView) convertView.findViewById(R.id.fname);
            viewHolder.fMobile = (TextView) convertView.findViewById(R.id.fmobile);
            viewHolder.line = convertView.findViewById(R.id.line_bottom);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mData.size() <= 1){
            viewHolder.line.setVisibility(View.GONE);
        }else {
            viewHolder.line.setVisibility(View.VISIBLE);
        }

        ParContactInfo.ParContactDetailInfo info = mData.get(position);

        viewHolder.name.setText(info.getStudentName());
        viewHolder.fName.setText(info.getParent());
        viewHolder.fMobile.setText(info.getParentPhone());
        return convertView;
    }

    public class ViewHolder{
        public TextView name,fName,fMobile;
        private View line;
    }

}
