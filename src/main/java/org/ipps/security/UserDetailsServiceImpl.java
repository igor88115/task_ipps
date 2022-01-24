package org.ipps.security;

import org.ipps.dao.EmployeeTimeDAOimpl;
import org.ipps.models.Table_employees;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private EmployeeTimeDAOimpl employeeTimeDAOimpl = new EmployeeTimeDAOimpl();

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Table_employees table_employees = employeeTimeDAOimpl.findByLogin(login);
        if (table_employees == null){
            throw new UsernameNotFoundException("employee does not exist!");
        }
        return UserDetailsimpl.fromUser(table_employees);
    }
}