package cn.interestingshop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BaseOrder implements Serializable {

	private Integer id;			//ID
	private String orderNo;		//订单号
	private Integer userId;		//用户id
	private String userAddress;	//收货地址
	private Date createTime;	//创建时间
	private Float amount;			//订单总计价格
	private Integer addressId;    //地址ID
	private Float cost;           //订单成本
	private String serialNumber;  //序列号
	private Integer payType;      //支付类型
	private Integer status;       //订单状态
	
	private String account;		//登录名称
	private List<OrderInfo> orderInfoList;//订单明细列表

	public List<OrderInfo> getOrderInfoList() {
		return orderInfoList;
	}

	public void setOrderInfoList(List<OrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public int hashCode() {		
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BaseOrder){
			if(((BaseOrder)obj).id==id){
				return true;
			}			
		}	
		return false;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	// 添加缺失的getter和setter方法
	public Integer getAddressId() {
		return addressId;
	}
	
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
	public Float getCost() {
		return cost != null ? cost : amount;
	}
	
	public void setCost(Float cost) {
		this.cost = cost;
	}
	
	public String getSerialNumber() {
		return serialNumber != null ? serialNumber : orderNo;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public Integer getPayType() {
		return payType;
	}
	
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
}
