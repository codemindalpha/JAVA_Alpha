package com.example.kotlin.C0014_STORECLEAR;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.net.PasswordAuthentication;


public class C0014_STORECLEAR__GetPassword
{
    public void bad() throws IOException {
		try {
			Socket s = new Socket("taranis", 4444);
			PrintWriter o = new PrintWriter(s.getOutputStream(), true);
			// 패스워드를 평문으로 전송하여 안전하지 않다.
			String user= "user";
			String password= "pwd";

			PasswordAuthentication pw = new PasswordAuthentication(user, password.toCharArray());
			String pwd = String.valueOf(pw.getPassword());
			o.write(pwd);

			String sql = " insert into customer(id, pwd, name, ssn, zipcode, addr)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pwd);

			stmt.executeUpdate();
		} catch (IOException e){
			throw new IOException(e);
		}

	}

    public void good() throws IOException {
		// 패스워드를 암호화 하여 전송
		try {
			Socket s = new Socket("taranis", 4444);
			PrintStream o = new PrintStream(s.getOutputStream(), true);
			// 패스워드를 강력한 AES암호화 알고리즘으로 전송하여 사용한다.
			String user= "user";
			String password= "pwd";

			PasswordAuthentication pw = new PasswordAuthentication(user, password.toCharArray());
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			String pwd = String.valueOf(pw.getPassword());
			byte[] encPassword = c.update(pwd.getBytes());
			o.write(encPassword, 0, encPassword.length);
		} catch (IOException e) {
			throw new IOException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}

