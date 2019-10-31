package com.wotung.integration.member.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 人员基本信息
 * </p>
 *
 * @author 田圃森
 * @since 2019-10-31
 */
@TableName("member")
public class Member extends Model<Member> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 姓名
     */
	private String name;
    /**
     * 昵称
     */
	private String nickname;
    /**
     * 性别:0-男性，1-女性
     */
	private Integer sex;
    /**
     * 证件类别：00-身份证,01-护照
     */
	@TableField("id_type")
	private String idType;
    /**
     * 证件号码
     */
	@TableField("id_number")
	private String idNumber;
    /**
     * 手机号
     */
	private String phone;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 联系人姓名
     */
	@TableField("linkman_name")
	private String linkmanName;
    /**
     * 联系人手机号
     */
	@TableField("linkman_phone")
	private String linkmanPhone;
    /**
     * 状态：0-未激活，1-激活，2-锁定，3-冻结
     */
	private Integer status;
    /**
     * 状态：1-删除，0-未删除
     */
	@TableField("is_deleted")
	private Integer isDeleted;
	@TableField("gmt_create")
	private Date gmtCreate;
	@TableField("gmt_modified")
	private Date gmtModified;


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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
