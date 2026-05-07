package testpharmacy;

public class OverTheCounter extends Medicine{
    
    private int minAge; //the minimum age for wich this medicine is allowed to be given.
    
    //Default Constructor
    public OverTheCounter(){
        super();
        minAge= 18;
    }
    
    //with-arg Constructor
    public OverTheCounter(String name, String composition, double price, int dose, int quantity, int minAge) {
        super(name, composition, dose, price, quantity);
        this.minAge = minAge;
    }
        
    //setters and getters
    
    public int getMinAge() {
        return minAge;
    }

    public void setminAge(int minAge){
        if (minAge > 0)
            this.minAge = minAge;
        else 
            System.out.println("Minimum age must be positive!");
    }
    
    //toString: descirption of the medicine
    @Override
    public String toString() {
        return super.toString()+"\nminAge: " + minAge;
    }
}