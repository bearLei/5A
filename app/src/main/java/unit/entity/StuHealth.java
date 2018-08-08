package unit.entity;

/**
 * Created by ${lei} on 2018/8/7.
 */
public class StuHealth {
    private String Title;
    private int Value;
    private double Score;
    private String Unit;

    public StuHealth() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }
}
