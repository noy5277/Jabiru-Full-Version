package Jabiru.Classes;


import java.io.File;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

import Jabiro.Service.JabiroService;
import Jabiro.StringMaching.IAlgoStringMatching;


public class Issue  {
	
	enum Priority{
		CRITICAL,
		WARNING
	}
	
	enum Category{
		MONITOR,
		SUPPORT,
		PROACTIVE,
	}
	
	enum Status {
		  RESOLVED,
		  FEEDBACK,
		  ACKNOLEDGE,
		  ASSIGNED,
		  UNASSIGNED
		}

	private Status mStatus;
	private HashSet <Comment> mFlow;
	private String mId;
	private Project mProject;
	private User mReporter;
	private Priority mPriority;
	private Category mCategoty;
	private Date mDateSubmitted;
	private User mAssignedTo;
	private Date mUpdated;
	private String mSubject;
	private String mSummarry;
	private Date mDueDate;
	private Boolean mAssigned;
	private File mAttachment;
	
	 
    public Issue()
    {
    	
    }
    
	public Issue(Project mProject,User mReporter,String mSubject,String mSummarry) throws ClassNotFoundException, SQLException { 
		this.mProject=mProject;
		this.mReporter=mReporter;
		this.mPriority=Priority.WARNING;
		this.mCategoty=Category.SUPPORT;
		this.mDueDate=new Date();
		this.mAssigned=false;
		this.mDateSubmitted=new Date();
		this.mAttachment=new File("");
		this.mSubject=mSubject;
		this.mSummarry=mSummarry;
		this.mStatus=Status.UNASSIGNED;
		this.mAssignedTo=JabiroService.getmInstance().getAdmin();
		this.mUpdated=new Date();
		
		StringBuilder random;
		random=new StringBuilder();
		Random rand=new Random();
		
		int ch=0;
		for(int i=0;i<6;i++)
		{
			ch=48+rand.nextInt(9);
			 random.insert(i,(char) ch);
		}
		this.mId=random.toString();
		this.mFlow=new HashSet <Comment>();
	}


	public void setmId(String mId) {
		this.mId = mId;
	}


	public Status getmStatus() {
		return mStatus;
	}
	
	public String getmStringStatus()
	{
		return(this.mStatus.toString());
	}
	
	public void setmStringStatus(String status)
	{
		switch(status)
		{
		case "RESOLVED":
			this.mStatus=Status.RESOLVED;
			break;
		case "FEEDBACK":
			this.mStatus=Status.FEEDBACK;
			break;
		case "ACKNOLEDGE":
			this.mStatus=Status.ACKNOLEDGE;
			break;
		case "ASSIGNED":
			this.mStatus=Status.ASSIGNED;
			break;
		case "UNASSIGNED":
			this.mStatus=Status.UNASSIGNED;
			break;
		default:
            System.out.println("no match");
		}
	}


	public void setmStatus(Status mStatus) {
		this.mStatus = mStatus;
	}


	public HashSet<Comment> getmFlow() {
		return mFlow;
	}


	public String getmId(){
		return mId.toString();
	}
	

	public Project getmProject() {
		return mProject;
	}


	public void setmProject(Project mProject) {
		this.mProject = mProject;
	}


	public User getmReporter() {
		return mReporter;
	}


	public void setmReporter(User mReporter) {
		this.mReporter = mReporter;
	}

    
	public void setmFlow(HashSet<Comment> mFlow) {
		this.mFlow = mFlow;
	}

	public Priority getmPriority() {
		return mPriority;
	}
	
	public String getmStringPriority()
	{
		return(mPriority.toString());
	}
	
	public void setmStringPriority(String p)
	{
		switch(p)
		{
		case "CRITICAL":
			this.mPriority=Priority.CRITICAL;
			break;
		case "WARNING":
			this.mPriority=Priority.WARNING;
			break;
		default:
            System.out.println("no match");
		
		}
	}


	public void setmPriority(Priority mPriority) {
		this.mPriority = mPriority;
	}
	
	public void setmStringCategory(String c)
	{
		switch(c)
		{
		case "MONITOR":
			this.mCategoty=Category.MONITOR;
			break;
		case "SUPPORT":
			this.mCategoty=Category.SUPPORT;
			break;
		case "PROACTIVE":
			this.mCategoty=Category.PROACTIVE;
			break;
		default:
            System.out.println("no match");
		}
			
		
	}

	public String getmStringCategoty()
	{
		return this.mCategoty.toString();
	}
	
	public Category getmCategoty() {
		return mCategoty;
	}


	public void setmCategoty(Category mCategoty) {
		this.mCategoty = mCategoty;
	}


	public Date getmDateSubmitted() {
		return mDateSubmitted;
	}


	public void setmDateSubmitted(Date mDateSubmitted) {
		this.mDateSubmitted = mDateSubmitted;
	}


	public User getmAssignedTo() {
		return mAssignedTo;
	}


	public void setmAssignedTo(User mAssignedTo) {
		this.mAssignedTo = mAssignedTo;
	}


	public Date getmUpdated() {
		return mUpdated;
	}


	public void setmUpdated(Date mUpdated) {
		this.mUpdated = mUpdated;
	}

	
	public String getmSubject() {
		return mSubject;
	}


	public void setmSubject(String mSubject) {
		this.mSubject = mSubject;
	}

	public String getmSummarry() {
		return mSummarry;
	}


	public void setmSummarry(String mSummarry) {
		this.mSummarry = mSummarry;
	}


	public Date getmDueDate() {
		return mDueDate;
	}


	public void setmDueDate(Date mDueDate) {
		this.mDueDate = mDueDate;
	}


	public Boolean getmAssigned() {
		return mAssigned;
	}


	public void setmAssigned(Boolean mAssigned) {
		this.mAssigned = mAssigned;
	}


	public File getmAttachment() {
		return mAttachment;
	}


	public void setmAttachment(File mAttachment) {
		this.mAttachment = mAttachment;
	}
	
	
	public void addcomment(Comment c, Status s)
	{
		this.mStatus=s;
		this.mFlow.add(c);
	}

	public String find(String pat) throws ClassNotFoundException, SQLException
	{
		for(Comment c: this.mFlow)
		{
			if(JabiroService.getmInstance().search(pat, c.getComment()))
			{
				return this.mId.toString();
			}
		}
		if(JabiroService.getmInstance().search(pat, this.mSubject))
		{
			return this.mId.toString();
		}
		if(JabiroService.getmInstance().search(pat, this.mSummarry))
		{
			return this.mId.toString();
		}
		return "not found";
	}			
}
