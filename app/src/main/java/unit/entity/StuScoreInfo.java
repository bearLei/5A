package unit.entity;

import java.util.List;

/**
 * Created by ${lei} on 2018/8/17.
 */
public class StuScoreInfo {
    private String IndexTypeName;
    private List<IndexInfo> Index;

    public StuScoreInfo() {
    }

    public String getIndexTypeName() {
        return IndexTypeName;
    }

    public void setIndexTypeName(String indexTypeName) {
        IndexTypeName = indexTypeName;
    }

    public List<IndexInfo> getIndex() {
        return Index;
    }

    public void setIndex(List<IndexInfo> index) {
        Index = index;
    }

    public class IndexInfo{
        private String TypeName;
        private String IndexRemark;
        private double Score;
        private String Term;

        public IndexInfo() {
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String typeName) {
            TypeName = typeName;
        }

        public String getIndexRemark() {
            return IndexRemark;
        }

        public void setIndexRemark(String indexRemark) {
            IndexRemark = indexRemark;
        }

        public double getScore() {
            return Score;
        }

        public void setScore(double score) {
            Score = score;
        }

        public String getTerm() {
            return Term;
        }

        public void setTerm(String term) {
            Term = term;
        }
    }
}
