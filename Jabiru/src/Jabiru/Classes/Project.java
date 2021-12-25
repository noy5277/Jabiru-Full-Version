package Jabiru.Classes;

import java.io.Serializable;

public class Project  {
	
	private String mName;
	private String mContact;
	private String mPhone;
	private Boolean mEnable;
	private String mEmail;
	private String mSLA;


	
	public Project(String mName,String mContact,String mPhone ,String mEmail , String mSLA )
	{
		this.mEnable=true;
		this.mName=mName;
		this.mContact=mContact;
		this.mPhone=mPhone;
		this.mEmail=mEmail;
		this.mSLA=mSLA;
		
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmContact() {
		return mContact;
	}

	public void setmContact(String mContact) {
		this.mContact = mContact;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public Boolean getmEnable() {
		return mEnable;
	}

	public void setmEnable(Boolean mEnable) {
		this.mEnable = mEnable;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmSLA() {
		return mSLA;
	}

	public void setmSLA(String mSLA) {
		this.mSLA = mSLA;
	}
	
	

}
