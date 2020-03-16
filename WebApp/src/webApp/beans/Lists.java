package webApp.beans;

// class List..
public class Lists {

	// Name of List (Microsoft, Lenovo, Unisys...)
	private String listName;

	// Code of List (DC11101,11102,11103...)
	private String listCode;

	private String listType;

	// GEO of List (USA,GDPR...)
	private String listGeo;

	// Default Constructor
	public Lists() {

	}

	// Constructor with Parameters
	public Lists(String listName, String listCode, String listType, String listGeo) {
		this.listName = listName;
		this.listCode = listCode;
		this.listType = listType;
		this.listGeo = listGeo;
	}

	// Return List's Name
	public String getListName() {
		return listName;
	}

	// Set List's Name
	public void set(String listName) {
		this.listName = listName;
	}

	// Return List's code
	public String getListCode() {
		return listCode;
	}

	// Set List's code
	public void setListCode(String listCode) {
		this.listCode = listCode;
	}

	// Return List's Type
	public String getListType() {
		return listType;
	}

	// Set List's Type
	public void setListType(String listType) {
		this.listType = listType;
	}

	// Return List's GEO
	public String getListGeo() {
		return listGeo;
	}

	// Set List's Geo
	public void setListGeo(String listGeo) {
		this.listGeo = listGeo;
	}
} // end of class List