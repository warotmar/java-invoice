package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private HashMap<Product, Integer> products = new HashMap<>();

	public void addProduct(Product product) {
		this.products.put(product, 1); 
	}

	public void addProduct(Product product, Integer quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException();	
		} else {
		this.products.put(product, quantity);
		}
	}

	public BigDecimal getNetPrice() {
		BigDecimal subtotal = new BigDecimal("0");
		
		for (Product product : this.products.keySet()) {
			BigDecimal q = new BigDecimal(this.products.get(product).toString());
			subtotal = subtotal.add(product.getPrice().multiply(q));
			
		}
		return subtotal;
	}

	public BigDecimal getTax() {
		
		BigDecimal tax = new BigDecimal("0");
		
		for (Product product : this.products.keySet()) {
			BigDecimal q = new BigDecimal(this.products.get(product).toString());
			tax = tax.add((product.getPriceWithTax().subtract(product.getPrice())).multiply(q));
			
		}
		
		return tax;
	}

	public BigDecimal getTotal() {
		
		BigDecimal total = new BigDecimal("0");
		
		for (Product product : this.products.keySet()) {
			BigDecimal q = new BigDecimal(this.products.get(product).toString());
			total = total.add(product.getPriceWithTax().multiply(q));
			
		}
		
		return total;
	}
}
