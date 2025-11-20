package zmobile.utils;

public enum ProductCatalog {

	PRIMITIVE_HAT("Primitive Altar Brown Snapback Hat", "$31.95"),
	EMPYRE_PANTS("Empyre Loose Fit Khaki Cargo Skate Pants", "$64.95"),
	EMPYRE_SWEATPANTS("Empyre Simone Green Flare SweatPants", "$29.99"),
	EMPYRE_SWEATER("Empyre Dehya Patchwork Green Crop Sweater", "$44.95"),
	VANS_HAT("VANS TRIPPY FLORAL BUCKET HAT", "$21.99");

	private final String label;
	private final String price;

	ProductCatalog(String label, String price) {
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
