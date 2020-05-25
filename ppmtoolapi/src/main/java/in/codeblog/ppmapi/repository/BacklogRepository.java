package in.codeblog.ppmapi.repository;

import org.springframework.data.repository.CrudRepository;

import in.codeblog.ppmapi.domain.Backlog;


public interface BacklogRepository extends CrudRepository<Backlog, Long> {
	Backlog findByProjectIdentifier(String projectIdentifier);
	
}
