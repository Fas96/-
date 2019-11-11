package model;

public class Person {

	private int id;
	private String firstName;
	private String lastName;
	private int DOB;
	private String address;

	public Person(int id, String firstName, String lastName, int age, String address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.DOB = age;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return DOB;
	}

	public void setAge(int age) {
		this.DOB = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("\nssn:\t %s\nFull name: %s %s\nAge: %d\nAddress: %s\n", getId(),getFirstName(), getLastName(), getAge(), getAddress());
				
	}
	
	

}
