package unit.entity;

/**
 * Created by ${lei} on 2018/8/2.
 */
public class StuMor {
    private String TypeName;
    private double Score;
    private String StartTime;
    private String EndTime;
    private int TermIndex;
    private String term;

    public StuMor() {
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getTermIndex() {
        return TermIndex;
    }

    public void setTermIndex(int termIndex) {
        TermIndex = termIndex;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
