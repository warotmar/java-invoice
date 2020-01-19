package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax) {
		BigDecimal zero = new BigDecimal("0");
		if (name == null || name == "" || price == null || tax == null || price.compareTo(zero) < 0) {
			throw new IllegalArgumentException();	
		} else {
			this.name = name;
			this.price = price;
			this.taxPercent = tax;
		}
	}

	public String getName() {
		return this.name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public BigDecimal getTaxPercent() {
		return this.taxPercent;
	}

	public BigDecimal getPriceWithTax() {	
		return this.price.add(this.price.multiply(this.taxPercent));
	}
}
