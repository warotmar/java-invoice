package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private HashMap<Product, Integer> products = new HashMap<>();

	public void addProduct(Product product) {
		products.put(product, 0);
	}

	public void addProduct(Product product, Integer quantity) {
		products.put(product, quantity);
	}

	public BigDecimal getSubtotal() {
		BigDecimal subtotal = new BigDecimal("0");
		
		for (Product product : products.keySet()) {
			BigDecimal q = new BigDecimal(products.get(product).toString());
			subtotal.add(product.getPrice().multiply(q));
			
		}
		return subtotal;
	}

	public BigDecimal getTax() {
		return null;
	}

	public BigDecimal getTotal() {
		return null;
	}
}
