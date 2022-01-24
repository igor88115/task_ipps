package org.ipps.dao;

import org.ipps.models.Table_employees;
import org.ipps.models.Table_time;

import java.sql.Date;
import java.util.ArrayList;

public interface EmployeeTimeDAOInterface {

    public Table_employees findByLogin(String login);
    public ArrayList<Table_time> findRecordsByLogin(String login);
    public ArrayList<Table_time> findRecordsByloginAndConstraint(Date date_start, Date date_end, String login);

    public ArrayList<Table_time> findAllRecords();
    public ArrayList<Table_time> findByConstraint(Date date_start, Date date_end, String surnameorname);
}
