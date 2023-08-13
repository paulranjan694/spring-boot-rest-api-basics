package com.example.springbootrestapi.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    /*
     * http://localhost:8080/student
     * return Student or Java Bean
     * */
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1,"Ranjan","Paul");
        //return new ResponseEntity<>(student, HttpStatus.OK);

        return ResponseEntity.ok().header("custom-header","Ranjan").body(student);
    }

    /*
    * http://localhost:8080/students
    * returns List of Students
    * */
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(1,"Ranjan","Paul"));
        students.add(new Student(2,"Amogh","Rane"));
        students.add(new Student(3,"Pahur","Dahra"));
        students.add(new Student(4,"Tanmesh","Bhokare"));
        students.add(new Student(5,"Hrisikhesh","Misal"));
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    /*
    *
    * Spring boot rest api with path variable
    * {id} - URI Template variable
    * http://localhost:8080/students/1
    * */
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id")  int studentId, @PathVariable("first-name") String firstName, @PathVariable("last-name")String lastName){
        return new ResponseEntity<>(new Student(studentId, firstName, lastName), HttpStatus.OK);
    }

    /*
     * Spring boot rest api with Request Param
     * http://localhost:8080/students/query?id=1&firstName=Ranjan&lastName=Paul
     * */
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName){
        return new ResponseEntity<>(new Student(id, firstName, lastName), HttpStatus.OK);
    }

    /*
    * Spring boot rest api that handles HTTP Post Request
    * @PostMapping and @RequestBody
    * http://localhost:8080/students/create
    * */
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    /*
     * Spring boot rest api that handles HTTP PUT Request
     * @PostMapping and @RequestBody
     * http://localhost:8080/students/3/updatẻ
     * */
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody  Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    /*
    * Spring Boot REST API that handles DELETE Request - deleting the existing resource
    * */
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }

}
