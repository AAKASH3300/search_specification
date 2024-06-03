package com.demo.predicate_specification.repository;

import com.demo.predicate_specification.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    Student findByName(String name);

    List<Student> findByAddressCity(String city);

    List<Student> findBySubjectsName(String subjectName);
}
