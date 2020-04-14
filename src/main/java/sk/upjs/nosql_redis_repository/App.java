package sk.upjs.nosql_redis_repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import sk.upjs.nosql_data_source.entity.Student;
import sk.upjs.nosql_data_source.persist.DaoFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        StudentRepository repository = context.getBean(StudentRepository.class);
        List<Student>  students = DaoFactory.INSTANCE.getStudentDao().getAll();
        RedisStudent redisStudent = new RedisStudent(students.get(0));
        repository.save(redisStudent);
        List<RedisStudent> found = repository.findByPriezvisko("Najahalovu");
        System.out.println("najdeny student: " + found);
        repository.delete(redisStudent);
        List<RedisStudent> redisStudents = students.stream().map((student -> new RedisStudent(student))).collect(Collectors.toList());
        repository.saveAll(redisStudents);
        System.out.println("pocet studentov: " + repository.count());
        for (RedisStudent s: repository.findAll()
             ) {
            if(s.getStudium().size()>1)
                 System.out.println(s);
        }
        context.close();
    }
}
