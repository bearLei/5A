package unit.entity;

import java.util.List;

/**
 * Created by ${lei} on 2018/8/1.
 */
public class StuAcademicLevelInfo {

    private List<StuAcadInfo> stuAcadInfo;

    public StuAcademicLevelInfo() {
    }

    public List<StuAcadInfo> getStuAcadInfo() {
        return stuAcadInfo;
    }

    public void setStuAcadInfo(List<StuAcadInfo> stuAcadInfo) {
        this.stuAcadInfo = stuAcadInfo;
    }

    public class StuAcadInfo{
        private String CourseName;
        private float Score;
        private String ClassTeacherName;

        public StuAcadInfo() {
        }

        public String getCourseName() {
            return CourseName;
        }

        public void setCourseName(String courseName) {
            CourseName = courseName;
        }

        public float getScore() {
            return Score;
        }

        public void setScore(float score) {
            Score = score;
        }

        public String getClassTeacherName() {
            return ClassTeacherName;
        }

        public void setClassTeacherName(String classTeacherName) {
            ClassTeacherName = classTeacherName;
        }
    }
}
