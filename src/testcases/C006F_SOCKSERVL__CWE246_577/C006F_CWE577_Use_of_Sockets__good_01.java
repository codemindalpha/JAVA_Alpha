import java.io.IOException;
import java.math.BigDecimal;

import javax.ejb.Stateless;

public class C006F_CWE577_Use_of_Sockets__good_01 {
	/*

	by juho 17.12.11
	## The following Java example is a simple stateless Enterprise JavaBean that retrieves stock symbols and stock values. The Enterprise JavaBean creates a socket and listens for and accepts connections from clients on the socket.
	*/
	@Stateless
	public class Good implements StockSymbolRemote {

		public Good() {
			//Use other interface
			int action;
		}
		public String getStockSymbol(String name) { return "s"; }
		public BigDecimal getStockValue(String symbol) { return new BigDecimal("1.0"); }
		private void processClientInputFromSocket() { int action; }
	}
	public interface StockSymbolRemote{ }
}
