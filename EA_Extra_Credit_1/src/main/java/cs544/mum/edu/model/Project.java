package cs544.mum.edu.model;

/**
 * Name: CHANDARA LEANG
 * ID: 108619
 * Task: Extra Credit 1
 * Class: Project
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Project {
	@Id
	@GeneratedValue
	private int id;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date expectedStartDate;

	@Temporal(TemporalType.DATE)
	private Date expectedEndDate;

	@OneToMany(mappedBy = "project")
	private List<Task> tasks = new ArrayList<Task>();

	@ManyToOne
	private User administrator;

	@Enumerated
	private Status status;

	private String location;

	public Project() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpectedStartDate() {
		return expectedStartDate;
	}

	public void setExpectedStartDate(Date expectedStartDate) {
		this.expectedStartDate = expectedStartDate;
	}

	public Date getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public List<Task> getTasks() {
		return Collections.unmodifiableList(tasks);
	}

	public void addTask(Task task) {
		tasks.add(task);
		task.setProject(this);
	}

	public void removeTask(Task task) {
		task.setProject(null);
		tasks.remove(task);
	}

	public User getAdministrator() {
		return administrator;
	}

	public void setAdministrator(User administrator) {
		this.administrator = administrator;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}