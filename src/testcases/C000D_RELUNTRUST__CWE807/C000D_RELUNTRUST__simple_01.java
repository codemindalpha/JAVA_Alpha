package testcases.C000D_RELUNTRUST__CWE807;

import testcasesupport.IO;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;

public class C000D_RELUNTRUST__simple_01 {

    public void bad(HttpServletRequest request) {
        String price = "";
        String quantity = "";
        float total = 0;
        try {
            // 서버가 보유하고 있는 가격(단가) 정보를 사용자 화면에서 받아서 처리
            price = request.getParameter("price");
            quantity = request.getParameter("quantity");
            total = Integer.parseInt (quantity) * Float.parseFloat(price);
        } catch (Exception e) {
            IO.logger.log(Level.WARNING, e.toString());
        }
    }

    public void good(HttpServletRequest request) {

        ProductService productService = new ProductService();

        String item = "";
        String price = "";
        String quantity = "";
        float total = 0;

        try {
            item = request.getParameter("item");
            // 가격이 아니라 item 항목을 가져와서 서버가 보유하고 있는 가격 정보를
            // 이용하여 전체 가격을 계산
            price = productService.getPrice(item);
            quantity = request.getParameter("quantity");
            total = Integer.parseInt(price);
        } catch (Exception e) {
            IO.logger.log(Level.WARNING, e.toString());
        }
    }
}

class ProductService {

    private static String[] items;
    private static String price;
    private static String name;
    private static String count;

    public void setItems(String item){
        this.items = item.split(",");
        this.price = items[0];
        this.name = items[1];
        this.count = items[2];
    }

    public void setPrice(int price){
        this.price = Integer.toString(price);
    }

    public String getPrice() {
        return price;
    }

    public String getPrice(String item) {
        return item.split(",")[0];
    }
}