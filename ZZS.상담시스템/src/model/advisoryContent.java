package model;

import java.util.Calendar;
import java.util.Date;

public class advisoryContent {

	private String studentName;
	private String profName;
	private String advContent;
	private Date dat;
	public advisoryContent(String studentName, String profName, String advContent, Date dat) {
		
		this.studentName = studentName;
		this.profName = profName;
		this.advContent = advContent;
		this.dat = dat;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	public String getAdvContent() {
		return advContent;
	}
	public void setAdvContent(String advContent) {
		this.advContent = advContent;
	}
	public Date getDat() {
		return dat;
	}
	public void setDat(Date dat) {
		
		this.dat = new Date();
	}
	
	
	
	
}
