package webApp.beans;

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

	public Lists(String listName, String listCode, String listType, String listGeo) {
		this.listName = listName;
		this.listCode = listCode;
		this.listType = listType;
		this.listGeo = listGeo;
	}

	public String getListName() {
		return listName;
	}

	public void set(String listName) {
		this.listName = listName;
	}

	public String getListCode() {
		return listCode;
	}

	public void setListCode(String listCode) {
		this.listCode = listCode;
	}

	public String getListType() {
		return listType;
	}

	// Set List's Type
	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getListGeo() {
		return listGeo;
	}

	public void setListGeo(String listGeo) {
		this.listGeo = listGeo;
	}
} // end of class List