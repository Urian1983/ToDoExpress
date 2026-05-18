package urian1983.todoexpress.service;


import org.springframework.stereotype.Service;
import urian1983.todoexpress.dto.TaskRequest;
import urian1983.todoexpress.dto.TaskResponse;
import urian1983.todoexpress.exceptions.NotFoundException;
import urian1983.todoexpress.mapper.TaskMapper;
import urian1983.todoexpress.model.Audit;
import urian1983.todoexpress.model.LogLevel;
import urian1983.todoexpress.model.Task;
import urian1983.todoexpress.repository.AuditRepository;
import urian1983.todoexpress.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;
    private final AuditRepository auditRepository;

    public TaskServiceImpl(TaskRepository repository, TaskMapper mapper, AuditRepository auditRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.auditRepository = auditRepository;
    }

    @Override
    public TaskResponse createTask(TaskRequest newTask) {
        Task taskToSave = mapper.toEntity(newTask);
        Task savedTask = repository.save(taskToSave);
        Audit audit = new Audit(LogLevel.INFO,savedTask.getId(),"New Task created" + savedTask.getDescription());
        auditRepository.save(audit);
        return mapper.toResponse(savedTask);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest updateTask) {

        Task taskToUpdate = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found"));

        taskToUpdate.setDescription(updateTask.description());
        taskToUpdate.setPriority(updateTask.priority());
        taskToUpdate.setStatus(updateTask.status());
        taskToUpdate.setUpdatedAt(LocalDateTime.now());

        repository.save(taskToUpdate);

        Audit audit = new Audit(LogLevel.INFO,taskToUpdate.getId(),"Task updated" + taskToUpdate.getDescription());
        auditRepository.save(audit);

        return mapper.toResponse(taskToUpdate);
    }

    @Override
    public void deleteTask(Long id) {
        repository.deleteById(id);

    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task taskToGet = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found"));

        return mapper.toResponse(taskToGet);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = repository.findAll();
        if (tasks.isEmpty()) {
            throw new NotFoundException("No tasks found");
        }
        return mapper.toResponseList(tasks);
    }
}
