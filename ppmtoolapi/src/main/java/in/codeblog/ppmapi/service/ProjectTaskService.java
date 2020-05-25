package in.codeblog.ppmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codeblog.ppmapi.domain.Backlog;
import in.codeblog.ppmapi.domain.ProjectTask;
import in.codeblog.ppmapi.repository.BacklogRepository;
import in.codeblog.ppmapi.repository.ProjectTaskRepository;


@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
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
			projectTask.setStatus("TO_DO");
		}
		return projectTaskRepository.save(projectTask);
	}

}
