package tf.entity;

import java.time.LocalDate;





public class Customer extends Object{
	
	public static final char MALE='M';
	public static final char FEMALE='F';
	
	
	private String id;//  ID, PKey, required
	private String password; // 必須為6~20個字元, required
	private String name;// required
	private char gender;// 'M':男, 'F':女, required
	private String email;// required, 用regular expression檢查(http://regexlib.com/), unique index
	private LocalDate birthday;// required
	private String phone = ""; // optional
	private String address = ""; // optional
	private String classname;

public Customer() {}

public Customer(String id, String password, String name) {
	
	this.setId(id);
	this.setPassword(password);
	this.setName(name);
	
}
	
	public Customer(String id, String password, String name, char gender) {
		
		this(id, password, name);
		this.setGender(gender);
	}
    
	public Customer(String id, String password, String name, String classname) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.classname = classname;
	}
	
	
	
	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getId() {
		return id;
	}
	
	
	
	public void setId(String idValue) {
		
		if(idValue!=null ) {
			id = idValue;
		} else {
			throw new DataInvalidException("帳號不能為空值");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password!=null && 
				password.length()>=6 && password.length()<=20) {
			this.password = password;
		}else {
		
			throw new DataInvalidException("密碼不正確，長度必須6~20個字元");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name!=null && (name=name.trim()).length()>0) {
			this.name = name;			
		}else {
			
			throw new DataInvalidException("必須輸入客戶姓名");
		}
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		if(gender == MALE || gender == FEMALE) {
			this.gender = gender;
		}else {
			
			throw new DataInvalidException("必須輸入正確客戶性別('M'為男，'F'為女)");
		}
	}
		
	public String getEmail() {
		return email;
	}
    public static final String EMAIL_REGEXP ="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	public void setEmail(String email) {
		if(email!=null && 
				(email.trim()).matches(EMAIL_REGEXP)) {
			this.email = email;
		}else {
			
			throw new DataInvalidException("必須輸入格式正確客戶Email");
		}
	}
	
	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		if(birthday!=null 
				&& LocalDate.now().isAfter(birthday)) {
			this.birthday = birthday;
		}else {
			
			throw new DataInvalidException("客戶生日必須小於今天");
		}
	}
	
	public void setBirthday(String date) {
		setBirthday(LocalDate.parse(date));
	}

	public void setBirthday(int year, int month, int day) {
		setBirthday(LocalDate.of(year, month, day));
	}

	public int getAge() {
		int age;

		if (getBirthday() != null) {
			int thisYear = LocalDate.now().getYear();
			int birthdYear = getBirthday().getYear();
			age = thisYear - birthdYear;
		} else {
			age = 0;
		
			throw new DataInvalidException("沒有客戶生日資料，無法計算年齡");
		}

		return age;
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