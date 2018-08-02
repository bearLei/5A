package unit.entity;

import com.puti.education.widget.ListViewForScrollView;

import java.util.List;

/**
 * Created by ${lei} on 2018/8/2.
 */
public class StuMoraInfo {
    private String GroupName;
    private List<StuMor> stuMor;

    public StuMoraInfo() {
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public List<StuMor> getStuMor() {
        return stuMor;
    }

    public void setStuMor(List<StuMor> stuMor) {
        this.stuMor = stuMor;
    }
}
