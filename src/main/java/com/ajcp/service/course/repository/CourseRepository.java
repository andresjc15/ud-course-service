package com.ajcp.service.course.repository;

import com.ajcp.service.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from  Course c join fetch c.students a where a.id=?1")
    public Course findCourseByStudentById(Long id);

}
