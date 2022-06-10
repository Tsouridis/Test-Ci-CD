package gr.uom.demo;

import gr.uom.demo.models.Student;
import gr.uom.demo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student student = new Student("Maria",
                    "Pa",
                    25,
                    "maria@gmail.com");
            Student student1 = new Student("Eleni",
                    "Pa",
                    22,
                    "eleni@gmail.com");

            studentRepository.save(student);
            studentRepository.save(student1);
        };
    }
}
