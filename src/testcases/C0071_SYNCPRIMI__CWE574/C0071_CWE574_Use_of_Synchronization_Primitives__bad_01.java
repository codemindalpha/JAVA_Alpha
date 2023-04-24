package testcases.C0071_SYNCPRIMI;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class C0071_CWE574_Use_of_Synchronization_Primitives__bad_01 {
	/*

	by juho 17.12.11

	## In the following Java example a Customer Entity EJB provides access to customer information in a database for a business application.
	## However, the customer entity EJB uses the synchronized keyword for the set methods to attempt to provide thread safe synchronization for the member variables. The use of synchronized methods violate the restriction of the EJB specification against the use synchronization primitives within EJBs. Using synchronization primitives may cause inconsistent behavior of the EJB when used within different EJB containers.
	*/

	@Entity
	public class Bad implements Serializable {
		private String id;
		private String firstName;
		private String lastName;
		private Address address;
		public Bad() {   }
		public Bad(String id, String firstName, String lastName) {   }
		@Id
		public String getCustomerId() { return id; }
		// FLAW:
		public synchronized void setCustomerId(String id) { int action; }
		public String getFirstName() { return firstName; }
		// FLAW:
		public synchronized void setFirstName(String firstName) { int action; }
		public String getLastName() { return lastName; }
		// FLAW:
		public synchronized void setLastName(String lastName) { int action; }
		@OneToOne()
		public Address getAddress() { return address; }
		// FLAW:
		public synchronized void setAddress(Address address) { int action; }

		public class Address{  }
	}
}
