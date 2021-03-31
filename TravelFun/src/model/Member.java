package model;

public class Member {
private String id,name,password,gender,email,birthday,phone,address;

public Member() {
}

public Member(String id, String name, String password, String gender, String email, String birthday, String phone,
		String address) {
	super();
	this.id = id;
	this.name = name;
	this.password = password;
	this.gender = gender;
	this.email = email;
	this.birthday = birthday;
	this.phone = phone;
	this.address = address;
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

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getBirthday() {
	return birthday;
}

public void setBirthday(String birthday) {
	this.birthday = birthday;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}


}
