/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentjpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Student;

/**
 *
 * @author cld
 */
public class StudentJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student std = new Student(1,"Dolphin",4.00);
        Student std2 = new Student(2,"Dolphin",4.00);
        Student std3 = new Student(3,"Dolphin",3.99);
        Student std4 = new Student(4,"Dolphin",4.00);
        StudentTable.insertStudent(std);
        StudentTable.insertStudent(std2);
        StudentTable.insertStudent(std3);
        StudentTable.insertStudent(std4);
        
//        persist(std);
       
       System.out.println("SHOW ALL");
       List<Student> stdList = StudentTable.findAllStudent();
       printAllStudent(stdList);
       
       System.out.println("\nAFTER UPDATE id = 3 gpa form 3.99 to 4.00");
       std3 = new Student(3,"Dolphin",4.00);
       StudentTable.updateStudent(std3);
       stdList = StudentTable.findAllStudent();
       printAllStudent(stdList);
       
       System.out.println("\nREMOVED ALL DATA.");
       StudentTable.removeStudent(std);
       StudentTable.removeStudent(std2);
       StudentTable.removeStudent(std3);
       StudentTable.removeStudent(std4);
       // will appear nothing cause already removed all of tha data.
       stdList = StudentTable.findAllStudent();
       printAllStudent(stdList);
    }
    public static void printAllStudent(List<Student> StudentList) {
        for(Student std : StudentList) {
           System.out.print(std.getId() + " ");
           System.out.print(std.getName() + " ");
           System.out.println(std.getGpa() + " ");
       }
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
