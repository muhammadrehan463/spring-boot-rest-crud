package com.practicecode.demo.rest;

import com.practicecode.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudent;

    // to load data only once using postconstruct
    @PostConstruct
    public void loadData(){
        theStudent = new ArrayList<>();

        theStudent.add(new Student("Dawood", "Ismail"));
        theStudent.add(new Student("Furqan", "Khan"));
        theStudent.add(new Student("Ali", "Raza"));
        theStudent.add(new Student("Bilal", "Ahmed"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudent;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){

        if((studentId >= theStudent.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student not found ");
        }
        return theStudent.get(studentId);
    }
}
