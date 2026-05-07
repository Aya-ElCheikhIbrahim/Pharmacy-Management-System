package testpharmacy;

public class Prescription extends Medicine {

    private String doctorSpecialization; //the specialization of the doctor prescribing this medicine.     

    //Default Constructor
    public Prescription (){
        super();
        doctorSpecialization="";
    }
//with-arg Constructor
    public Prescription(String name, String composition, int dose, double price, int quantity, String doctorsp) {
        super(name, composition, dose, price, quantity);
        this.doctorSpecialization = doctorsp;
    }

    //setters and getters  
    
    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }
    
    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }
    
    //toString: description of the medicine
    @Override
    public String toString() {
        return super.toString()+"\ndoctorSpecialization: " + doctorSpecialization;
    }
}
