package data;

public class MedicineRequirement {

	private Medicine medicine;
	private String amount;
	private String application;

	public MedicineRequirement(Medicine medicine, String amount, String application) {
		this.medicine = medicine;
		this.amount = amount;
		this.application = application;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

}
