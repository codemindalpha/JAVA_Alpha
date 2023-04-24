package testcases.C0007_XQUERYI__CWE652;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.xml.namespace.QName;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

public class C0007_XQUERYI__simple_01 {
	public void bad(Properties props) throws NamingException, XQException {
		// 외부로부터 입력을 받음
		String name = props.getProperty("name");
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sunjndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=rootDir");
		javax.naming.directory.DirContext ctx = new InitialDirContext(env);
		javax.xml.xquery.XQDataSource xqds = (javax.xml.xquery.XQDataSource) ctx.lookup("xqj/personnel");
		javax.xml.xquery.XQConnection conn = xqds.getConnection();
		String es = "doc('users.xml')/userlist/user[uname='" + name + "']";
		// 입력값이 XQuery의 인자로 사용
		/* FLAW : CWE-652 */
		XQPreparedExpression expr = conn.prepareExpression(es);
		XQResultSequence result = expr.executeQuery();
		while (result.next()) {
		  String str = result.getAtomicValue();
		  if (str.indexOf('>') < 0) {
		    System.out.println(str);
		  }
		}
	}
	
	public void good(Properties props) throws NamingException, XQException {
		// 외부로부터 입력을 받음
		String name = props.getProperty("name");
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sunjndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=rootDir");
		javax.naming.directory.DirContext ctx = new InitialDirContext(env);
		javax.xml.xquery.XQDataSource xqds = (javax.xml.xquery.XQDataSource) ctx.lookup("xqj/personnel");
		javax.xml.xquery.XQConnection conn = xqds.getConnection();
		String es = "doc('users.xml')/userlist/user[uname='$xpathname']";
		// 입력값이 XQuery의 인자로 사용
		XQPreparedExpression expr = conn.prepareExpression(es);
		// bindString 함수 사용함으로써 외부 입력으로 인한 쿼리구조 변조 방지
		expr.bindString(new QName("xpathname"), name, null);
		XQResultSequence result = expr.executeQuery();
		while (result.next()) {
		  String str = result.getAtomicValue();
		  if (str.indexOf('>') < 0) {
		    System.out.println(str);
		  }
		}
	}
}
