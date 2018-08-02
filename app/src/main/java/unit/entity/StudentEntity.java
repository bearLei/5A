package unit.entity;

import java.util.List;

/**
 * Created by lei on 2018/6/14.
 */

public class StudentEntity {
    private List<Student> Users;
    private String Word;
    public StudentEntity() {
    }

    public List<Student> getUsers() {
        return Users;
    }

    public void setUsers(List<Student> users) {
        Users = users;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }
}
