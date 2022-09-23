package com.ajcp.service.course.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private Long capacity;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Student> students;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Exam> exams;

    private boolean isActive;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public Course() {
        this.students = new ArrayList<>();
        this.exams = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void addExam(Exam exam) {
        this.exams.add(exam);
    }

    public void removeExam(Exam exam) {
        this.exams.remove(exam);
    }

}
