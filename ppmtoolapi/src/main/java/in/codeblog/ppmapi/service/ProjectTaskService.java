package in.codeblog.ppmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codeblog.ppmapi.domain.Backlog;
import in.codeblog.ppmapi.domain.Project;
import in.codeblog.ppmapi.domain.ProjectTask;
import in.codeblog.ppmapi.exception.ProjectNotFoundException;
import in.codeblog.ppmapi.repository.BacklogRepository;
import in.codeblog.ppmapi.repository.ProjectRepository;
import in.codeblog.ppmapi.repository.ProjectTaskRepository;


@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	@Autowired
	private ProjectRepository projectRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		try 
		{
		//Exception Handling :In case project is not available
	
		//ProjectTask should be added to specific Project,project!=null,backlog exist

		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		
		//set the backlog to project task
		projectTask.setBacklog(backlog);
		
		//Project request should be like :projId-1
		Integer backLogSequence = backlog.getPTSequence();
		
		//update projecttask sequence no
		backLogSequence++;
		
		backlog.setPTSequence(backLogSequence);

		projectTask.setProjectSequence(projectIdentifier + "-" + backLogSequence);
		projectTask.setProjectIdentifer(projectIdentifier);

		if (projectTask.getPriority() == null) {
			projectTask.setPriority(3);
		}
		if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
			projectTask.setStatus("TO-DO");
		}
		
		return projectTaskRepository.save(projectTask);
	}
	catch(Exception ex) {
		throw new ProjectNotFoundException("Project not Found");
	}

	}
	public Iterable<ProjectTask> findBacklogById(String id){//if theprojectIdentifier is not available
		Project project = projectRepository.findByProjectIdentifier(id);
		if(project==null) {
			throw new ProjectNotFoundException("Project Not Found");
		}
		return projectTaskRepository.findByProjectIdentiferOrderByPriority(id);
	}
	public ProjectTask findByProjectSequence(String backlog_id,String pt_id) {
		return projectTaskRepository.findByProjectSequence(pt_id);
		
	}
}
	
