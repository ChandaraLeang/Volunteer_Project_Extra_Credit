package cs544.mum.edu.model;

/**
 * Name: CHANDARA LEANG
 * ID: 108619
 * Task: Extra Credit 1
 * Class: Task
 */

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Task {
	@Id
	@GeneratedValue
	private int id;

	private String description;

	@OneToMany
	@JoinColumn(name = "resource_Id")
	private List<Resource> resources = new ArrayList<Resource>();

	@Enumerated(EnumType.STRING)
	private Status status;

	private int timeFrame;

	@ManyToOne
	private User volunteer;

	@ManyToOne
	private Project project;

	public Task() {
	}

	public int getId() {
		return id;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public Status getStatus() {
		return status;
	}

	public int getTimeFrame() {
		return timeFrame;
	}

	public User getVolunteer() {
		return volunteer;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setTimeFrame(int timeFrame) {
		this.timeFrame = timeFrame;
	}

	public void setVolunteer(User volunteer) {
		this.volunteer = volunteer;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}