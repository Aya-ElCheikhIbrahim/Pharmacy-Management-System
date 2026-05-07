package testpharmacy;

public class Pharmacy {

    private String name; //name of the Pharmacy
    private Medicine[] medicines;
    private static final int MaxNumberOfMedicines = 100; //fixed maximum number
    private int numberOfMedicines; //number of different medicines in the array.

    //Default Constructor
    public Pharmacy() {
        name = "";
        numberOfMedicines = 0;
    }

    //with-arg Constructor
    public Pharmacy(String name) {
        this.name = name;
        medicines = new Medicine[numberOfMedicines];
        numberOfMedicines = 0;
    }

    //accept new medicine
    public void addMedicine(Medicine newMedicine) {
        if (numberOfMedicines >= MaxNumberOfMedicines) {
            System.out.println("Error! Pharmacy is already full, cannot add any other medicines");
        } else {

            for (int i = 0; i < numberOfMedicines; i++) {
                if (medicines[i].equals(newMedicine)) {
                    System.out.println("The medicine already exists!");
                } else {
                    medicines[numberOfMedicines] = newMedicine;
                    numberOfMedicines++;
                }
            }
        }
    }

    //raise or reduce the price of all the medicines
    public void changePrice(double percentage) {
        double newprice;
        if (percentage < 0) {
            for (int i = 0; i < medicines.length; i++) {
                percentage = Math.abs(percentage);
                newprice = medicines[i].getPrice() * (percentage / 100);
                medicines[i].setPrice(newprice);
            }
        } else {
            for (int i = 0; i < medicines.length; i++) {
                newprice = medicines[i].getPrice() + (medicines[i].getPrice() * percentage / 100);
                medicines[i].setPrice(newprice);
            }
        }
    }

    //return an array of indices of medicines having the same name but different doses
    public void searchByName(String name) {

        for (int i = 0; i < medicines.length; i++) {
            if (medicines[i].getName() == name) {
                System.out.println("name: " + medicines[i].getName() + "\ncomposition: " + medicines[i].getComposition() + "\ndose: " + medicines[i].getDose() + "mg" + "\nprice: " + medicines[i].getPrice() + "\nquantity: " + medicines[i].getQuantity());
            }
        }

    }

    public void searchByNameAndDose(String name, int dose) {
        for (int i = 0; i < medicines.length; i++) {
            if (medicines[i].getName() == name && medicines[i].getDose() == dose) {
                System.out.println("name: " + medicines[i].getName() + "\ncomposition: " + medicines[i].getComposition() + "\ndose: " + medicines[i].getDose() + "mg" + "\nprice: " + medicines[i].getPrice() + "\nquantity: " + medicines[i].getQuantity());

            }

        }
    }

    //
    public void searchByComposition(String composition) {

        for (int i = 0; i < medicines.length; i++) {
            if (medicines[i].getComposition() == composition) {
                System.out.println("name: " + medicines[i].getName() + "\ncomposition: " + medicines[i].getComposition() + "\ndose: " + medicines[i].getDose() + "mg" + "\nprice: " + medicines[i].getPrice() + "\nquantity: " + medicines[i].getQuantity());

            }
        }
    }

    public String sellMedicine(String name, int dose, int quantity) {
        int newquantity;
        for (int i = 0; i < medicines.length; i++) {
            if (name == medicines[i].getName() && dose == medicines[i].getDose()) {
                if (quantity > medicines[i].getQuantity()) {
                    return "Available quantity is not enough";
                } else {
                    newquantity = medicines[i].getQuantity() - quantity;
                    medicines[i].setQuantity(newquantity);
                    return "The selling is successful !";
                }
            }
        }
        return "The medicine is not found";
    }

    //restock the quantity for available medicines
    public boolean restock(String name, int dose, int quantity) {

        int newquantity;
        for (int i = 0; i < medicines.length; i++) {
            if (name == medicines[i].getName() && dose == medicines[i].getDose()) {
                newquantity = medicines[i].getQuantity() + quantity;
                medicines[i].setQuantity(newquantity);
                return true;
            }
        }

        return false;

    }

    //getters and setters
    public int getNumberOfMedicines() {
        return numberOfMedicines;
    }

    public void setNumberOfMedicines(int numberOfMedicines) {
        this.numberOfMedicines = numberOfMedicines;
    }

    public Medicine[] getMedicines() {
        return medicines;
    }

    public static int getMaxnumberofmedicines() {
        return MaxNumberOfMedicines;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pharmacy: " + name
                + "\nNumber Of Medicines: " + numberOfMedicines;
    }
}