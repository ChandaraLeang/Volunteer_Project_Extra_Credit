package cs544.mum.edu.model;

/**
 * Name: CHANDARA LEANG
 * ID: 108619
 * Task: Extra Credit 1
 * Class: Resource
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Resource {
	@Id
	@GeneratedValue
	private Integer id;

	private String description;

	public Resource() {
	}

	public Resource(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}