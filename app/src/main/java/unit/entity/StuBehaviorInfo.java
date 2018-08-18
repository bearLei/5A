package unit.entity;

import java.util.List;

/**
 * Created by ${lei} on 2018/8/17.
 */
public class StuBehaviorInfo {

    private List<StuScoreInfo> stuScoreInfo;


    public StuBehaviorInfo() {
    }

    public List<StuScoreInfo> getStuScoreInfo() {
        return stuScoreInfo;
    }

    public void setStuScoreInfo(List<StuScoreInfo> stuScoreInfo) {
        this.stuScoreInfo = stuScoreInfo;
    }
}
