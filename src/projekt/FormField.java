package projekt;

public class FormField {
	private String onWhenOrdered;
	private int orderedMedicineId;
	private int patientId;
	private int amount;
	private boolean isDelivered;
	private String whenOrderedDate;
	private String userWhoOrdered;
	public int FormFieldId;
	
//	public FormField(String userWhoOrdered, Date onWhenOrdered, int orderedMedicineId, int patientId, int amount,
//			boolean isDelivered, Date whenOrderedDate) {
//		super();
//		this.userWhoOrdered = userWhoOrdered;
//		this.onWhenOrdered = onWhenOrdered;
//		this.orderedMedicineId = orderedMedicineId;
//		this.patientId = patientId;
//		this.amount = amount;
//		this.isDelivered = isDelivered;
//		this.whenOrderedDate = whenOrderedDate;
//	}
	
	@Override
	public String toString() {
		return  "Kto zamówi³: " + userWhoOrdered+ ", iloœæ: " + amount +", na kiedy: " + onWhenOrdered + ", id leku: " + orderedMedicineId + ", id pacjenta: "
				+ patientId + ", kiedy zamówione: "
				+ whenOrderedDate;
	}
	
	public String getUserWhoOrdered() {
		return userWhoOrdered;
	}
	public void setUserWhoOrdered(String userWhoOrdered) {
		this.userWhoOrdered = userWhoOrdered;
	}
	public String getOnWhenOrdered() {
		return onWhenOrdered;
	}
	public void setOnWhenOrdered(String onWhenOrdered) {
		this.onWhenOrdered = onWhenOrdered;
	}
	public int getOrderedMedicineId() {
		return orderedMedicineId;
	}
	public void setOrderedMedicineId(int orderedMedicineId) {
		this.orderedMedicineId = orderedMedicineId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public boolean isDelivered() {
		return isDelivered;
	}
	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
	public String getWhenOrderedDate() {
		return whenOrderedDate;
	}
	public void setWhenOrderedDate(String whenOrderedDate) {
		this.whenOrderedDate = whenOrderedDate;
	}
}
