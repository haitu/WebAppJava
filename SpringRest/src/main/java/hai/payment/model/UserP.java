package hai.payment.model;

import java.util.List;

public class UserP {
	int userId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public List<String> getListOfCreditCard() {
		return listOfCreditCard;
	}
	public void setListOfCreditCard(List<String> listOfCreditCard) {
		this.listOfCreditCard = listOfCreditCard;
	}
	String userName;
	String firstName;
	String lastName;
	List<String> listOfCreditCard;
}
