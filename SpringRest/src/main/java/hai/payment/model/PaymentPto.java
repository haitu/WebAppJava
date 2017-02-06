package hai.payment.model;

import org.joda.time.DateTime;

public class PaymentPto {
	private float paidAmount;
	private int userId;
	private DateTime paidDatetime;
	
	public PaymentPto()
	{
		
	}
	public PaymentPto(int userId,float paidAmount,DateTime paidDatetime)
	{
		this.userId = userId;
		this.paidAmount = paidAmount;
		this.paidDatetime = paidDatetime;
	}
	public PaymentPto(int userId,float paidAmount)
	{
		this.userId = userId;
		this.paidAmount = paidAmount;
	}
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
}
