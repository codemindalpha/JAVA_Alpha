package testcases.C0009_LDAP_Injection__CWE90.s01;

import testcasesupport.IO;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.logging.Level;

public class C0009_LDAPI__simple_01 {

    public void bad(String userSN, String userPassword) throws NamingException {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        try {
            DirContext dctx = new InitialDirContext(env);
            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "cn", "mail" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String base = "dc=example,dc=com";
            /* FLAW : CWE-90 */
            String filter = "(&(sn=" + userSN + ")(userPassword=" + userPassword + "))";
            NamingEnumeration<?> results = dctx.search(base, filter, sc);
            while (results.hasMore()) {
                SearchResult sr = (SearchResult) results.next();
                Attributes attrs = sr.getAttributes();
                Attribute attr = attrs.get("cn");
                IO.logger.log(Level.INFO, attr.toString());
            }
            dctx.close();
        } catch (NamingException e) {
            IO.logger.log(Level.WARNING, e.toString());
        }
    }

    public void good(String userSN, String userPassword) throws NamingException {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        try {
            DirContext dctx = new InitialDirContext(env);
            SearchControls sc = new SearchControls();
            String[] attributeFilter = {"cn", "mail" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String base = "dc=example,dc=com";

            // userSN과 userPassword 값에서 LDAP 필터를 조작할 수 있는 문자열을 제거하고 사용
            if (!userSN.matches("[\\w\\s]*") || !userPassword.matches("[\\w]*")) {
                throw new IllegalArgumentException("Invalid input");
            }
            String filter = "(&(sn=" + userSN + ")(userPassword=" + userPassword + "))";
            NamingEnumeration<?> results = dctx.search(base, filter, sc);
            while (results.hasMore()) {
                SearchResult sr = (SearchResult) results.next();
                Attributes attrs = sr.getAttributes();
                Attribute attr = attrs.get("cn");
                IO.logger.log(Level.INFO, attr.toString());
            }
            dctx.close();
        } catch (NamingException e) {
            IO.logger.log(Level.WARNING, e.toString());
        }
    }
}
