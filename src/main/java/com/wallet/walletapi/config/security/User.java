package com.wallet.walletapi.config.security;

import com.wallet.walletapi.model.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User implements UserDetails {
    private static final long serialVersionUID = -65950709882074455L;

    private Customer customer;
    private Collection authorities;

    public User(Customer customer, Collection authorities) {
        this.customer = customer;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getFullName() {
        if (customer == null) {
            return null;
        } else {
            return customer.getFirstName() +" "+ customer.getLastName();
        }
    }

    @Override
    public String getPassword() {
        if (customer == null) {
            return null;
        } else {
            return String.format("{noop}%s", customer.getPassword());
        }
    }

    @Override
    public String getUsername() {
        if (customer == null) {
            return null;
        } else {
            return customer.getUsername();
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
