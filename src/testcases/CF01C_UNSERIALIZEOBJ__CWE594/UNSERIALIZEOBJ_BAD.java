package CF01C_UNSERIALIZEOBJ__CWE594;

import javax.mail.Address;

public class UNSERIALIZEOBJ_BAD {
    @Entity
    public class Customer {
        private String id;
        private String name;
        private Address addr;

        public Customer() {
        }

        public Customer(String id, String name) {

        }

        @Id
        public String getCustomerId() {

            return null;
        }

        public void setCustomerId(String id) {

        }

        public String getName() {

            return null;
        }

        public void setName(String name) {

        }

        @OneToOne()
        public Address getAddress() {

            return null;
        }

        public void setAddress(Address addr) {

        }
    }
}
