package testcases.C006F_SOCKSERVL;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ejb.Stateless;

public class C006F_CWE577_Use_of_Sockets__good_02 {
	int SOCKET_PORT = 789;
	/*

	by juho 17.12.11

	## And the following Java example is similar to the previous example but demonstrates the use of multicast socket connections within an Enterprise JavaBean.
	## The previous two examples within any type of Enterprise JavaBean violate the EJB specification by attempting to listen on a socket, accepting connections on a socket, or using a socket for multicast.
	*/
	@Stateless
	public class Bad extends Thread implements StockSymbolRemote {
		boolean listening = false;

		public Bad() {
			//Use other interface
			int action;
		}
		public String getStockSymbol(String name) { return "s"; }
		public BigDecimal getStockValue(String symbol) { return new BigDecimal("1.0"); }
		public void run() {
			//Use other interface
			int action;
		}
	}

	public interface StockSymbolRemote{ }

}
