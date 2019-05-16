package com.wallet.walletapi.config.security;

import com.wallet.walletapi.exception.EntityNotFoundException;
import com.wallet.walletapi.interfaces.ICustomer;
import com.wallet.walletapi.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private ICustomer ic;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return mockUser(username);
    }

    private UserDetails mockUser(String username) {
        try {
            Customer customer = ic.getCustomerByUsername(username);
            if (customer == null) {
                throw new EntityNotFoundException("444", "Customer doesn't exist!");
            }
            //return new User(customer.getUsername(), String.format("{noop}%s", customer.getPassword()), getAuthority());
            return new User(customer, getAuthority());
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException("Invalid username and password.");
        }
    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
