package Jabiru.Classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User {
	
	private String mUserName;
	private String mRealName;
	private String mPassword;
	private String mEmail;
	private Set<Issue> assigned;
	private Boolean mEnabled;
	
	public User(String mUserName, String mPassword)
	{
		this.mUserName=mUserName;
		this.mPassword=mPassword;
	}
	public User(String mUserName,String mRealName, String mEmail , String mPassword)
	{
		this.mUserName=mUserName;
		this.mPassword=mPassword;
		this.mRealName=mRealName;
		this.mEmail=mEmail;
		this.mEnabled=true;
		this.assigned=new HashSet<>();
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public String getmUserName() {
		return mUserName;
	}

	public void setmUserName(String mUserName) {
		this.mUserName = mUserName;
	}

	public String getmRealName() {
		return mRealName;
	}

	public void setmRealName(String mRealName) {
		this.mRealName = mRealName;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public Boolean getmEnabled() {
		return mEnabled;
	}

	public void setmEnabled(Boolean mEnabled) {
		this.mEnabled = mEnabled;
	}
	
	public void assign(Issue i)
	{
		this.assigned.add(i);
	}

}
