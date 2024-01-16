package ua.pohribnyi.springsecurity.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 */
@Entity
@Table(name = "Person")
public class Person {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_name")
	@NotNull(message = "User name shouldn`t be empty")
	@Size(min = 2, max = 30, message = "User name should be between 2 and 30 characters")
	private String userName;

	@Column(name = "age_of_birth")
	@Min(value = 1900, message = "Age of birth should be greater then 1900")
	private int ageOfBirth;

	@Column(name = "password")
	@NotNull(message = "User password shouldn`t be empty")
	private String password;

	@Column(name = "role")
	private String role;

	public Person() {
	}

	public Person(int ageOfBirth, String username) {
		this.ageOfBirth = ageOfBirth;
		this.userName = username;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAgeOfBirth() {
		return ageOfBirth;
	}

	public void setAgeOfBirth(int ageOfBirth) {
		this.ageOfBirth = ageOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", userName=" + userName + ", ageOfBirth=" + ageOfBirth + ", password=" + password
				+ "]";
	}

}
