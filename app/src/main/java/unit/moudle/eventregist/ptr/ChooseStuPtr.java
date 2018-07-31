package unit.moudle.eventregist.ptr;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.puti.education.base.BaseMvpPtr;
import com.puti.education.listener.BaseListener;
import com.puti.education.netFrame.response.PageInfo;
import com.puti.education.util.CharacterParser;
import com.puti.education.util.ToastUtil;
import com.puti.education.widget.CommonDropView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import unit.api.PutiTeacherModel;
import unit.entity.ClassSimple;
import unit.entity.Student;
import unit.entity.StudentEntity;
import unit.entity.StudentEvent;
import unit.moudle.eventregist.entity.ChooseStuEntity;
import unit.moudle.eventregist.view.ChooseStuView;

/**
 * Created by lei on 2018/6/14.
 */

public class ChooseStuPtr implements BaseMvpPtr {

    private Context mContext;
    private ChooseStuView mView;

    private ArrayList<ClassSimple> mClassList;
    private CharacterParser characterParser;
    private ArrayList<ChooseStuEntity> mStudentList;
    private Map<String,ArrayList<Student>> studentMap;
//    private  StudentEntity entity;
    private String mChooseClassId;
    public ChooseStuPtr(Context mContext, ChooseStuView mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void star() {
        if (characterParser == null){
            characterParser = new CharacterParser();
        }
        if (mStudentList == null){
            mStudentList = new ArrayList<>();
        }
        if (studentMap == null){
            studentMap = new HashMap<>();
        }
        queryClass();
    }

    @Override
    public void stop() {

    }

    public void queryClass(){
        PutiTeacherModel.getInstance().getClass("",new BaseListener(ClassSimple.class){
            @Override
            public void responseListResult(Object infoObj, Object listObj, PageInfo pageInfo, int code, boolean status) {
                super.responseListResult(infoObj, listObj, pageInfo, code, status);
                mClassList = (ArrayList<ClassSimple>) listObj;
                //默认拉取第一个班级的学生
                mChooseClassId = mClassList.get(0).getUID();
                queryStudent("");
                mView.setClassName(mClassList.get(0).getName());
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                super.requestFailed(status, code, errorMessage);
                ToastUtil.show("拉取班级列表失败");
                mView.hideLoading();
                mView.showErrorView();
            }
        });
    }

    public void queryStudent(String studentName){
        mView.showLoading();
        PutiTeacherModel.getInstance().getStudent(mChooseClassId,"",0,1,Integer.MAX_VALUE,studentName,new BaseListener(StudentEntity.class){
            @Override
            public void responseResult(Object infoObj, Object listObj, int code, boolean status) {
                super.responseResult(infoObj, listObj, code, status);
                StudentEntity studentEntity = (StudentEntity) infoObj;
                handleResult(studentEntity.getStudents());
            }

            @Override
            public void responseListResult(Object infoObj, Object listObj, PageInfo pageInfo, int code, boolean status) {
                super.responseListResult(infoObj, listObj, pageInfo, code, status);
//                ArrayList<StudentEntity> students = (ArrayList<StudentEntity>) listObj;
//                handleResult(students);
            }

            @Override
            public void requestFailed(boolean status, int code, String errorMessage) {
                super.requestFailed(status, code, errorMessage);
                ToastUtil.show("拉取学生列表失败");
                mView.hideLoading();
                mView.showErrorView();
            }
        });
    }

    public void handleResult(List<Student> students) {
        final int size = students.size();
        studentMap.clear();
        mStudentList.clear();
        for (int i = 0; i < size; i++) {
            Student student = students.get(i);
            if (!TextUtils.isEmpty(student.getStudentName())) {
                String s = getSelling(student.getStudentName());
                if (studentMap.containsKey(s)) {
                    studentMap.get(s).add(student);
                } else {
                    ArrayList<Student> list = new ArrayList<Student>();
                    list.add(student);
                    studentMap.put(s, list);
                }
            }
        }
        Iterator<Map.Entry<String, ArrayList<Student>>> iterator = studentMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ArrayList<Student>> entry = iterator.next();
            ChooseStuEntity student = new ChooseStuEntity();
            student.setLetter(entry.getKey());
            student.setmStuents(entry.getValue());
            mStudentList.add(student);
        }
        mView.success(mStudentList);

    }

    private String getSelling(String name){
        if (characterParser == null){
            characterParser = new CharacterParser();
        }
        String s = characterParser.getSelling(name);
        if (s.length() > 0){
            return String.valueOf(s.charAt(0));
        }
        return "";
    }

    //班级筛选列表
    public void showClassDialog(View view){
        ArrayList<String> list = new ArrayList<>();
        int size = mClassList.size();
        for (int i = 0; i < size; i++) {
            list.add(mClassList.get(i).getName());
        }
        final CommonDropView dropView = new CommonDropView(mContext,view,list);
        dropView.setPopOnItemClickListener(new CommonDropView.PopOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mChooseClassId= mClassList.get(position).getUID();
                String name = mClassList.get(position).getName();
                mView.setClassName(name);
                queryStudent(mView.getEditSearch());
                dropView.dismiss();
            }
        });
        dropView.showAsDropDown(view);
    }

}
