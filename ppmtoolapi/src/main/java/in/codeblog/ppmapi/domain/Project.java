package in.codeblog.ppmapi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Project Name should not be blank")
	private String projectName;
	@NotBlank(message = "Project Identifier should not be blank")
	@Size(min=4,max=5,message = "Project Identifier size should be in between 4 and 5")
	@Column(updatable = false,unique = true)
	private String projectIdentifier;
	@NotBlank(message = "Project Decription should not be blank")
	String description;
	@JsonFormat(pattern = "dd-MM-yyyy")
	Date start_date;
	@JsonFormat(pattern = "dd-MM-yyyy")
	Date end_date;
	@JsonFormat(pattern = "dd-MM-yyyy")
	Date created_At;
	@JsonFormat(pattern = "dd-MM-yyyy")
	Date updated_At;
	
	
	
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	@PrePersist
	public void onCreate() {
		this.created_At=new Date();
		
		
	}
	@PreUpdate
	public void onUpdate() {
		this.updated_At=new Date();
		
		
	}

}
