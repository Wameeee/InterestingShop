package cn.interestingshop.entity;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by bdqn on 2016/5/12.
 */
public class UserAddress implements Serializable {

    private Integer id;

    private String address;

    private Integer userId;

    private Date createTime;//创建时间

    private String remark;

    private String name;  // 收件人姓名
    
    private String phone; // 收件人电话
    
    private Integer isDefault; // 是否默认地址 1-默认 0-非默认

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Integer getIsDefault() {
        return isDefault;
    }
    
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
