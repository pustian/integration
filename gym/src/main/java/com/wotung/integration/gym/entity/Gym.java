package com.wotung.integration.gym.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;




/**
 * <p>
 * 场地基本信息
 * </p>
 *
 * @author 郑义军
 * @since 2019-11-03
 */
@TableName("gym")
public class Gym extends Model<Gym> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 场馆名字
	 */
	private String name;
	/**
	 * 场馆地址
	 */
	private String address;
	/**
	 * 联系方式
	 */
	@TableField("contact_info")
	private String contactInfo;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
