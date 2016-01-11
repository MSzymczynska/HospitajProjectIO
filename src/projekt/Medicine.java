package projekt;

import java.util.ArrayList;
import java.util.Date;

public class Medicine extends Product {

	public Medicine(Integer id, String name, String producer, Date expDate, String name2,
			ArrayList<String> medicineComposition, String medicineDescription, String instructionsToPrepareMedicine,
			int quantity, boolean canCreate) {
		super(id, name, producer, expDate);
		name = name2;
		this.medicineComposition = medicineComposition;
		this.medicineDescription = medicineDescription;
		this.instructionsToPrepareMedicine = instructionsToPrepareMedicine;
		this.quantity = quantity;
		this.canCreate = canCreate;
	}
	public Medicine(Integer id, String name, String producer, Date expDate) {
		super(id, name, producer, expDate);
		this.name=name;
	}
	
	public String name;
	private ArrayList<String> medicineComposition;
	private String medicineDescription;
	private String instructionsToPrepareMedicine;
	public int quantity;
	public boolean canCreate;
	

	//	public Medicine(String name, String medicineDescription, String instructionsToPrepareMedicine, ArrayList<String> medicineComposition) {
//		this.name = name;
//		this.medicineDescription = medicineDescription;
//		this.instructionsToPrepareMedicine = instructionsToPrepareMedicine;
//		this.medicineComposition = medicineComposition;
//	}
	@Override
	public String toString() {
		return "Medicine [name=" + name + ", medicineComposition=" + medicineComposition + ", medicineDescription="
				+ medicineDescription + ", instructionsToPrepareMedicine=" + instructionsToPrepareMedicine
				+ ", quantity=" + quantity + "]";
	}
	public String getInstructions() {
		return instructionsToPrepareMedicine;
	}
	public void setInstructions(String instructions){
		this.instructionsToPrepareMedicine = instructions;
	}
	public String getDescription(){
		return medicineDescription;
	}
	public void setDescription(String description){
		this.medicineDescription = description;
	}
	public String getComposition(){
		String x= new String();
		for (int i=0;i<this.medicineComposition.size(); i++)
		{
			if(i==0)
			x= this.medicineComposition.get(i);
			else
			x= x +", "+this.medicineComposition.get(i);
		}
		return x;
	}
	public void setMedicineComposition(ArrayList<String> medicineComposition) {
		this.medicineComposition = medicineComposition;
	}
	public boolean isCanCreate() {
		return canCreate;
	}
	public void setCanCreate(boolean canCreate) {
		this.canCreate = canCreate;
	}
}
