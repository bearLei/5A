package unit.moudle.record.view;

import android.view.View;

import com.puti.education.base.BaseMvpView;

import unit.moudle.record.holder.StuPortraitHolder;

/**
 * Created by lei on 2018/6/18.
 */

public interface PutiStuView extends BaseMvpView{

    void addLikeNessView(View view);
    void addBaseInfoView(View view);
    void addCharacTerView(View view);
    void addAcademicLevelView(View view);
    void addMeatalHealthView(View view);
    void addActivityView(View view);
    String getStudentUid();
    void setHeadTitle(String name);
}
