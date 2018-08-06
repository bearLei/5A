package unit.moudle.record.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.puti.education.R;
import com.puti.education.base.InflateService;
import com.puti.education.base.holder.BaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import unit.entity.StudentInfo;
import unit.widget.PutiRecordItem;

/**
 * Created by lei on 2018/6/18.
 * 学生档案-学生基础信息
 */

public class StuBaseInfoHolder extends BaseHolder<StudentInfo> {
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.name)
    PutiRecordItem name;
    @BindView(R.id.sex)
    PutiRecordItem sex;
    @BindView(R.id.nation)
    PutiRecordItem nation;
    @BindView(R.id.birth)
    PutiRecordItem birth;
    @BindView(R.id.category)
    PutiRecordItem category;
    @BindView(R.id.cencus)
    PutiRecordItem cencus;
    @BindView(R.id.mobile)
    PutiRecordItem mobile;
    @BindView(R.id.stu_card)
    PutiRecordItem stuCard;
    @BindView(R.id.family_address)
    PutiRecordItem familyAddress;
    @BindView(R.id.father_name)
    PutiRecordItem fatherName;
    @BindView(R.id.father_mobile)
    PutiRecordItem fatherMobile;
    @BindView(R.id.father_card)
    PutiRecordItem fatherCard;
    @BindView(R.id.pull_down)
    ImageView pullDown;
    @BindView(R.id.content_layout)
    LinearLayout contentLayout;

    @BindView(R.id.stu_class)
    PutiRecordItem stuClass;
    @BindView(R.id.stu_major)
    PutiRecordItem stuMajor;
    @BindView(R.id.stu_yuanxi)
    PutiRecordItem stuYuanxi;
    @BindView(R.id.stu_id)
    PutiRecordItem stuId;
    @BindView(R.id.stu_status)
    PutiRecordItem stuStatus;
    @BindView(R.id.stu_in_school)
    PutiRecordItem stuInSchool;
    @BindView(R.id.stu_dorm_status)
    PutiRecordItem stuDormStatus;
    @BindView(R.id.stu_dorm_number)
    PutiRecordItem stuDormNumber;

    private boolean hide;

    public StuBaseInfoHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        mRootView = InflateService.g().inflate(R.layout.puti_stu_record_base_info_holder);
        ButterKnife.bind(this, mRootView);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide = !hide;
                contentLayout.setVisibility(hide ? View.GONE : View.VISIBLE);
            }
        });
        return mRootView;
    }

    @Override
    protected void updateUI(Context context, StudentInfo data) {
        if (data == null) {
            return;
        }
        StudentInfo.StuBasicInfo stuBasicInfo = data.getStuBasicInfo();
        StudentInfo.StuFatherInfo stuFatherInfo = data.getStuFatherInfo();
        StudentInfo.StuMotherInfo stuMotherInfo = data.getStuMotherInfo();
        StudentInfo.StudentInfoms studentInfoms = data.getStudentInfoms();
        StudentInfo.StuDorminfo stuDorminfo = data.getStuDorminfo();
        StudentInfo.StuHeadInfo stuHeadInfo = data.getStuHeadInfo();

        //基础信息
        if (stuBasicInfo != null) {
            name.setTDesc(stuBasicInfo.getUserName());
            if (stuBasicInfo.getSex().equals("F")) {
                sex.setTDesc("男");
            } else {
                sex.setTDesc("女");
            }
            // TODO: 2018/6/18 民族
            birth.setTDesc(stuBasicInfo.getBirthday());
            cencus.setTDesc(stuBasicInfo.getCensusRegister());

            mobile.setTDesc(stuBasicInfo.getMobile());
            stuCard.setTDesc(stuBasicInfo.getIdCard());
            familyAddress.setTDesc(stuBasicInfo.getAddress());
        }
        //父亲信息
        if (stuFatherInfo != null) {
            fatherName.setTDesc(stuFatherInfo.getUserName());
            fatherMobile.setTDesc(stuFatherInfo.getMobile());
            fatherCard.setTDesc(stuFatherInfo.getIdCard());
        }

        if (studentInfoms != null) {
            stuClass.setTDesc(studentInfoms.getClassName());
            stuMajor.setTDesc(studentInfoms.getProfessionalName());
            stuYuanxi.setTDesc(studentInfoms.getDepartment());
            //学号
            stuStatus.setTDesc(studentInfoms.getStatus() == 0 ? "正常" : "异常");
            stuInSchool.setTDesc(studentInfoms.getStatusTime());
        }
        if (stuDorminfo != null){
            stuDormStatus.setTDesc(stuDorminfo.isStatus() ? "是" : "否" );
            stuDormNumber.setTDesc(stuDorminfo.getRoom());
        }
    }
}
