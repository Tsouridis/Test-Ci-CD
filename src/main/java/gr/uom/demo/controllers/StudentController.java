package gr.uom.demo.controllers;

import gr.uom.demo.models.Student;
import gr.uom.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping("/students")
    public String createStudent(@RequestBody Student student){
        studentService.createStudent(student);
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not created");
        return "Student created!";
    }

    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable(value = "studentId") Long id){
        studentService.deleteStudent(id);
        return "Deleted!";
    }

    //localhost:8080/students/1?email=nikos@gmail.com
    //localhost:8080/students/1?age=5
    //localhost:8080/students?id=1&age=5&email=nikos@gmail.com
    @PutMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long id,
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) String age){
        int intAge=0;
        if(age!=null){
            intAge= Integer.parseInt(age);
        }
        studentService.updateStudent(id, email, intAge);
        return "Updated!";
    }
}
