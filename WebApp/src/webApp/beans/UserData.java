package webApp.beans;

// Class UserData ...
public class UserData {

	// Create constants for user authorization
	public static final String SEX_MALE = "M";
	public static final String SEX_FEMALE = "F";

	// Class members
	private String userName;
	private String sex;
	private String password;

	// Default constructor
	public UserData() {

	}

	// Set the Name of user
	public void setUserName(String userName) {
		this.userName = userName;
	}

	// Return the Name of user
	public String getUserName() {
		return userName;
	}

	// Set Sex of user
	public void setSex(String sex) {
		this.sex = sex;
	}

	// Return Sex of user
	public String getSex() {
		return sex;
	}

	// set Password
	public void setPassword(String password) {
		this.password = password;
	}

	// Return Password
	public String getPassword() {
		return password;
	}

} // end of class UserData
