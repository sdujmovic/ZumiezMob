package zmobile.utils;

public enum ProductCatalogProd {

	PRIMITIVE_HAT("Primitive Passage Black Snapback Hat", "$31.95"), NIKE_BAG("Nike RPM Black Backpack", "$101.95");

	private final String label;
	private final String price;

	ProductCatalogProd(String label, String price) {
		this.label = label;
		this.price = price;
	}

	public String getLabel() {
		return label;
	}

	public String getPrice() {
		return price;
	}

}
