package unit.entity;

import java.util.List;

/**
 * Created by ${lei} on 2018/8/1.
 */
public class StuCharacterInfo {
    private List<StuCharInfo> stuCharInfo;

    public StuCharacterInfo() {
    }

    public List<StuCharInfo> getStuCharInfo() {
        return stuCharInfo;
    }

    public void setStuCharInfo(List<StuCharInfo> stuCharInfo) {
        this.stuCharInfo = stuCharInfo;
    }

    public class StuCharInfo{
        private String EventName;
        private List <Stup> stuP;

        public StuCharInfo() {
        }

        public String getEventName() {
            return EventName;
        }

        public void setEventName(String eventName) {
            EventName = eventName;
        }

        public List<Stup> getStuP() {
            return stuP;
        }

        public void setStuP(List<Stup> stuP) {
            this.stuP = stuP;
        }
    }

}
