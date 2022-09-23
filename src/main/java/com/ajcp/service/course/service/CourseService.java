package com.ajcp.service.course.service;

import com.ajcp.service.course.entity.Course;
import com.ajcp.service.course.entity.Exam;
import com.ajcp.service.course.entity.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<Course> findAll();

    public Optional<Course> findById(Long id);

    public Course save(Course student);

    public Optional<Course> update(Course student);

    public Course delete(Long id);

    public Optional<Course> addStudents(Long courseId, List<Student> students);

    public Optional<Course> removeStudent(Long courseId, Student student);

    public Course findCourseByStudentId(Long id);

    public Optional<Course> addExams(Long courseId, List<Exam> exams);

    public Optional<Course> removeExam(Long courseId, Exam exam);

}
