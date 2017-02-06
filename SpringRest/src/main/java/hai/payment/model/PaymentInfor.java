package hai.payment.model;

import org.joda.time.DateTime;

public class PaymentInfor {
	private float paidAmount;
	private int userId;
	private DateTime paidDatetime;
	String userName;
	String firstName;
	String lastName;
	public float getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public DateTime getPaidDatetime() {
		return paidDatetime;
	}
	public void setPaidDatetime(DateTime paidDatetime) {
		this.paidDatetime = paidDatetime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
