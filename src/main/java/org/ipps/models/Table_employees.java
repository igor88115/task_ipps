package org.ipps.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Модель из таблицы (наверное стоило бы назвать класс по-другому)
@Entity
@Table(name = "table_employees")
public class Table_employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String person_surname;
    private String person_fname;
    private String person_lname;

    @OneToMany(mappedBy = "employee_id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Table_time> table_timeList;


    public Table_employees() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson_surname() {
        return person_surname;
    }

    public void setPerson_surname(String person_surname) {
        this.person_surname = person_surname;
    }

    public String getPerson_fname() {
        return person_fname;
    }

    public void setPerson_fname(String person_fname) {
        this.person_fname = person_fname;
    }

    public String getPerson_lname() {
        return person_lname;
    }

    public void setPerson_lname(String person_lname) {
        this.person_lname = person_lname;
    }

    public List<Table_time> getTable_timeList() {
        return table_timeList;
    }

    public void setTable_timeList(List<Table_time> table_timeList) {
        this.table_timeList = table_timeList;
    }
}
