package org.ipps.dao;

import org.ipps.models.Table_employees;
import org.ipps.models.Table_time;

import java.sql.Date;
import java.util.ArrayList;

public interface EmployeeTimeDAOInterface {
    //  Нигде не используется, но может пригодится
    public ArrayList<Table_employees> findAll();

    ArrayList<Table_time> findAllRecords();

    public ArrayList<Table_time> findByConstraint(Date date_start, Date date_end, String surnameorname);
}
