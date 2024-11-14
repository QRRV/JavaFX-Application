package com.example.codecademy;

import com.example.codecademy.Domain.Address;
import com.example.codecademy.Domain.Course;
import com.example.codecademy.Domain.Registration;
import com.example.codecademy.Domain.Student;
import com.example.codecademy.Repository.AddressRepository;
import com.example.codecademy.Domain.Certificate;
import com.example.codecademy.Repository.CertificateRepository;
import com.example.codecademy.Repository.CourseRepository;
import com.example.codecademy.Repository.RegistrationRepository;
import com.example.codecademy.Repository.StudentRepository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TestDatabaseConnectionMain {
    public static void main(String[] args) {
        System.out.println("begin database test");

        //CourseRepo
//        CourseRepository course = new CourseRepository();
//        course.updateCourse("spaans","kut taal", "mid", "spaans is een kut taal",null);
//        course.deleteCourse("spaans");
//        course.createCourse("spaans", "kut vak", "high", "scheikunde is een kut vak", "c");
//
//        ArrayList<Course> courses = course.getCourses();
//        courses.forEach(course1 -> {
//            System.out.println(course1.toString());
//        });

        //StudentRepo
//        StudentRepository.deleteStudent("test2@gmail.com");
//        StudentRepository.createStudent("test2@gmail.com", "Matthijs", "2001-11-11", "m");
//        StudentRepository.updateStudent("test2@gmail.com", "test@gmail.com", "Quinn", "2001-11-11", "m");
//        ArrayList<Student> students = StudentRepository.getStudents();
//
        //AddressRepo
//       AddressRepository.createAddress("test@gmail.com", "straatlaan", 69, "a", "2343DE", "Dorp", "Nederland");
//        AddressRepository.updateAddress("test@gmail.com","test@gmail.com", "straatlaanweg", 69, "a", "2343DE", "Dorp", "Nederland");
//        AddressRepository.deleteAddress("test@gmail.com");
//        ArrayList<Address> addresses = AddressRepository.getAddresses();
//        addresses.forEach(address -> {
//            System.out.println(address.toString());
//        });
//        System.out.println(AddressRepository.getAddressByEmail("test@gmail.com"));

        //RegistrationRepo
//        RegistrationRepository.createRegistration("matthijs@gmail.com", "wiskunde", "2022-12-21", null);
//        ArrayList<Registration> registrations2 = RegistrationRepository.getRegistrations();
//        registrations2.forEach(address -> {
//            System.out.println(address.toString());
//        });
//        RegistrationRepository.updateRegistration("matthijs@gmail.com","matthijs@gmail.com", "c", "2022-12-22");
//        RegistrationRepository.deleteRegistration("matthijs@gmail.com", "c", "2022-12-22");
//        ArrayList<Registration> registrations = RegistrationRepository.getRegistrations();
//        registrations.forEach(address -> {
//            System.out.println(address.toString());
//        });
//        System.out.println(RegistrationRepository.getRegistrationsByEmail("quinn@gmail.com"));
//      CertificateRepository certificate = new CertificateRepository();
//      ArrayList<Certificate> certificates = certificate.getCertificates();
//      courses.forEach(course1 -> {
//          System.out.println(course1.toString());
//      });
//      certificate.deleteCertificate(3233);
//      certificates.forEach(certicate1 -> {
//      System.out.println(certicate1.toString());
//      });


      //  certificate.updateCertificate(3233,7,"octo tortelinnie");

    }
}
