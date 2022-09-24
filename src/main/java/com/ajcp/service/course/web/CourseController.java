package com.ajcp.service.course.web;

import com.ajcp.service.course.entity.Course;
import com.ajcp.service.course.entity.Exam;
import com.ajcp.service.course.entity.Student;
import com.ajcp.service.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<?> getCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        return courseService.findById(id)
                .map(student -> ResponseEntity.ok(student))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return this.validate(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return this.validate(result);
        }
        return courseService.update(course)
                .map(obj -> ResponseEntity.ok(obj))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.delete(id));
    }

    @PutMapping("/{id}/add-students")
    public ResponseEntity<?> addStudents(@PathVariable Long id, @RequestBody List<Student> students) {
        return courseService.addStudents(id, students)
                .map(course -> ResponseEntity.ok(course))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/remove-students")
    public ResponseEntity<?> removeStudents(@PathVariable Long id, @RequestBody Student student) {
        return courseService.removeStudent(id, student)
                .map(course -> ResponseEntity.ok(course))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(courseService.findCourseByStudentId(id));
    }

    @PutMapping("/{id}/add-exams")
    public ResponseEntity<?> addExam(@PathVariable Long id, @RequestBody List<Exam> exams) {
        return courseService.addExams(id, exams)
                .map(course -> ResponseEntity.ok(course))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/remove-exam")
    public ResponseEntity<?> removeExam(@PathVariable Long id, @RequestBody Exam exam) {
        return courseService.removeExam(id, exam)
                .map(course -> ResponseEntity.ok(course))
                .orElse(ResponseEntity.notFound().build());
    }

    protected ResponseEntity<?> validate(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
