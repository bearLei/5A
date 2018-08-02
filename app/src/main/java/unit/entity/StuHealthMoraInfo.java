package unit.entity;

import java.util.List;

/**
 * Created by ${lei} on 2018/8/2.
 */
public class StuHealthMoraInfo {
    private String EventName;
    private List<StuHealthMor> stuMor;

    public StuHealthMoraInfo() {
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public List<StuHealthMor> getStuMor() {
        return stuMor;
    }

    public void setStuMor(List<StuHealthMor> stuMor) {
        this.stuMor = stuMor;
    }
}
