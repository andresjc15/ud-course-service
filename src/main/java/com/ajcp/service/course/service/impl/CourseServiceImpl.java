package com.ajcp.service.course.service.impl;

import com.ajcp.service.course.entity.Course;
import com.ajcp.service.course.entity.Exam;
import com.ajcp.service.course.entity.Student;
import com.ajcp.service.course.repository.CourseRepository;
import com.ajcp.service.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course student) {
        return courseRepository.save(student);
    }

    @Override
    @Transactional
    public Optional<Course> update(Course student) {
        return courseRepository.findById(student.getId()).map(s -> {
            s.setName(student.getName());
            s.setUpdateAt(LocalDateTime.now());
            return Optional.of(courseRepository.save(s));
        }).orElse(Optional.empty());
    }

    @Override
    @Transactional
    public Course delete(Long id) {
        return courseRepository.findById(id).map(student -> {
            student.setActive(false);
            return courseRepository.save(student);
        }).orElse(null);
    }

    @Override
    @Transactional
    public Optional<Course> addStudents(Long courseId, List<Student> students) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    students.stream().forEach(st -> {
                        course.addStudent(st);
                    });
                    return Optional.of(courseRepository.save(course));
                })
                .orElse(Optional.empty());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> removeStudent(Long courseId, Student student) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    course.removeStudent(student);
                    return Optional.of(courseRepository.save(course));
                }).orElse(Optional.empty());
    }

    @Override
    @Transactional(readOnly = true)
    public Course findCourseByStudentId(Long id) {
        return courseRepository.findCourseByStudentById(id);
    }

    @Override
    public Optional<Course> addExams(Long courseId, List<Exam> exams) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    exams.stream().forEach(ex -> {
                        course.addExam(ex);
                    });
                    return Optional.of(courseRepository.save(course));
                })
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Course> removeExam(Long courseId, Exam exam) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    course.removeExam(exam);
                    return Optional.of(courseRepository.save(course));
                }).orElse(Optional.empty());
    }
}
