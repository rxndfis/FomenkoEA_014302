package com.hrdepartment.controller;

import com.hrdepartment.controller.main.Attributes;
import com.hrdepartment.model.*;
import com.hrdepartment.model.enums.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/humans")
public class HumansCont extends Attributes {

    @GetMapping("/{id}")
    public String Human(Model model, @PathVariable Long id) {
        AddAttributesHuman(model, id);
        return "human";
    }

    @GetMapping("/{id}/comments")
    public String HumanComments(@PathVariable Long id, @RequestParam String text) {
        humanCommentsRepo.save(new HumanComments(text, getDateNow(), getUser(), usersRepo.getReferenceById(id)));
        return "redirect:/humans/{id}";
    }

    @GetMapping("/edit/{id}")
    public String HumanEdit(Model model, @PathVariable Long id) {
        AddAttributesHumanEdit(model, id);
        return "human_edit";
    }

    @GetMapping("/add")
    public String HumanAdd(Model model) {
        AddAttributes(model);
        return "human_add";
    }

    @PostMapping("/add")
    public String HumanAdd(Model model, @RequestParam String username, @RequestParam String password) {
        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            AddAttributes(model);
            return "human_add";
        }
        usersRepo.save(new Users(username, password, Role.CLIENT));
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String HumanEditOld(
            Model model, @PathVariable Long id, @RequestParam MultipartFile photo, @RequestParam String surname,
            @RequestParam String name, @RequestParam String patronymic, @RequestParam String passport,
            @RequestParam String passport_number, @RequestParam String date, @RequestParam String issued,
            @RequestParam String issued_date, @RequestParam String identity, @RequestParam String address,
            @RequestParam String tel_mob, @RequestParam String tel_home, @RequestParam String email,
            @RequestParam String job, @RequestParam String post, @RequestParam int income,
            @RequestParam Marital marital, @RequestParam Origin origin, @RequestParam Citizenship citizenship,
            @RequestParam YesNo retiree, @RequestParam YesNo conscripted, @RequestParam Disability disability) {
        Users user = usersRepo.getReferenceById(id);
        try {
            if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String result = "humans/" + uuidFile + "_" + photo.getOriginalFilename();
                photo.transferTo(new File(uploadImg + "/" + result));
                user.getPrimarys().setPhoto(result);
            }
        } catch (IOException e) {
            AddAttributesHumanEdit(model, id);
            model.addAttribute("message", "Некорректные данные!");
            return "human_edit";
        }

        Primarys primarys = user.getPrimarys();
        primarys.set(surname, name, patronymic, passport, passport_number);
        Secondary secondary = user.getSecondary();
        secondary.set(date, issued, issued_date, identity, address, tel_mob, tel_home, email, job, post, income);
        Tertiary tertiary = user.getTertiary();
        tertiary.set(marital, origin, citizenship, retiree, conscripted, disability);

        usersRepo.save(user);

        return "redirect:/humans/{id}";
    }
}
