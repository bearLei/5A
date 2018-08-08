package unit.entity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lei on 2018/7/22.
 */

public class PutiUnUsedEntity {
    private HashMap<String,Integer> termList;
    private HashMap<String,Integer> monthList;
    private HashMap<String,Integer> weekList;

    public PutiUnUsedEntity() {
    }

    public HashMap<String, Integer> getTermList() {
        return termList;
    }

    public void setTermList(HashMap<String, Integer> termList) {
        this.termList = termList;
    }

    public HashMap<String, Integer> getMonthList() {
        return monthList;
    }

    public void setMonthList(HashMap<String, Integer> monthList) {
        this.monthList = monthList;
    }

    public HashMap<String, Integer> getWeekList() {
        return weekList;
    }

    public void setWeekList(HashMap<String, Integer> weekList) {
        this.weekList = weekList;
    }
}
