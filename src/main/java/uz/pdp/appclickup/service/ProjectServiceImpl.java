package uz.pdp.appclickup.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.appclickup.dto.ProjectDTO;
import uz.pdp.appclickup.dto.Response;
import uz.pdp.appclickup.entity.Project;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.repository.ProjectRepository;
import uz.pdp.appclickup.repository.SpaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    final ProjectRepository projectRepository;
    final SpaceRepository spaceRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              SpaceRepository spaceRepository) {
        this.projectRepository = projectRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public List<Project> findAllProject(Long spaceId, User user) {
        return projectRepository.findAllBySpaceIdAndCreatedBy(spaceId, user.getId());
    }

    @Override
    public Response addProject(ProjectDTO projectDTO) {

        Project project = new Project(
                projectDTO.getName(),
                spaceRepository.findById(projectDTO.getSpaceId()).orElseThrow(() -> new ResourceNotFoundException("space")),
                projectDTO.getAccessType(),
                false,
                projectDTO.getColor()
        );

        projectRepository.save(project);
        return new Response("Project created!", true);
    }

    @Override
    public Response editProject(Long id, ProjectDTO projectDTO, User user) {

        if (!projectRepository.existsByI(id))
            return new Response("Such project was not found!", false);

        Optional<Project> optionalProject = projectRepository.findById(id);
        optionalProject.get().setName(projectDTO.getName());
        optionalProject.get().setAccessType(projectDTO.getAccessType());
        optionalProject.get().setArchived(projectDTO.isArchived());
        optionalProject.get().setColor(projectDTO.getColor());
        projectRepository.save(optionalProject.get());

        return new Response("Project edited!", true);
    }

    @Override
    public Response deleteProject(Long id, Long spaceId, User user) {

        if (!projectRepository.existsByI(id))
            return new Response("Such project was not found!", false);

        projectRepository.deleteById(id);
        return new Response("Project deleted!", true);
    }
}
