package com.hrdepartment.controller;

import com.hrdepartment.controller.main.Attributes;
import com.hrdepartment.model.Jobs;
import com.hrdepartment.model.Vacancy;
import com.hrdepartment.model.enums.JobStatus;
import com.hrdepartment.model.enums.VacancyStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jobs")
public class JobsCont extends Attributes {
    @GetMapping
    public String jobs(Model model) {
        AddAttributes(model);
        model.addAttribute("jobs", jobsRepo.findAll());
        model.addAttribute("jobStatuses", JobStatus.values());
        return "jobs";
    }

    @GetMapping("/res/{id}")
    public String jobRes(@PathVariable Long id) {
        if (vacancyRepo.findByJob_IdAndUser_Id(id, getUser().getId()) == null) {
            vacancyRepo.save(new Vacancy(jobsRepo.getReferenceById(id), getUser()));
        }
        return "redirect:/jobs";
    }

    @PostMapping("/add")
    public String jobAdd(@RequestParam String name, @RequestParam int exp, @RequestParam int salary) {
        jobsRepo.save(new Jobs(name, exp, salary));
        return "redirect:/jobs";
    }

    @PostMapping("/edit/{id}")
    public String jobEdit(@RequestParam String name, @RequestParam int exp, @RequestParam int salary, @RequestParam JobStatus status, @PathVariable Long id) {
        Jobs job = jobsRepo.getReferenceById(id);
        job.setName(name);
        job.setExp(exp);
        job.setSalary(salary);
        job.setStatus(status);
        jobsRepo.save(job);
        return "redirect:/jobs";
    }
}
