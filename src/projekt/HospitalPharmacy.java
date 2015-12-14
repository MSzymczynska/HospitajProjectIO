package projekt;

import java.util.ArrayList;
import java.util.Date;

public class HospitalPharmacy{
	
	public HospitalPharmacy() {
		orderedMedicine = new ArrayList<>();
		medicineList = new ArrayList<>();
		pharmacyArchieve = new ArrayList<>();
		madeMedicines = new ArrayList<>();
	}
	
	public ArrayList<Medicine> medicineList;
	private ArrayList<FormField> orderedMedicine;
	private ArrayList<FormField> pharmacyArchieve;
	private ArrayList<Medicine> madeMedicines;
	
	
	public String madeMedicinesToString() {
		return "[madeMedicines=" + madeMedicines + "]";
	}
	public ArrayList<FormField> getOrderedMedicine() {
		ArrayList<FormField> ordered= new ArrayList<FormField>();
		for(int i=0; i<orderedMedicine.size(); i++)
		{
			if(!orderedMedicine.get(i).isDelivered())
				ordered.add(orderedMedicine.get(i));
		}
		return ordered;
	}
	public ArrayList<FormField> getDeliveredMedicine(){
		ArrayList<FormField> delivered= new ArrayList<FormField>();
		for(int i=0; i<orderedMedicine.size(); i++)
		{
			if(orderedMedicine.get(i).isDelivered())
				delivered.add(orderedMedicine.get(i));
		}
		return delivered;
	}
	public ArrayList<Medicine> getPharmacyMedicine(){

		return this.medicineList;
	}
	public ArrayList<FormField> getPharmacyArchieve() {
		return pharmacyArchieve;
	}
	public void orderMedForPatient(int medId, int quantity, int patientId) {
		
		
	}
	public void addMedicineToPharmecyList(int medId, int quantity) {
		
	}
	public void addOrderedMedicine(FormField ff){
		orderedMedicine.add(ff);
	}
	public ArrayList<Medicine> getMadeMedicines() {
		return madeMedicines;
	}
	public void addToMadeMedicineList(Medicine m) {
		madeMedicines.add(m);
	}
	
	public void przyk³adoweDane(){
		int quantity = 0;
		String description = "Lek na g³owê.";
		String instructions = "Bierzesz sk³ 1 * 0.1. Dodajesz sk³ 2 * 1. Mieszasz. Dodajesz sk³ 3 * 0.5 + sk³ 4 * 0.6 i podgrzewasz.";
		ArrayList<String> composition = new ArrayList();
		composition.add("Sk³ 1");
		composition.add("Sk³ 2");
		composition.add("Sk³ 3");
		composition.add("Sk³ 4");
		Medicine m1 = new Medicine(1, "name1", "prod1", null);
		m1.name="Hexodumox";
		m1.setDescription(description);
		m1.setInstructions(instructions);
		m1.setMedicineComposition(composition);
		m1.quantity = quantity;
		m1.setCanCreate(true);
		medicineList.add(m1);
		composition = new ArrayList<>();
		description = "Lek na wszystko.";
		instructions = "£¹czysz wszystko w iloœciach 1";
		composition.add("S 1");
		composition.add("S 2");
		composition.add("S 3");
		composition.add("S 4");
		Medicine m2 =new Medicine(2, "name2", "prod2", null);
		m2.name="Wszystkonix";
		m2.setDescription(description);
		m2.setInstructions(instructions);
		m2.setMedicineComposition(composition);
		m2.quantity = quantity;
		m2.setCanCreate(true);
		medicineList.add(m2);
		composition = new ArrayList<>();
		description = "Lek na nic.";
		instructions = "£¹czysz wszystko. Podgrzewasz. Dodajesz 1 czêœæ wody.";
		composition.add("Sk³adnik 1");
		composition.add("Sk³adnik 2");
		composition.add("Sk³adnik 3");
		composition.add("Sk³adnik 4");
		Medicine m3 = new Medicine(3, "name3", "prod3", null);
		m3.name="NicNieDzia³aMix";
		m3.setDescription(description);
		m3.setInstructions(instructions);
		m3.setMedicineComposition(composition);
		m3.quantity = quantity;
		m3.setCanCreate(true);
		medicineList.add(m3);
		Medicine lek1= new Medicine(4, "name4", "prod4", null);
		lek1.name="lek1";
		lek1.quantity=5;
		medicineList.add(lek1);
		Medicine lek2= new Medicine(5, "name5", "prod5", null);
		lek2.name="lek2";
		lek2.quantity=10;
		medicineList.add(lek2);
		Medicine lek3= new Medicine(5, "name6", "prod6", null);
		lek3.name="lek3";
		lek3.quantity=15;
		medicineList.add(lek3);
	}
}
