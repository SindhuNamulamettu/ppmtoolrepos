package in.codeblog.ppmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codeblog.ppmapi.domain.Backlog;
import in.codeblog.ppmapi.domain.Project;
import in.codeblog.ppmapi.exception.ProjectIdException;
import in.codeblog.ppmapi.repository.BacklogRepository;
import in.codeblog.ppmapi.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BacklogRepository backlogRepository;

	public Project saveOrUpdate(Project project)
	{
		try {
			//While saving the project,backlog should be saved
			//when project is not having id,it is anew project ,you need to create a backlog
			if(project.getId()==null) {
				Backlog backlog=new Backlog();
				project.setBacklog(backlog);//one to one relationship
				backlog.setProject(project);//one to one relationship
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			//When updating the project,backlog should be set as it is,it should not be null
			if(project.getId()!=null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			
			return projectRepository.save(project);
		}
		catch(Exception e)
		{
			throw new ProjectIdException("Project Id '"+project.getProjectIdentifier().toUpperCase()+"' already exists !");
		}
		
	}
	public Project findByProjectIdentifier(String projectId)
	{
		Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null)
		{
			throw new ProjectIdException("Project Id '"+projectId.toUpperCase()+"' does not exists !");
		}
		return project;
		
	}
	public Iterable<Project> findAllProjects()
	{
		return projectRepository.findAll();
	}
	public Project deleteProjectByIdentifier(String projectIdentifier)
	{
		Project project=projectRepository.findByProjectIdentifier(projectIdentifier);
		if(project==null)
		{
			throw new ProjectIdException("Project with Id '"+projectIdentifier+"' does not exists !");
		}
		projectRepository.delete(project);
		return project;
	}
	
}
