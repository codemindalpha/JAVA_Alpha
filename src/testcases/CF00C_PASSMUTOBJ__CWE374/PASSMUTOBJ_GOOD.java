package CF00C_PASSMUTOBJ__CWE374;

public class PASSMUTOBJ_GOOD {
    public class BookShop {
        private BookShopInventory inven;
        private BookShopInventory inventory;
        private SalesDBManager sales;
        //...
        // constructor for BookStore
        public BookShop() {
            this.inven = new BookShopInventory();
            this.inventory = new BookShopInventory();
            this.sales = new SalesDBManager();
                //...
        }
        public void updateSalesAndInventoryForBookSold(String bookISBN) {

            // Get book object from inventory using ISBN
            Book book = inventory.getBookWithISBN(bookISBN);
            // Create copy of book object to make sure contents are not changed
            Book bookSold = (Book)book.clone();
            // update sales information for book sold
            sales.updateSalesInformation(bookSold);
            // update inventory
            inven.updateInventory(book);
        }
        // other BookShop methods
    }
    public class Book implements Cloneable{
        private String title;
        private String author;
        private String isbn;
        // ...
        public Object clone() {

            try {
                return super.clone();
            }
            catch (java.lang.CloneNotSupportedException e) {

                throw new RuntimeException(e.toString());
            }
        }
    }
    class BookShopInventory{
        public void updateInventory(Book book) {
        }

        public Book getBookWithISBN(String bookISBN) {
            return null;
        }
    }
    class SalesDBManager{

        public void updateSalesInformation(Book book) {
        }
    }
}
