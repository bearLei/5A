package unit.entity;

import java.util.List;

/**
 * Created by ${lei} on 2018/8/2.
 */
public class StuHealthInfo {

    private List<StuHealthMoraInfo> stuMoraInfo;

    public StuHealthInfo() {
    }

    public List<StuHealthMoraInfo> getStuMoraInfo() {
        return stuMoraInfo;
    }

    public void setStuMoraInfo(List<StuHealthMoraInfo> stuMoraInfo) {
        this.stuMoraInfo = stuMoraInfo;
    }
}
