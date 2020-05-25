package in.codeblog.ppmapi.repository;

import org.springframework.data.repository.CrudRepository;

import in.codeblog.ppmapi.domain.ProjectTask;



public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

}
