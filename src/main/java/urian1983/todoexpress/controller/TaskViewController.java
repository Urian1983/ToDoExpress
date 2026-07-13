package urian1983.todoexpress.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import urian1983.todoexpress.dto.TaskRequest;
import urian1983.todoexpress.model.TaskPriority;
import urian1983.todoexpress.model.TaskStatus;
import urian1983.todoexpress.service.TaskService;
import urian1983.todoexpress.webform.TaskFormDTO;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService service;

    public TaskViewController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tasks", service.getAllTasks());
        return "tasks/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("taskForm", new TaskFormDTO());
        model.addAttribute("priorities", TaskPriority.values());
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("isEdit", false);
        return "tasks/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        var task = service.getTaskById(id);

        var form = new TaskFormDTO();
        form.setId(task.id());
        form.setDescription(task.description());
        form.setPriority(task.priority());
        form.setStatus(task.status());

        model.addAttribute("taskForm", form);
        model.addAttribute("priorities", TaskPriority.values());
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("isEdit", true);
        return "tasks/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("taskForm") TaskFormDTO form,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("priorities", TaskPriority.values());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("isEdit", false);
            return "tasks/form";
        }

        var request = new TaskRequest(form.getDescription(), form.getPriority(), form.getStatus());
        service.createTask(request);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("taskForm") TaskFormDTO form,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("priorities", TaskPriority.values());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("isEdit", true);
            return "tasks/form";
        }

        var request = new TaskRequest(form.getDescription(), form.getPriority(), form.getStatus());
        service.updateTask(id, request);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteTask(id);
        return "redirect:/tasks";
    }
}
