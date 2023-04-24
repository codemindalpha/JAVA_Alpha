package testcases.C0001_SQLI__CWE89_SQL_Injection.s01;
/*

https://www.tutorialspoint.com/jpa/jpa_jpql.htm
by juho
*/

import java.awt.print.Book;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

public class C0001_CWE89_SQL_Injection__JPA_API_01 {
   private static EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
   private static EntityManager em = emFactory.createEntityManager();

   public void bad(HttpServletRequest req) {
      // #1
      //FLAW:
      String orderId = java.lang.System.getProperty("path");
      List results = em.createQuery("Select order from Orders order where order.id = " + orderId).getResultList();
      // #2
      //FLAW:
      String author = req.getParameter("author");
      List results2 = em.createNativeQuery("Select * from Books where author = " + author).getResultList();
      // #3
      //FLAW:
      String itemId = req.getParameter("itemId");
      int resultCode = em.createNativeQuery("Delete from Cart where itemId = " + itemId).executeUpdate();
   }

   public void good() {
      // #1
      // Scalar function
      Query query = em.createQuery("Select UPPER(e.ename) from Employee e");
      List<String> list = query.getResultList();
      // Aggregate function
      Query query1 = em.createQuery("Select MAX(e.salary) from Employee e");
      Double result = (Double) query1.getSingleResult();
      // #2 positional parameter in JPQL
      Query jpqlQuery2 = em.createQuery("Select order from Orders order where order.id = ?1");
      List results2 = jpqlQuery2.setParameter(1, "123-ADB-567-QTWYTFDL").getResultList();
      // #3 named parameter in JPQL
      Query jpqlQuery3 = em.createQuery("Select emp from Employees emp where emp.incentive > :incentive");
      List results3 = jpqlQuery3.setParameter("incentive", new Long(10000)).getResultList();
      // #4 named query in JPQL
      // Query named "myCart" being "Select c from Cart c where c.itemId = :itemId"
      Query jpqlQuery4 = em.createNamedQuery("myCart");
      List results4 = jpqlQuery4.setParameter("itemId", "item-id-0001").getResultList();
      // #5 Native SQL
      Query sqlQuery5 = em.createNativeQuery("Select * from Books where author = ?", Book.class);
      List results5 = sqlQuery5.setParameter(1, "Charles Dickens").getResultList();
   }
}
