package webApp.beans;

// Class UserData ...
public class UserData {

	public static final String SEX_MALE = "M";
	public static final String SEX_FEMALE = "F";

	private String userName;
	private String sex;
	private String password;

	public UserData() {

	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

} // end of class UserData
