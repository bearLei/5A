package unit.moudle.record.ptr;

import android.content.Context;

import com.puti.education.base.BaseMvpPtr;
import com.puti.education.listener.BaseListener;
import com.puti.education.netFrame.response.PageInfo;
import com.puti.education.util.ToastUtil;

import unit.api.PutiCommonModel;
import unit.entity.StuAcademicLevelInfo;
import unit.entity.StuActivityInfo;
import unit.entity.StuCharacterInfo;
import unit.entity.StuHealthInfo;
import unit.entity.StudentInfo;
import unit.entity.StudentPortraitInfo;
import unit.moudle.record.holder.StuAcademicLevelHolder;
import unit.moudle.record.holder.StuActivityHolder;
import unit.moudle.record.holder.StuBaseInfoHolder;
import unit.moudle.record.holder.StuHealthHolder;
import unit.moudle.record.holder.StuMoralCharacterHolder;
import unit.moudle.record.holder.StuPortraitHolder;
import unit.moudle.record.view.PutiStuView;

/**
 * Created by lei on 2018/6/18.
 */

public class PutiStuPtr implements BaseMvpPtr {

    private Context mContext;
    private PutiStuView mView;

    private StuBaseInfoHolder stuBaseInfoHolder;
    private StuPortraitHolder stuPortraitHolder;
    private StuMoralCharacterHolder stuMoralCharacterHolder;
    private StuAcademicLevelHolder stuAcademicLevelHolder;
    private StuHealthHolder stuHealthHolder;
    private StuActivityHolder stuActivityHolder;

    public PutiStuPtr(Context mContext, PutiStuView mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void star() {
        initStuBaseInfoHolder();
        initStuPortraitHolder();
        initStuMoralCharacterHolder();
        initStuAcademicLeveleHolder();
        initStuHealthHolder();
        initStuActivityHolder();


        queryPortrait();
        queryBaseInfo();

        queryStuMoralCharacter();
        queryStuAcademicLevel();
        queryStuMentalHealth();
        queryStuActivity();
    }

    @Override
    public void stop() {

    }

    //查询学生画像
    private void queryPortrait(){
        PutiCommonModel.getInstance().queryPortrait(mView.getStudentUid(),"",new BaseListener(StudentPortraitInfo.class){
            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {

                StudentPortraitInfo info = (StudentPortraitInfo) infoObj;
                stuPortraitHolder.setData(info);
                if (info != null && info.getStudentInfoms() != null){
                    mView.setHeadTitle(info.getStudentInfoms().getUserName());
                }
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                ToastUtil.show(errorMessage);
            }
        });
    }

    private void queryBaseInfo(){
        PutiCommonModel.getInstance().queryStuInfo(mView.getStudentUid(),"",new BaseListener(StudentInfo.class){
            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                super.responseResult(infoObj, listObj, code, status);
                StudentInfo info = (StudentInfo) infoObj;
                if (stuBaseInfoHolder != null){
                    stuBaseInfoHolder.setData(info);
                }
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                super.requestFailed(status, code, errorMessage);
            }
        });
    }
    //查询学生思想品德信息
    private void queryStuMoralCharacter(){
        PutiCommonModel.getInstance().queryCharacter(mView.getStudentUid(),"",new BaseListener(StuCharacterInfo.class){
            @Override
            public void responseListResult(Object infoObj, Object listObj, PageInfo pageInfo, int code, boolean status) {
                super.responseListResult(infoObj, listObj, pageInfo, code, status);
            }

            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                super.responseResult(infoObj, listObj, code, status);
                StuCharacterInfo stuCharacterInfo = (StuCharacterInfo) infoObj;
                if (stuMoralCharacterHolder != null){
                    stuMoralCharacterHolder.setData(stuCharacterInfo);
                }
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                super.requestFailed(status, code, errorMessage);
            }
        });
    }
    //查询学生专业水平信息
    private void queryStuAcademicLevel(){
        PutiCommonModel.getInstance().queryAcademicLevel(mView.getStudentUid(),"",new BaseListener(StuAcademicLevelInfo.class){
            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                super.responseResult(infoObj, listObj, code, status);
                StuAcademicLevelInfo info = (StuAcademicLevelInfo) infoObj;
                if (stuAcademicLevelHolder != null){
                    stuAcademicLevelHolder.setData(info);
                }
            }

            @Override
            public void responseListResult(Object infoObj, Object listObj, PageInfo pageInfo, int code, boolean status) {
                super.responseListResult(infoObj, listObj, pageInfo, code, status);
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                super.requestFailed(status, code, errorMessage);
            }
        });
    }
    private void queryStuMentalHealth(){
        PutiCommonModel.getInstance().queryMentalHealth(mView.getStudentUid(),"",new BaseListener(StuHealthInfo.class){
            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                StuHealthInfo info = (StuHealthInfo) infoObj;
                if (stuHealthHolder != null){
                    stuHealthHolder.setData(info);
                }
            }

            @Override
            public void responseListResult(Object infoObj, Object listObj, PageInfo pageInfo, int code, boolean status) {
                super.responseListResult(infoObj, listObj, pageInfo, code, status);

            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                super.requestFailed(status, code, errorMessage);
            }
        });
    }
    private void queryStuActivity(){
        PutiCommonModel.getInstance().queryActivity(mView.getStudentUid(),"",new BaseListener(StuActivityInfo.class){
            @Override
            public void responseListResult(Object infoObj, Object listObj, PageInfo pageInfo, int code, boolean status) {
                super.responseListResult(infoObj, listObj, pageInfo, code, status);
            }

            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                super.responseResult(infoObj, listObj, code, status);
                StuActivityInfo info = (StuActivityInfo) infoObj;
                if (stuActivityHolder != null){
                    stuActivityHolder.setData(info);
                }
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                super.requestFailed(status, code, errorMessage);
            }
        });
    }
    private void initStuBaseInfoHolder(){
        if (stuBaseInfoHolder == null){
            stuBaseInfoHolder = new StuBaseInfoHolder(mContext);
        }
        mView.addBaseInfoView(stuBaseInfoHolder.getRootView());
    }
    private void initStuPortraitHolder(){
        if (stuPortraitHolder == null){
            stuPortraitHolder = new StuPortraitHolder(mContext);
        }
        mView.addLikeNessView(stuPortraitHolder.getRootView());
    }

    //思想品德
    private void initStuMoralCharacterHolder(){
        if (stuMoralCharacterHolder == null){
            stuMoralCharacterHolder = new StuMoralCharacterHolder(mContext);
        }
        mView.addCharacTerView(stuMoralCharacterHolder.getRootView());
    }

    //学业水平
    private void initStuAcademicLeveleHolder(){
        if (stuAcademicLevelHolder == null){
            stuAcademicLevelHolder = new StuAcademicLevelHolder(mContext);
        }
        mView.addAcademicLevelView(stuAcademicLevelHolder.getRootView());
    }

    //身心健康
    private void initStuHealthHolder(){
        if (stuHealthHolder == null){
            stuHealthHolder = new StuHealthHolder(mContext);
        }
        mView.addMeatalHealthView(stuHealthHolder.getRootView());
    }

    private void initStuActivityHolder(){
        if (stuActivityHolder == null){
            stuActivityHolder = new StuActivityHolder(mContext);
        }
        mView.addActivityView(stuActivityHolder.getRootView());
    }

}
