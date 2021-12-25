
package Jabiru.Classes;

import java.io.Serializable;
import java.util.Date;

public class Comment  {

	
	private Date mDate;
	private User mUser;
	private String comment;

	
	public Comment(User mUser, String c) {
		
		this.mDate = new Date();
		this.mUser = mUser;
		this.comment = c;
	}

	
	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	public User getmUser() {
		return mUser;
	}

	public void setmUser(User mUser) {
		this.mUser = mUser;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
