import java.util.ArrayList;
import java.util.List;

public class ProductAnalytics {

    private List<Product> productCatalog;

    public ProductAnalytics(List<Product> productCatalog) {
        this.productCatalog = productCatalog;
    }

    // TODO: Refactor เมธอดทั้งหมดด้านล่างนี้ให้ใช้ Stream API

    /**
     * ค้นหาสินค้าทั้งหมดในหมวดหมู่ที่กำหนด
     */

    public List<Product> findProductsByCategory(String category) {
        return productCatalog
            .stream()
            .filter(p -> p.category().equalsIgnoreCase(category))
            .toList();
    }

    /**
     * คืนค่า "ชื่อ" ของสินค้าทั้งหมดที่มีราคาต่ำกว่าที่กำหนด
     */
    public List<String> getProductNamesWithPriceLessThan(double maxPrice) {
        return productCatalog
            .stream()
            .filter(p -> p.price() < maxPrice)
            .map(p -> p.name())
            .toList();
    }

    /**
     * คำนวณมูลค่ารวมของสต็อกสินค้าในหมวดหมู่ที่กำหนด
     */
    public double calculateTotalStockValueForCategory(String category) {
        double totalPrice = productCatalog
            .stream()
            .filter(p -> p.category().equalsIgnoreCase(category))
            .map(p -> p.price() * p.stock())
            .reduce(0.0, (total, price) -> total + price);

        return totalPrice;
    }

    /**
     * ตรวจสอบว่ามีสินค้าที่หมดสต็อก (stock = 0) หรือไม่
     */
    public boolean hasProductOutOfStock() {
        boolean isHasInStock = false;

        List<Product> filterProductCatalog = productCatalog
            .stream()
            .filter(p -> p.stock() == 0)
            .toList();

        if (filterProductCatalog.size() > 0) isHasInStock = true;

        return isHasInStock;
    }
}
