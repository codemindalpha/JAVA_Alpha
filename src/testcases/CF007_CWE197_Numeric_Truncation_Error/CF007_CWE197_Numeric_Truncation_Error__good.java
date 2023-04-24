package testcases.CF020_CWE1235_Incorrect_Use_of_Autoboxing;

public class CF020_CWE1235_Incorrect_Use_of_Autoboxing__simple {

	public void bad(String productID, int amountSold) {

        // get the total number of products in inventory database
        int productCount = inventory.getProductCount(productID);
        // convert integer values to short, the method for the

        // sales object requires the parameters to be of type short
        short count = (short)productCount;
        short sold = (short)amountSold;
        // update sales database for product
        sales.updateSalesCount(productID, count, sold);
	}

	public void good(String productID, int amountSold) {

        // get the total number of products in inventory database
        int productCount = inventory.getProductCount(productID);
        // make sure that integer numbers are not greater than

        // maximum value for type short before converting
        if ((productCount < Short.MAX_VALUE) && (amountSold < Short.MAX_VALUE)) {

            // convert integer values to short, the method for the

            // sales object requires the parameters to be of type short
            short count = (short) productCount;
            short sold = (short) amountSold;
            // update sales database for product
            sales.updateSalesCount(productID, count, sold);

        }else {
                // throw exception or perform other processing
        }
    }
	
}
