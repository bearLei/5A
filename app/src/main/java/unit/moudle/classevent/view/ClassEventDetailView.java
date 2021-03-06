package unit.moudle.classevent.view;

import android.view.View;

import com.puti.education.base.BaseMvpView;

import java.util.ArrayList;

import unit.entity.DealEntity;
import unit.entity.Event2Involved;

/**
 * Created by lei on 2018/6/22.
 */

public interface ClassEventDetailView  extends BaseMvpView{
    void setTitle(String title);
    void getHeadHolderView(View view);
    void success(ArrayList<DealEntity> data);
    void addData(DealEntity data);
    void clearData();
    String getEventId();
}
