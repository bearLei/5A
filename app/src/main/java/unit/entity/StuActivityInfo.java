package unit.entity;

import java.util.List;

/**
 * Created by ${lei} on 2018/8/2.
 */
public class StuActivityInfo {
    private List<StuMoraInfo> stuMoraInfo;

    public StuActivityInfo() {
    }

    public List<StuMoraInfo> getStuMoraInfo() {
        return stuMoraInfo;
    }

    public void setStuMoraInfo(List<StuMoraInfo> stuMoraInfo) {
        this.stuMoraInfo = stuMoraInfo;
    }
}
