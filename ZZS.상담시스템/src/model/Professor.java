package model;

public class Professor extends Person {

	private String deptname;
	private String resCourses;
	
	public Professor(int id, String firstName, String lastName, int age, String address, String deptname,
			String resCourses) {
		super(id, firstName, lastName, age, address);
		this.deptname = deptname;
		this.resCourses = resCourses;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getResCourses() {
		return resCourses;
	}
	public void setResCourses(String resCourses) {
		this.resCourses = resCourses;
	}
	 
	
	
	@Override
	public String toString() {
		super.toString();
		return String.format("\nDepartment: %s\nStatus: %s \n ",getDeptname(), getResCourses());
				
	}
	
	
	
}
