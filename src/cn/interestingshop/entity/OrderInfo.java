package cn.interestingshop.entity;

import java.io.Serializable;

public class OrderInfo implements Serializable{
	private Integer id;		//ID
	private Integer baseOrderId;//订单ID
	private Integer buyNum;	//数量
	private Float amount;		//单价
	private Integer goodsId;	//商品id
	private Integer orderId;    //用于DAO层的订单ID
	private Integer quantity;   //用于DAO层的数量
	private Float cost;         //用于DAO层的成本

	private Goods goods;//商品

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Integer baseOrderId) {
		this.baseOrderId = baseOrderId;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}


	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	// 添加缺失的方法，用于DAO层
	public Integer getOrderId() {
		return orderId != null ? orderId : baseOrderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
		this.baseOrderId = orderId;
	}
	
	public Integer getQuantity() {
		return quantity != null ? quantity : buyNum;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		this.buyNum = quantity;
	}
	
	public Float getCost() {
		return cost != null ? cost : amount;
	}
	
	public void setCost(Float cost) {
		this.cost = cost;
		this.amount = cost;
	}
}
