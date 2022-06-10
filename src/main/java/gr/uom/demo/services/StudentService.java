package gr.uom.demo.services;

import gr.uom.demo.models.Student;
import gr.uom.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void createStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String email, int age) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Student with id "+ id +" doesn't exist!"
        ));

        if(email != null && !email.isBlank() && !email.equals(student.getEmail())){
            if(studentRepository.findStudentByEmail(email).isPresent()){
                throw new IllegalStateException("Email "+ email +" is already taken");
            }
            student.setEmail(email);
        }

        if(age != 0) {
            student.setAge(age);
        }
    }
}
