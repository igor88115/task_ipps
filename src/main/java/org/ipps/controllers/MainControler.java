package org.ipps.controllers;
import org.ipps.models.Table_time;
import org.ipps.services.EmployeeTimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;


@Controller
public class MainControler {

    private final EmployeeTimeService employeeTimeService;

    public MainControler(EmployeeTimeService employeeTimeService) {
        this.employeeTimeService = employeeTimeService;
    }

    @GetMapping("/empl_time")
    public String findrecords(Model model){
//        Берет текущую дату
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Date date_start = null;
//        Получает все запии
        ArrayList<Table_time> records;
        records =  employeeTimeService.findAll();
//        Находит запись первой даты, чтобы автоматически вставить в форму-календарик html
        if (records.isEmpty()) {
            date_start = date;
        }else{
            date_start = records.get(records.size() - 1).getDate_entr();
        }
        records =  employeeTimeService.findAll();
        model.addAttribute("records", records);
        model.addAttribute("date_start", date_start);
        model.addAttribute("date_end", date);
        return "workView";
    }
    @PostMapping("/empl_time")
    public String findrecordsBydateandsurname(Model model,
                                              @RequestParam(name = "date_start") Date date_start,
                                              @RequestParam(name = "date_end") Date date_end,
                                              @RequestParam(name = "surnameorname") String surnameorname){
        ArrayList<Table_time> records;
        records =  employeeTimeService.findByConstraint(date_start, date_end, surnameorname);
        model.addAttribute("records", records);
        model.addAttribute("date_start", date_start);
        model.addAttribute("date_end", date_end);
        model.addAttribute("surnameorname", surnameorname);
        return "workView";
    }

}
