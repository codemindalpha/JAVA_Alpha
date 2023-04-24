package testcases.C0072_USEAWT;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.ejb.Stateless;

public class C0072_CWE575_Use_of_AWT_Swing__good_01 {
	/*

	by juho 17.12.11


	## This converter class demonstrates the improper practice of using a stateless session Enterprise JavaBean that implements an AWT Component and AWT keyboard event listener to retrieve keyboard input from the user.
	## This use of the AWT and Swing APIs within any kind of Enterprise JavaBean not only
	violates the restriction of the EJB specification against using AWT or Swing within an EJB but also	violates the intended use of Enterprise JavaBeans to separate business logic from presentation logic.
	*/
	/*
	@Stateless
	public class ConverterSessionBeanBad extends Component implements KeyListener, ConverterSessionRemote {
		// member variables for receiving keyboard input using AWT API
		private StringBuffer enteredText = new StringBuffer();
		// conversion rate on US dollars to Yen
		private BigDecimal yenRate = new BigDecimal("115.3100");
		public ConverterSessionBeanBad() {
			super();
			// method calls for setting up AWT Component for receiving keyboard input
			addKeyListener(this);
		}
		public BigDecimal dollarToYen(BigDecimal dollars) {
			BigDecimal result = dollars.multiply(yenRate);
			return result.setScale(2, BigDecimal.ROUND_DOWN);
		}
		// member functions for implementing AWT KeyListener interface
		public void keyTyped(KeyEvent event) { int action; }
		public void keyPressed(KeyEvent e) { int action; }
		public void keyReleased(KeyEvent e) { int action; }
		// member functions for receiving keyboard input and displaying output
		public void paint(Graphics g) { int action; }
	}
	*/

	/*
	## The Stateless Session Enterprise JavaBean should contain only business logic. Presentation logic should be provided by some other mechanism such as Servlets or Java Server Pages (JSP) as in the following Java/JSP example.
	*/
	@Stateless
	public class ConverterSessionBeanGood implements ConverterSessionRemoteInterface {
		/* conversion rate on US dollars to Yen */
		private BigDecimal yenRate = new BigDecimal("115.3100");
		public ConverterSessionBeanGood() { int action; }
		/* remote method to convert US dollars to Yen */
		public BigDecimal dollarToYen(BigDecimal dollars) {
			BigDecimal result = dollars.multiply(yenRate);
			return result.setScale(2, BigDecimal.ROUND_DOWN);
		}
	}

	public interface ConverterSessionRemote{}
	public interface ConverterSessionRemoteInterface{}
	// + JSP
	/*
	<%@ page import="converter.ejb.Converter, java.math.*, javax.naming.*"%>
	<%!
	private Converter converter = null;
	public void jspInit() {
	try {
	InitialContext ic = new InitialContext();
	converter = (Converter) ic.lookup(Converter.class.getName());

	} catch (Exception ex) {
	System.out.println("Couldn't create converter bean."+ ex.getMessage());

	}

	}
	public void jspDestroy() {
	converter = null;

	}

	%>
	<html>
	<head><title>Converter</title></head>
	<body bgcolor="white">
	<h1>Converter</h1>
	<hr>
	<p>Enter an amount to convert:</p>
	<form method="get">
	<input type="text" name="amount" size="25"><br>
	<p>
	<input type="submit" value="Submit">
	<input type="reset" value="Reset">

	</form>
	<%
	String amount = request.getParameter("amount");
	if ( amount != null && amount.length() > 0 ) {
	BigDecimal d = new BigDecimal(amount);
	BigDecimal yenAmount = converter.dollarToYen(d);

	%>
	<p>
	<%= amount %> dollars are <%= yenAmount %> Yen.
	<p>
	<%
	}

	%>

	</body>

	</html>
	*/
}
