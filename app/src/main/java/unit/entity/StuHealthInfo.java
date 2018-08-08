package unit.entity;

import java.util.List;

/**
 * Created by ${lei} on 2018/8/2.
 */
public class StuHealthInfo {

    private List<StuHealthMoraInfo> stuMoraInfo;
    private List<StuHealth> stuHealth;
    public StuHealthInfo() {
    }

    public List<StuHealthMoraInfo> getStuMoraInfo() {
        return stuMoraInfo;
    }

    public void setStuMoraInfo(List<StuHealthMoraInfo> stuMoraInfo) {
        this.stuMoraInfo = stuMoraInfo;
    }

    public List<StuHealth> getStuHealth() {
        return stuHealth;
    }

    public void setStuHealth(List<StuHealth> stuHealth) {
        this.stuHealth = stuHealth;
    }
}
