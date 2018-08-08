package unit.entity;

/**
 * Created by ${lei} on 2018/8/8.
 */
public class StuLessEvent {
    private String StudentUID;
    private String StudentName;
    private int EventCount;

    public StuLessEvent() {
    }

    public String getStudentUID() {
        return StudentUID;
    }

    public void setStudentUID(String studentUID) {
        StudentUID = studentUID;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public int getEventCount() {
        return EventCount;
    }

    public void setEventCount(int eventCount) {
        EventCount = eventCount;
    }
}
