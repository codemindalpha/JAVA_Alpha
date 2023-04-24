package CF00C_PASSMUTOBJ__CWE374;

public class PASSMUTOBJ_BAD {
    public class BookShop {
        private BookShopInventory inven;
        private SalesDBManager sales;
        //...
        // constructor for BookStore
        public BookShop() {
            this.inven = new BookShopInventory();
            this.sales = new SalesDBManager();
                //...
        }
        public void updateSalesAndInventoryForBookSold(String bookISBN) {

            // Get book object from inventory using ISBN
            Book book = inven.getBookISBN(bookISBN);
            // update sales information for book sold
            sales.updateSalesInformation(book);
            // update inventory
            inven.updateInventory(book);
        }
        // other BookShop methods
    }
    public class Book {
        private String title;
        private String author;
        private String isbn;
        // ...
    }
    class BookShopInventory{
        public void updateInventory(Book book) {
        }

        public Book getBookISBN(String bookISBN) {
            return null;
        }
    }
    class SalesDBManager{

        public void updateSalesInformation(Book book) {
        }
    }
}
