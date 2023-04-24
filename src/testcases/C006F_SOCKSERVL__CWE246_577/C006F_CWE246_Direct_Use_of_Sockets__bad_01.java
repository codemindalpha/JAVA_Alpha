import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C006F_CWE246_Direct_Use_of_Sockets__bad_01 extends HttpServlet {
	String remoteHostname = "s";
	/*
	by juho 17. 12. 11
	## A Socket object is created directly within the Java servlet, which is a dangerous way to manage remote connections.
	*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Perform servlet tasks.
		// ...
		// Open a socket to a remote server (bad).
		Socket sock = null;
		bad(sock);
	}

	public void bad(Socket s){
		try{
    	//FLAW:
			s = new Socket(remoteHostname, 3000);
			// Do something with the socket.
			// ...
		} catch (IOException e) { int action; }
	}

	/* origin
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		…   // Perform servlet tasks.
		// Open a socket to a remote server (bad).
		Socket sock = null;
		try {
			sock = new Socket(remoteHostname, 3000);
			…   // Do something with the socket.
		} catch (Exception e) { ... }
	}
	*/
}
