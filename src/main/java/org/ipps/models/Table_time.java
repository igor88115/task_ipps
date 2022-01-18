package org.ipps.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


// Модель из таблицы (наверное стоило бы назвать класс по-другому)
@Entity
@Table(name = "table_time")
public class Table_time {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Table_employees employee_id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int time_id;
    private Date date_entr;
    private Time min_time;
    private Time max_time;


    public Table_time() {
    }


    public Table_time(Table_employees employee_id) {
        this.employee_id = employee_id;
    }

    public Table_employees getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Table_employees employee_id) {
        this.employee_id = employee_id;
    }

    public int getTime_id() {
        return time_id;
    }

    public void setTime_id(int time_id) {
        this.time_id = time_id;
    }

    public Date getDate_entr() {
        return date_entr;
    }

    public void setDate_entr(Date date_enr) {
        this.date_entr = date_enr;
    }

    public Time getMin_time() {
        return min_time;
    }

    public void setMin_time(Time min_time) {
        this.min_time = min_time;
    }

    public Time getMax_time() {
        return max_time;
    }

    public void setMax_time(Time max_time) {
        this.max_time = max_time;
    }
}
