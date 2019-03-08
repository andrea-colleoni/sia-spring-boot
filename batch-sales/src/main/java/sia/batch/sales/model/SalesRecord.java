package sia.batch.sales.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SalesRecord {
	
	@Id
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderID;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Temporal(TemporalType.DATE)
	private Date shipDate;
	
	private Integer elapsedDays;
	
	private String region;
	private String country;
	private String itemType;
	private String salesChannel;
	private String orderPriority;

	private Integer unitsSold;
	
	private BigDecimal unitPrice;
	private BigDecimal unitCost;
	private BigDecimal totalRevenue;
	private BigDecimal totalCost;
	private BigDecimal totalProfit;
	
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getSalesChannel() {
		return salesChannel;
	}
	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}
	public String getOrderPriority() {
		return orderPriority;
	}
	public void setOrderPriority(String orderPriority) {
		this.orderPriority = orderPriority;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Long getOrderID() {
		return orderID;
	}
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public Integer getUnitsSold() {
		return unitsSold;
	}
	public void setUnitsSold(Integer unitsSold) {
		this.unitsSold = unitsSold;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}
	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public BigDecimal getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
	@Override
	public String toString() {
		return "SalesRecord [itemType=" + itemType + ", salesChannel=" + salesChannel + ", orderDate=" + orderDate
				+ ", orderID=" + orderID + ", shipDate=" + shipDate + ", unitsSold=" + unitsSold + ", totalRevenue="
				+ totalRevenue + ", totalCost=" + totalCost + ", totalProfit=" + totalProfit + "]";
	}
	public Integer getElapsedDays() {
		return elapsedDays;
	}
	public void setElapsedDays(Integer elapsedDays) {
		this.elapsedDays = elapsedDays;
	}
	
	

}
