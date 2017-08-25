
package com.libertymutual.goforcode.timeless.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libertymutual.goforcode.timeless.models.TimeSheet;
import com.libertymutual.goforcode.timeless.services.TimeSheetRepository;

@Controller
@RequestMapping("/")
public class TimelessController {
    
    private TimeSheetRepository repository;
    private TimeSheet currentItem;

    public TimelessController(TimeSheetRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("")
    public String redirectToApplication() {
        return "redirect:/update";
    }
    
    @GetMapping("update")
    public String showDefault(Model model) {
        List<TimeSheet> items = repository.getAll();
        currentItem = repository.getCurrentTimeSheet();
        model.addAttribute("timeSheets", items);
        model.addAttribute("hasTimeSheets", !items.isEmpty());
        if (currentItem != null) {
            System.out.println("currentItem is not null");
            model.addAttribute("totalHours", currentItem.getTotalHours());
            model.addAttribute("hasNoItem", false);
            model.addAttribute("hasItem", true);
            model.addAttribute("weekOf", currentItem.getWeekOf());
            model.addAttribute("mondayHours", currentItem.getMondayHours());
            model.addAttribute("tuesdayHours", currentItem.getTuesdayHours());
            model.addAttribute("wednesdayHours", currentItem.getWednesdayHours());
            model.addAttribute("thursdayHours", currentItem.getThursdayHours());
            model.addAttribute("fridayHours", currentItem.getFridayHours());
        } else {
            System.out.println("current item is null");
            model.addAttribute("totalHours", "0.0");
            model.addAttribute("hasNoItem", true);
            model.addAttribute("hasItem", false);
        }
        return "timeless/default";
    }
    
    @PostMapping("update")
    public String update(TimeSheet item, String theButtons) {
        if (theButtons.equals("submit")) {
            repository.submit(item);    
        } else {
            if (currentItem == null) {
                item.setId(repository.getNextId());
                item.setIsComplete(false);  
                repository.create(item);
            }
            currentItem = item;
        }
        return "redirect:/update";
    }
    
}
