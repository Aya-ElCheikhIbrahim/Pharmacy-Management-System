#include <stdio.h>
#include <string.h>

#define MAX_MEDICINES 100
typedef struct {
    char name [50];
    char composition [50];
    int dose;
    double price;
    int quantity;
} Medicine;

typedef struct {
    char name [50];
    Medicine medicines [MAX_MEDICINES];
    int numberOfMedicines;
    int prescritionQuantity;
    int overTheCounterQuantity;
} Pharmacy;

Medicine creatPrescription (){
    Medicine m;
    printf("Enter Prescription Medicine Name: ");
    scanf("%s", m.name);

    printf("Enter Composition: ");
    scanf("%s", m.composition);

    printf("Enter Dose (mg): ");
    scanf("%d", &m.dose);

    printf("Enter Price: ");
    scanf("%lf", &m.price);

    printf("Enter Quantity: ");
    scanf("%d", &m.quantity);

    return m;
}

Medicine createOTC (){
    Medicine m;

    printf ("Enter OTC Medicine Name: ");
    scanf ("%s",m.name);

    printf ("Enter Composition: ");
    scanf ("%s",m.composition);

    printf ("Enter Dose (mg): ");
    scanf ("%d", &m.dose);

    printf ("Enter Price: ");
    scanf ("%lf", &m.price);

    printf ("Enter Quantity: ");
    scanf ("%d", &m.quantity);

    return m;
}

Pharmacy createPharmacy (char n[]){
    Pharmacy p;
    strcpy (p.name, n);
    p.numberOfMedicines = 0;
    p.prescritionQuantity = 0;
    p.overTheCounterQuantity = 0;
    return p;
}

void addMedicine (Pharmacy *ph, Medicine m){
    if (ph->numberOfMedicines >= MAX_MEDICINES){
        printf ("Pharmacy full!\n");
        return;
    }

    ph->medicines [ph->numberOfMedicines ++] = m;

    if (m.dose > 0 )
        ph -> prescritionQuantity ++;
    else
        ph -> overTheCounterQuantity ++;

    printf ("Medicine Added!\n");
}

void raisePrices (Pharmacy *ph){
    double percent;
    printf ("Enter percentage to raise prices: ");
    scanf ("%lf", &percent);

    for (int i=0; i< ph -> numberOfMedicines; i++){
        ph -> medicines [i].price += ph->medicines[i].price * (percent / 100.0);
    }
    printf ("Prices updated!\n");
}

int searchByName(Pharmacy ph, char n[]){
    for (int i=0; i< ph.numberOfMedicines; i++)
        if (strcmp (ph.medicines[i].name, n) == 0)
            return i;
        return -1;
}

int searchByNameandDose(Pharmacy ph, char n[], int dose){
    for (int i=0; i<ph.numberOfMedicines; i++)
            if (strcmp(ph.medicines[i].name, n) == 0 && ph.medicines[i].dose == dose)
            return i;
    return -1;
}

int searchByComposition(Pharmacy ph, char comp[]) {
    for (int i = 0; i < ph.numberOfMedicines; i++)
        if (strcmp(ph.medicines[i].composition, comp) == 0)
            return i;
    return -1;
}

void sellMedicine(Pharmacy* ph) {
    char name[50];
    printf("Enter medicine to sell: ");
    scanf("%s", name);

    int index = searchByName(*ph, name);

    if (index == -1) {
        printf("Not found!\n");
        return;
    }

    if (ph->medicines[index].quantity > 0) {
        ph->medicines[index].quantity--;
        printf("Sold successfully!\n");
    } else {
        printf("Out of stock!\n");
    }
}

int restockMedicine(Pharmacy* ph) {
    char name[50];
    int q;

    printf("Enter medicine name to restock: ");
    scanf("%s", name);

    int index = searchByName(*ph, name);

    if (index == -1) {
        printf("Not found!\n");
        return 0;
    }

    printf("Enter quantity to add: ");
    scanf("%d", &q);

    ph->medicines[index].quantity += q;
    return 1;
}


void displayInfo(Medicine m) {
    printf("\nName: %s", m.name);
    printf("\nComposition: %s", m.composition);
    printf("\nDose: %d mg", m.dose);
    printf("\nPrice: %.2lf", m.price);
    printf("\nQuantity: %d\n", m.quantity);
}

void displayAll(Pharmacy ph) {
    printf("\n--- All Medicines ---\n");
    for (int i = 0; i < ph.numberOfMedicines; i++) {
        printf("\nMedicine #%d:\n", i + 1);
        displayInfo(ph.medicines[i]);
    }
}

int menu() {
    int c;
    printf("\n===== Pharmacy Menu =====\n");
    printf("1. Add Prescription Medicine\n");
    printf("2. Add OTC Medicine\n");
    printf("3. Raise Prices\n");
    printf("4. Search By Name\n");
    printf("5. Sell Medicine\n");
    printf("6. Restock Medicine\n");
    printf("7. Display All\n");
    printf("8. Exit\n");
    printf("Choice: ");
    scanf("%d", &c);
    return c;
}

int main() {
    Pharmacy ph = createPharmacy("My Pharma");
    int choice;
    do {
        choice = menu();
        switch (choice) {
            case 1: {
                Medicine m;
                m = creatPrescription();
                addMedicine(&ph, m);
                break;
            }
            case 2: {
                Medicine m;
                m = createOTC();
                addMedicine(&ph, m);
                break;
            }
            case 3:
                raisePrices(&ph);
                break;
            case 4: {
                char name[50];
                printf("Enter name: ");
                scanf("%s", name);
                int index = searchByName(ph, name);
                if (index == -1)
                    printf("Not found!\n");
                else
                    displayInfo(ph.medicines[index]);
                break;
            }
            case 5:
                sellMedicine(&ph);
                break;
            case 6:
                restockMedicine(&ph);
                break;
            case 7:
                displayAll(ph);
                break;
        }
    } while (choice != 8);
    return 0;
}
