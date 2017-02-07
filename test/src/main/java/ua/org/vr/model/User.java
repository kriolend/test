package ua.org.vr.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name="USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min=3, max=50)
	@Column(name = "NAME", nullable = false)
	private String name;

	@NotNull
	@Column(name = "AGE")
	private Integer age;


	@Column(name = "CREATED_DATE", insertable=false, updatable=false)
	private Date createdDate;
	
	@NotEmpty
	@Column(name = "SSN", unique=true, nullable = false)
	private String ssn;

	@Column(name = "IS_ADMIN", columnDefinition="BIT")
	private Boolean isAdmin;

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (getId() != user.getId()) return false;
		if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
		if (getAge() != null ? !getAge().equals(user.getAge()) : user.getAge() != null) return false;
		return !(getSsn() != null ? !getSsn().equals(user.getSsn()) : user.getSsn() != null);

	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
		result = 31 * result + (getSsn() != null ? getSsn().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", ssn='" + ssn + '\'' +
				'}';
	}


}
