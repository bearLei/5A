package unit.moudle.record.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.puti.education.R;
import com.puti.education.base.InflateService;

import java.util.ArrayList;

import unit.entity.StuAcademicLevelInfo;
import unit.entity.StuMor;

/**
 * Created by ${lei} on 2018/8/1.
 */
public class StuRecordActivityAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<StuMor> mData;

    public StuRecordActivityAdapter(Context context, ArrayList<StuMor> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        if (mData == null){
            return 0;
        }
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

        ViewHolder holder;
        if (convertView == null){
            convertView = InflateService.g().inflate(R.layout.stu_moral_character_adapter_item);
            holder = new ViewHolder();
            holder.index = (TextView) convertView.findViewById(R.id.index);
            holder.eventType = (TextView) convertView.findViewById(R.id.eventType);
            holder.score = (TextView) convertView.findViewById(R.id.score);
            holder.term = (TextView) convertView.findViewById(R.id.term);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        StuMor stuMor = mData.get(position);
        holder.index.setText(String.valueOf(position+1));
        if (stuMor != null) {
            holder.eventType.setText(stuMor.getTypeName());
            holder.score.setText(String.valueOf(stuMor.getScore()));
            holder.term.setText("");
        }
        return convertView;
    }

    public class ViewHolder{
        public TextView index,eventType,score,term;
    }
}
