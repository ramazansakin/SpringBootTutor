package com.sakinramazan.springboot.model;

public class User {

	private int id;
	private String name;
	private int age;
	private double salary;

	public User() {
	}

	public User(int id, String name, int age, double salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	// equals ı override ettiğimizde hashCode uda unutmayalım.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	// Sadece referans kontrolü değil(==) objelerin gerçekten içeriklerinin aynı
	// olup olmadığı kontol
	// etmek için kullanırız.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kullanıcı >> id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary;
	}

}
