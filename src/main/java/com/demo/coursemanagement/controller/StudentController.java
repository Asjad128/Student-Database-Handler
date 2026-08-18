package com.demo.coursemanagement.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.coursemanagement.entity.Student;
import com.demo.coursemanagement.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/page")
public ResponseEntity<Page<Student>> getStudentsPage(
        @RequestParam int page,
        @RequestParam int size) {

    Pageable pageable = PageRequest.of(page, size);

    return ResponseEntity.ok(studentService.getStudents(pageable));

}
// Sort Students
@GetMapping("/sort")
public ResponseEntity<List<Student>> sortStudents(
        @RequestParam String field) {

    return ResponseEntity.ok(
            studentService.getStudentsSorted(field));

}
//serch students by name
@GetMapping("/name/{name}")
public ResponseEntity<List<Student>> getByName(
        @PathVariable String name) {

    return ResponseEntity.ok(
            studentService.getStudentsByName(name));

}
//serch students by city
@GetMapping("/city/{city}")
public ResponseEntity<List<Student>> getByCity(
        @PathVariable String city) {

    return ResponseEntity.ok(
            studentService.getStudentsByCity(city));

}
//serch students by email
@GetMapping("/email/{email}")
public ResponseEntity<Student> getByEmail(
        @PathVariable String email) {

    return ResponseEntity.ok(
            studentService.getStudentByEmail(email));

}

    // Create Student
    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {

        Student savedStudent = studentService.saveStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // Get All Students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        List<Student> students = studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }

    // Get Student By ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {

        Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    // Update Entire Student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        Student updatedStudent = studentService.updateStudent(id, student);

        return ResponseEntity.ok(updatedStudent);
    }

    // Update Only Student City
    @PatchMapping("/{id}/city")
    public ResponseEntity<Student> updateCity(
            @PathVariable Long id,
            @RequestParam String city) {

        Student updatedStudent = studentService.updateStudentCity(id, city);

        return ResponseEntity.ok(updatedStudent);
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student deleted successfully");
    }

}