package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        } else if (products.isEmpty()) {
            products.put(product, quantity);
        } else {
            boolean status = false;
            for (Product p : products.keySet()) {
                String productName = product.getName();
                String newProductName = p.getName();
                if (productName.equals(newProductName)) {
                    status = true;
                    int newProductQuantity = products.get(p) + quantity;
                    products.replace(p, newProductQuantity);
                }
            }
            if (!status) {
                products.put(product, quantity);
            }
        }
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

	public String getProductsList() {
		String productsList = "Numer faktury: " + getNumber() + "\n";
        for (Product product : products.keySet()) {
            productsList = productsList + product.getName() + ", " + products.get(product) + ", " + product.getPrice() + "\n";
        }
        productsList = productsList + "Liczba pozycji: " + products.size();
        return productsList;
	}
}
