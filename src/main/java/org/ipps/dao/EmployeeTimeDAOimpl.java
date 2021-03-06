package org.ipps.dao;
import org.ipps.models.Table_employees;
import org.ipps.models.Table_time;
import org.ipps.utils.HibernateSessionFactoryUtil;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeTimeDAOimpl implements EmployeeTimeDAOInterface {
//    Поиск через логин
    @Override
    public Table_employees findByLogin(String login) {
        ArrayList<Table_employees> list_table_employees = (ArrayList<Table_employees>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Table_employees where login = :login").setParameter("login", login).list();
        Table_employees table_employees = list_table_employees.get(0);
        return table_employees;
    }
// Вытаскиает все записи
    @Override
    public ArrayList<Table_time> findAllRecords() {
        ArrayList<Table_time> table_employees = (ArrayList<Table_time>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Table_time order by date_entr desc").list();
        return table_employees;
    }
//  Поиск с параметрами
    @Override
    public ArrayList<Table_time> findByConstraint(Date date_start, Date date_end, String surnameorname) {
        ArrayList<Table_time> table_times;
        if (surnameorname.isEmpty()) {
            table_times = (ArrayList<Table_time>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("" +
                            "from Table_time Where date_entr >= :date_start and date_entr <= :date_end")
                    .setParameter("date_start", date_start).setParameter("date_end", date_end).list();
        } else {
            List<String> search = Arrays.asList(surnameorname.split(" "));
//            Проверяет сколько слов было введено через пробел (если только одно, то ищет просто в колонках имени и фамилии по введенному слову)
//            Если ввдены и имя и фамилия, то НЕ выведет однофамильцев, а определенного человека, не зависимо было введено первымм имя или фамилия
            if (search.size()>1){
                table_times = (ArrayList<Table_time>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("" +
                                "from Table_time Where date_entr >= :date_start and date_entr <= :date_end " +
                                "and (employee_id.person_fname in (:surnameorname) and employee_id.person_surname in (:surnameorname))")
                        .setParameter("date_start", date_start).setParameter("date_end", date_end).setParameterList("surnameorname", search).list();
            }else{
                table_times = (ArrayList<Table_time>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("" +
                                "from Table_time Where date_entr >= :date_start and date_entr <= :date_end " +
                                "and (employee_id.person_fname in (:surnameorname) or employee_id.person_surname in (:surnameorname))")
                        .setParameter("date_start", date_start).setParameter("date_end", date_end).setParameterList("surnameorname", search).list();
            }
        }
        return table_times;
    }


    @Override
    public ArrayList<Table_time> findRecordsByLogin(String login) {
        ArrayList<Table_time> list_table_time = (ArrayList<Table_time>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from Table_time where employee_id.login = :login order by date_entr desc").setParameter("login", login).list();
        return list_table_time;
    }

    @Override
    public ArrayList<Table_time> findRecordsByloginAndConstraint(Date date_start, Date date_end, String login) {
        ArrayList<Table_time> list_table_time = (ArrayList<Table_time>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("" +
                        "from Table_time Where date_entr >= :date_start and date_entr <= :date_end " +
                        "and employee_id.login like :login")
                        .setParameter("date_start", date_start).setParameter("date_end", date_end).setParameter("login", login).list();
        return list_table_time;
    }
}
