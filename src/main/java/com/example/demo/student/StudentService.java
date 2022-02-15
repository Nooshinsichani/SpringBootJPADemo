package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void AddNewStudent(Student student) {
        if(studentRepository
                .findStudentByEmail(student.getEmail())
                .isPresent())
        {
            throw new IllegalArgumentException("Email Taken");
        }
        studentRepository.save(student);

    }

    public void deleteStudentById(Long studentId) {
        if(! studentRepository.existsById(studentId)){
            throw new IllegalStateException("Student Id" + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> {throw new IllegalStateException(
                        "Student Id" + studentId + " does not exist");});

            if(name != null && name.length() > 0 && ! Objects.equals(student.getName(),name)){
                student.setName(name);
            }
            if(email != null && email.length() > 0 && ! Objects.equals(student.getEmail(),email)) {
                if(studentRepository.findStudentByEmail(email).isPresent()){
                    throw new IllegalStateException("Email Taken");
                }
                student.setEmail(email);
            }
   }
}
