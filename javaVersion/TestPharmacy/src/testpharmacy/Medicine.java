package testpharmacy;

public class Medicine {
    
        private String name; // name of the medicine, use small letters
	private String composition; // active element of the medicine, use small lettres
	private int dose; // dose of the medicine in mg, default is 1000
	private double price; // price of the medicine, default is 10
	private int quantity; // quantity of the medicine, default is 0
	
	// default constructor
	public Medicine () {
		name= "";
		composition= "";
		dose= 1000;
		price= 10.0;
		quantity= 0;
		
	}
	
	// with-arg constructors
	public Medicine (String name, String composition, int dose) {
		this.name= name;
		this.composition= composition;
		this.dose= dose;
		price= 10.0;
		quantity= 0;
	}
	
	public Medicine (String name, String composition, int dose, double price, int quantity) {
		this.name= name;
		this.composition= composition;
		this.dose= dose;
		this.price= price;
		this.quantity= quantity;
	}
	
	// getters and setters
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name= name;
	}
	
	public String getComposition() {
		return composition;
	}
	
	public void setComposition(String composition) {
		this.composition= composition;
	}

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// toString: description of the medicine
	public String toString() {
		return("name: " + name + 
				"\ncomposition: " + composition +
				"\ndose: " + dose + "mg" + 
				"\nprice: " + price +
				"\nquantity: " + quantity);
	}

	// equals method: true same name and dose, false otherwise
	public boolean equals(Medicine medicine){
		if ((medicine.getName() == name) && (medicine.getDose() == dose)) {
			return true;
		}
		
		else {
			return false;
		}
	}
}