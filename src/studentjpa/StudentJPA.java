/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author macintoch
 */
public class StudentJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Student st = new Student(1,"Miki",3.80);
        //StudentTable.insertStudent(st);
        Student st;
        st = StudentTable.findStudentById(1);
        StudentTable.removeStudent(st);
        //StudentTable.updateStudent(st);
        List<Student> stList = StudentTable.findAllStudent();
        printAllStudent(stList);
    }
    public static void printAllStudent(List<Student> studentList) {
        for(Student st : studentList) {
           System.out.print(st.getId() + " ");
           System.out.print(st.getName() + " ");
           System.out.println(st.getGpa() + " ");
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
