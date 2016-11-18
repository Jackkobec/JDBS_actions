package model;

/**
 * Created by Jack on 12.11.2016.
 */
public class Prepod {

    private int id;
    private String name;
    private int experience;
    private int subject_id;//(Один препод ведет один предмет)


    public Prepod() {
    }

    public Prepod(int id, String name, int experience, int subject_id) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.subject_id = subject_id;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    @Override
    public String toString() {
        return "Prepod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", subject_id=" + subject_id +
                '}';
    }
}
