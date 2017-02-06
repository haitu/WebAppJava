package hai.payment.model;

import org.joda.time.DateTime;

public class CreditCardInfor {
	String cardNumber;
	DateTime expireDate;
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public DateTime getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(DateTime expireDate) {
		this.expireDate = expireDate;
	}
	
}
