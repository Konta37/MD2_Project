import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        // Tạo một UUID ngẫu nhiên
        UUID sku = UUID.randomUUID();

        // Chuyển đổi UUID thành chuỗi
        String skuString = sku.toString();

        // In ra SKU
        System.out.println("SKU: " + skuString);

    }
}
