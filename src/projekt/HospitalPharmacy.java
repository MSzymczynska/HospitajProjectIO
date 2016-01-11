package projekt;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class HospitalPharmacy{
	
	public HospitalPharmacy(Connection con) {
		orderedMedicine = new ArrayList<>();
		medicineList = new ArrayList<>();
		pharmacyArchieve = new ArrayList<>();
		madeMedicines = new ArrayList<>();
		deliveredMedicines=new ArrayList<>();
		this.con=con;
		
	}
	
	public ArrayList<Medicine> medicineList;
	public ArrayList<FormField> orderedMedicine;
	private ArrayList<FormField> pharmacyArchieve;
	private ArrayList<Medicine> madeMedicines;
	public ArrayList<FormField> deliveredMedicines;
	public Connection con;
	
	
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
	
	public void addMedicineToPharmecyList(Medicine m) {
		System.out.println(m.name);
		boolean x=false;
		for(int i=0; i<this.medicineList.size(); i++){
			if(this.medicineList.get(i).name==m.name)
			{
				this.medicineList.get(i).quantity+=m.quantity;
				x=true;
				break;
			}				
		}
		if(x==false)
		medicineList.add(m);
			
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
	
	public void przykladoweDane(){		

		try {
			PreparedStatement query1=(PreparedStatement) con.prepareStatement("select m.medicine_id, p.title, p.producent, m.quantity_in_pharmacy, m.isMadeMedicine from pharmacy_medicines m, products p where p.product_id=m.product_id");
			ResultSet result=(ResultSet) query1.executeQuery();
			while(result.next())
			{
				Medicine m = new Medicine(Integer.parseInt(result.getString(1)), result.getString(2), result.getString(3), null);				
				m.quantity=Integer.parseInt(result.getString(4));
				int x =Integer.parseInt(result.getString(5));
				if(x==1)
					m.canCreate=true;
				if(x==0)
					m.canCreate=false;

				if(m.canCreate)
				{
					PreparedStatement query2=(PreparedStatement) con.prepareStatement("select p.title from products p, composition_list c where c.product_id=p.product_id and c.medicine_id="+m.id);
					ResultSet result2=(ResultSet) query2.executeQuery();
					
					ArrayList<String> composition = new ArrayList();
					while(result2.next())
					{
						composition.add(result2.getString(1));
						System.out.println(result2.getString(1));
					}
					m.setMedicineComposition(composition);			
				}
				
				PreparedStatement query3=(PreparedStatement) con.prepareStatement("select description, instructions from pharmacy_medicines where medicine_id="+m.id);
				ResultSet result3=(ResultSet) query3.executeQuery();
				while(result3.next())
				{
				m.setDescription(result3.getString(1));
				m.setInstructions(result3.getString(2));
				}
				medicineList.add(m);
				
			}			
		} catch (SQLException e) {
			System.out.println( e.getMessage() );
		}
		
				
	}
}
