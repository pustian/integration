package com.wotung.integration.member.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 会员密码
 * </p>
 *
 * @author 田圃森
 * @since 2019-10-31
 */
@TableName("member_passwd")
public class MemberPasswd extends Model<MemberPasswd> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 会员id，ref member：id
     */
	@TableField("member_id")
	private Integer memberId;
    /**
     * md5 密码
     */
	private String passwd;
    /**
     * 状态：0-使用中，1-锁定
     */
	private Integer status;
    /**
     * 出错次数
     */
	private Integer times;
    /**
     * 最后一次锁定时间
     */
	@TableField("lock_time")
	private Date lockTime;
    /**
     * md5用盐
     */
	private String salt;
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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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
