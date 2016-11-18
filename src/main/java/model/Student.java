package model;

/**
 * Created by Jack on 12.11.2016.
 */
public class Student {
    private int id;
    private String name;
    private int group_id;

    public Student() {
    }

    public Student(int id, String name, int group_id) {
        this.id = id;
        this.name = name;
        this.group_id = group_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group_id=" + group_id +
                '}';
    }
}
