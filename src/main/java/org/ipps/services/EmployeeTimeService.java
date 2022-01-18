package org.ipps.services;

import org.ipps.dao.EmployeeTimeDAOimpl;
import org.ipps.models.Table_time;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

// Прослойка между классом, который напрямую взаиможействует с базой данный и контроллером
@Service
public class EmployeeTimeService {

    private EmployeeTimeDAOimpl employeeTimeDAOimpl = new EmployeeTimeDAOimpl();

    public ArrayList<Table_time> findAll() {
        return employeeTimeDAOimpl.findAllRecords();
    }

    public ArrayList<Table_time> findByConstraint(Date date_start, Date date_end, String surnameorname) {

        return employeeTimeDAOimpl.findByConstraint(date_start, date_end, surnameorname);
    }
}
