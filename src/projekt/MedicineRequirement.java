package projekt;

public class MedicineRequirement {

	private String medicine;
	private String amount;
	private String application;

	public MedicineRequirement(String medicine, String amount, String application) {
		this.medicine = medicine;
		this.amount = amount;
		this.application = application;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
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
