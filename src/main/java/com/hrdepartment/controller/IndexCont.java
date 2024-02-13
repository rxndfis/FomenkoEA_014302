package com.hrdepartment.controller;

import com.hrdepartment.controller.main.Attributes;
import com.hrdepartment.model.enums.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1() {
        return "redirect:/about";
    }

    @GetMapping("/index")
    public String index2() {
        return "redirect:/about";
    }

    @GetMapping("/catalog")
    public String index2(Model model) {
        AddAttributesCatalog(model);
        return "humans";
    }

    @GetMapping("/filter")
    public String filter(Model model, @RequestParam String fio) {
        AddAttributesFilter(model,fio);
        return "humans";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam Marital marital, @RequestParam Origin origin, @RequestParam Citizenship citizenship,
                         @RequestParam YesNo retiree, @RequestParam YesNo conscripted, @RequestParam Disability disability) {
        AddAttributesCatalogSearch(model, marital, origin, citizenship, retiree, conscripted, disability);
        return "humans";
    }
}
