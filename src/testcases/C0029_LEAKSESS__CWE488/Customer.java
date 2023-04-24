package testcases.C0029_LEAKSESS__CWE488;


public class Customer {
	private String name;
	private String phone;
	   
	public Customer() {}
	
	public Customer(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}    
}

//public class Customer {
//	   private static Customer cust;
//	   private String name;
//	   private String phone;
//	   
//       public static Customer getInstance() {
//    	   if ( cust == null ) {
//    		   cust  = new Customer();
//    	   }
//    	   return cust;
//       }
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getPhone() {
//		return phone;
//	}
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}    
//	
//	
//}
