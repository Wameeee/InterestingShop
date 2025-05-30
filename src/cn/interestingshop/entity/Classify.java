package cn.interestingshop.entity;
import java.io.Serializable;
public class Classify implements Serializable{

	private Integer id;//ID
	private String classifyName;//名称
	private Integer parentId;//父级ID
	private Integer type;//分类级别
	private String icon;
	private String parentName;
	private String iconClass; // 图标类名

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	// 添加setName方法，与ClassifyDaoImpl中使用的方法名匹配
	public void setName(String name) {
		this.classifyName = name;
	}
	
	// 添加getName方法，与setName对应
	public String getName() {
		return this.classifyName;
	}
	
	// 添加getIconClass方法
	public String getIconClass() {
		return iconClass;
	}
	
	// 添加setIconClass方法
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
}
