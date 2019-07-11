package com.cafe24.shoppingmall.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	
	private Long no;
	
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String password;
	
	private String phone;
	
	@NotEmpty
	private String telephone;
	
	@NotEmpty
	private String email;
	
	private String gender;
	
	private String birthday;
	
	private String join_date;
	
	
	public UserVo() {}
	public UserVo(String id, String name, String password, String phone, String telephone,
			String email, String gender, String birthday) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.telephone = telephone;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		
	}
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone
				+ ", telephone=" + telephone + ", email=" + email + ", gender=" + gender + ", birthday=" + birthday
				+ ", join_date=" + join_date + "]";
	}
	
	


	
}


