package unit.entity;

/**
 * Created by ${lei} on 2018/8/2.
 */
public class StuHealthMor {
    private String Name;
    private String TypeName;
    private double Score;

    public StuHealthMor() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
}
