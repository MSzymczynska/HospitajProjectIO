package projekt;
import java.util.Date;

public class Condition {

	private Date date;
	private String condition;
	
	public Condition(Date date, String condition) {
		this.date = date;
		this.condition = condition;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}
