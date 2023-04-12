package com.example.demoForJpa.controller;

import com.example.demoForJpa.entity.Student;
import com.example.demoForJpa.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepo studentRepo;

    @PostMapping("/api/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
       System.out.println(student);
       return new ResponseEntity<>(studentRepo.save(student), HttpStatus.CREATED);
    }

    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> getListOfStudents(){

        return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/students/{id}")
        public ResponseEntity<Student> getParticularStudent(@PathVariable long id){

            Optional<Student> student = studentRepo.findById(id);
            if(student.isPresent()){
                return new ResponseEntity<>(student.get(),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

    @PutMapping("/api/students/{id}")
    public ResponseEntity<Student> updateParticularStudent(@PathVariable long id,@RequestBody Student stud){

        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent()){
            student.get().setStudentName(stud.getStudentName());
            student.get().setStudentEmail(stud.getStudentEmail());
            student.get().setStudentAddress(stud.getStudentAddress());

            return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<Void> deleteParticularStudent(@PathVariable long id){

        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent()){
            studentRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
