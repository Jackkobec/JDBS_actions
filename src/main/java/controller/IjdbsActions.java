package controller;


import model.Group;
import model.Lesson;
import model.Prepod;
import model.Student;

import java.util.List;

/**
 * Created by Jack on 12.11.2016.
 */
public interface IjdbsActions<T> {

    List<T> getAll(T entity);

    T getOne(T t);

    void updateInfo();

    List<Student> getStudentsByGroup(Group group);

    List<Group> getGroupsWhoLearnMath(Lesson math);

    List<Group> getGroupsWithAllLessons();

    Prepod getPrepodWithHighExp();

    Prepod getPrepodWithLowhExp();

    List<Prepod> gerPrepodsWithMore3yearsExp();

    List<Lesson> getGymanitaryLessons();

    double getAvaragePoint(Lesson fizika, Group group);

    double getAvaragePointAll(Lesson fizika, List<Group> groups);

    Group showGroupWithMore3WhoLernFilosofy();


}
