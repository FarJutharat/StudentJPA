/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentjpa;
import studentjpa.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author macintoch
 */
public class StudentTable {
    public static void insertStudent(Student st) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(st);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static void updateStudent(Student st) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentJPAPU");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, st.getId());
        fromDb.setName(st.getName());
        fromDb.setGpa(st.getGpa());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void removeStudent(Student st) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentJPAPU");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, st.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb); //
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }           
    }
     public static List<Student> findAllStudent() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentJPAPU");
        EntityManager em = emf.createEntityManager();
        List<Student> stList = (List<Student>) em.createNamedQuery("Student.findAll").getResultList();
        em.close();
        return stList;
    }
      public static Student findStudentById(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentJPAPU");
        EntityManager em = emf.createEntityManager();
        Student st = em.find(Student.class, id);
        em.close();
        return st;
    }
}
