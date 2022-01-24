package org.ipps.controllers;

import org.ipps.models.Table_employees;
import org.ipps.models.Table_time;
import org.ipps.services.EmployeeTimeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;


@Controller
public class PersonalizedController {

    private final EmployeeTimeService employeeTimeService;

    public PersonalizedController(EmployeeTimeService employeeTimeService) {
        this.employeeTimeService = employeeTimeService;
    }

    @GetMapping("/")
    public String findPersonalRecords(Authentication authentication, Model model) {
        if (authentication == null) {
            return "redirect:/login";
        }
//        Получает логин  авторизованного пользователя
        String login = authentication.getName();
        Table_employees table_employees = employeeTimeService.finByLogin(login);
//        Берет текущую дату
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Date date_start = null;
//        Получает все запии
        ArrayList<Table_time> records = employeeTimeService.findRecordsByLogin(login);
//        Находит запись первой даты, чтобы автоматически вставить в форму-календарик html
        if (records.isEmpty()) {
            date_start = date;
        } else {
            date_start = records.get(records.size() - 1).getDate_entr();
        }

        model.addAttribute("records", records);
        model.addAttribute("date_start", date_start);
        model.addAttribute("date_end", date);
        model.addAttribute("auth_employee", table_employees);
        return "personalizedView";
    }

    @PostMapping("/")
    public String findPersonalRecordsByConstraint(Model model, Authentication authentication,
                                                  @RequestParam(name = "date_start") Date date_start,
                                                  @RequestParam(name = "date_end") Date date_end) {
//        Получает логин  авторизованного пользователя
        String login = authentication.getName();
        Table_employees table_employees = employeeTimeService.finByLogin(login);
        ArrayList<Table_time> records = employeeTimeService.findRecordsByLoginAndConstraintDate(date_start, date_end, login);

        model.addAttribute("records", records);
        model.addAttribute("date_start", date_start);
        model.addAttribute("date_end", date_end);
        model.addAttribute("auth_employee", table_employees);
        return "personalizedView";
    }
}
