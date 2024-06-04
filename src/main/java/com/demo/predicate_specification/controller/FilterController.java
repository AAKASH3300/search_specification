package com.demo.predicate_specification.controller;

import com.demo.predicate_specification.dto.RequestDto;
import com.demo.predicate_specification.entity.Student;
import com.demo.predicate_specification.repository.StudentRepository;
import com.demo.predicate_specification.service.FiltersSpecificationService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FiltersSpecificationService<Student> studentFiltersSpecification;

    @GetMapping("/{name}")
    public Student getStdByName(@PathVariable(name = "name") String name) {
        return studentRepository.findByName(name);
    }

    @GetMapping("/city/{CITY}")
    public List<Student> getStdByCity(@PathVariable(name = "CITY") String city) {
        return studentRepository.findByAddressCity(city);
    }

    @GetMapping("/subject/{SUB}")
    public List<Student> getStdBySubject(@PathVariable(name = "SUB") String subject) {
        return studentRepository.findBySubjectsName(subject);
    }

//Specification -> reusable
//    @PostMapping("/specification")
//    public List<Student> getStudents() {
//        Specification<Student> specification = new Specification<Student>() {
//
//            @Override
//            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.equal(root.get("name"), "Aakash");
//            }
//        };
//
//        List<Student> all = studentRepository.findAll(specification);
//        return all;
//    }

    @PostMapping("/specification")
    public List<Student> getStudents(@RequestBody RequestDto requestDto) {
        Specification<Student> searchSpecification = studentFiltersSpecification.getSearchSpecification(requestDto.getSearchRequestDto());
        return studentRepository.findAll(searchSpecification);
    }


}
