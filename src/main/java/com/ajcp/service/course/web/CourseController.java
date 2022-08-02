package com.ajcp.service.course.web;

import com.ajcp.service.course.entity.Course;
import com.ajcp.service.course.entity.Student;
import com.ajcp.service.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        return courseService.findById(id)
                .map(student -> ResponseEntity.ok(student))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Course course) {
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

}
