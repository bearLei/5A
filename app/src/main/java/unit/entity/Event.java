package unit.entity;

import java.io.Serializable;

/**
 * Created by lei on 2018/6/22.
 */
public class Event implements Serializable {
    private String EventUID;
    private String Address;
    private String Time;
    private String TimeSpan;
    private String Description;
    private String EventTypeName;
    private String IndexType;
    private String StudentNames;
    private String ClassNames;
    private String Scores;
    private String StatusArr;
    private boolean IsTypical;
    private int Categories;
    private String CategoriesName;
    public Event() {
    }

    public String getEventUID() {
        return EventUID;
    }

    public void setEventUID(String eventUID) {
        EventUID = eventUID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTimeSpan() {
        return TimeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        TimeSpan = timeSpan;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getCategories() {
        return Categories;
    }

    public void setCategories(int categories) {
        Categories = categories;
    }

    public String getCategoriesName() {
        return CategoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        CategoriesName = categoriesName;
    }

    public String getEventTypeName() {
        return EventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        EventTypeName = eventTypeName;
    }

    public String getIndexType() {
        return IndexType;
    }

    public void setIndexType(String indexType) {
        IndexType = indexType;
    }

    public String getStudentNames() {
        return StudentNames;
    }

    public void setStudentNames(String studentNames) {
        StudentNames = studentNames;
    }

    public String getClassNames() {
        return ClassNames;
    }

    public void setClassNames(String classNames) {
        ClassNames = classNames;
    }

    public String getScores() {
        return Scores;
    }

    public void setScores(String scores) {
        Scores = scores;
    }

    public String getStatusArr() {
        return StatusArr;
    }

    public void setStatusArr(String statusArr) {
        StatusArr = statusArr;
    }

    public boolean isTypical() {
        return IsTypical;
    }

    public void setTypical(boolean typical) {
        IsTypical = typical;
    }
}
