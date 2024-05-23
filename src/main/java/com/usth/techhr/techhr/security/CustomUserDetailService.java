package com.usth.techhr.techhr.security;

import com.usth.techhr.techhr.model.Employee;
import com.usth.techhr.techhr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> user = employeeRepository.findEmployeeByPhone(username);
        if (user.isEmpty()) {
//            Manager manager = managerRepository.findManagerByPhone(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not present"));;
//            return new User(manager.getPhone(), manager.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        Employee employee = user.get();
        return new User(employee.getPhone(), employee.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
