package com.demo.coursemanagement.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.coursemanagement.entity.Student;
import com.demo.coursemanagement.exception.ResourceNotFoundException;
import com.demo.coursemanagement.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {

    return studentRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Student with ID " + id + " not found"));
}

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
        .orElseThrow(() ->
        new ResourceNotFoundException(
        "Student with ID " + id + " not found"));

        studentRepository.delete(student);
    }

    public Page<Student> getStudents(Pageable pageable) {
    return studentRepository.findAll(pageable);
}
  
    public List<Student> getStudentsSorted(String field) {

    return studentRepository.findAll(Sort.by(field));

}
public List<Student> getStudentsByName(String name) {
    return studentRepository.findByName(name);
}

public List<Student> getStudentsByCity(String city) {
    return studentRepository.findByCity(city);
}

public Student getStudentByEmail(String email) {
    return studentRepository.findByEmail(email);
}
    
    //update entire student by paganition
    
    public Student updateStudentCity(Long id, String city) {

    Student student = studentRepository.findById(id).orElse(null);

    if (student != null) {
        student.setCity(city);
        return studentRepository.save(student);
    }

    return null;
}
    public Student updateStudent(Long id, Student updatedStudent) {

            Student existingStudent =
            studentRepository.findById(id)
            .orElseThrow(() ->
            new ResourceNotFoundException(
            "Student with ID " + id + " not found"));
    

    if (existingStudent != null) {
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setCity(updatedStudent.getCity());

        return studentRepository.save(existingStudent);
    }

    return null;
}
}