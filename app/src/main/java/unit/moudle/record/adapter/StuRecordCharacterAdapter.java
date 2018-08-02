package unit.moudle.record.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.puti.education.R;
import com.puti.education.base.InflateService;

import java.util.ArrayList;
import java.util.List;

import unit.entity.StuCharacterInfo;
import unit.entity.Stup;

/**
 * Created by ${lei} on 2018/8/1.
 */
public class StuRecordCharacterAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Stup> mData;

    public StuRecordCharacterAdapter(Context context, ArrayList<Stup> mData) {
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

        Stup stup = mData.get(position);
        holder.index.setText(String.valueOf(position+1));
        if (stup != null) {
            holder.eventType.setText(stup.getTypeName());
            holder.score.setText(String.valueOf(stup.getScore()));
            holder.term.setText(stup.getTerm());
        }
        return convertView;
    }

    public class ViewHolder{
        public TextView index,eventType,score,term;
    }
}
