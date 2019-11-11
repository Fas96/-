package model;

public class Student extends Person {

	private String Dept;
	private String status;

	

	public Student(int id, String firstName, String lastName, int age, String address, String dept, String status) {
		super(id, firstName, lastName, age, address);
		Dept = dept;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDept() {
		return Dept;
	}

	public void setDept(String dept) {
		Dept = dept;
	}

	@Override
	public String toString() {
		
		return super.toString()+String.format("Department: %s\nStatus: %s \n ",getDept(), getStatus());
				
	}
	

}
