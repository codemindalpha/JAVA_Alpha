import java.io.Serializable;

import javax.servlet.http.HttpSession;

public class C0075_CWE579_Non_serializable_Object_Stored_in_Session__bad_01 {
	/*

	by juho 17.12.11
	## Description
	The application stores a non-serializable object as an HttpSession attribute, which can hurt reliability.

	## Demonstrative Examples
	The following class adds itself to the session, but because it is not serializable, the session can no longer be replicated.

	*/
	public class DataGlobBad {
		String globName;
		String globValue;
		public void bad(HttpSession session) {
			// FLAW:
			session.setAttribute("glob", this);
		}
	}
	public class DataGlobBad2 implements Serializable{
		String globName;
		String globValue;
		public void bad(HttpSession session) {
			// FLAW:
			session.setAttribute("glob", DataGlobBad.class);
		}
	}
	/*
	public class DataGlobGood implements Serializable{
		String globName;
		String globValue;
		public void bad(HttpSession session) {
			session.setAttribute("glob", this);
		}
	}
	*/
}
