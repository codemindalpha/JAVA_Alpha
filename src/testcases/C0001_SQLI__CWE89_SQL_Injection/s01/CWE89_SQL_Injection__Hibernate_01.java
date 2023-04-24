package testcases.C0001_SQLI__CWE89_SQL_Injection.s01;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CWE89_SQL_Injection__Hibernate_01 {
	private static SessionFactory factory = new Configuration().buildSessionFactory();
	
	public void bad(HttpServletRequest req) {
		Session session = factory.openSession();
		String name = req.getParameter("name");
		Query query = session.createQuery("from Student where Name = '" + name + "'");
		query.list();
	}
	
	public void good(HttpServletRequest req) {
		Session session = factory.openSession();
		String name = req.getParameter("name");
		Query query = session.createQuery("from Student where Name = ?");
		query.setString(0, name);
		query.list();
	}
}
