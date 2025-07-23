package com.placebra.edu.PP_3_1_2.dto;

import com.placebra.edu.PP_3_1_2.entity.Role;
import com.placebra.edu.PP_3_1_2.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String password;
    private List<Role> roles;
    private List<String> simpleRoles;

    public CustomUserDetails(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();

        this.simpleRoles = roles.stream()
                .map(role -> role.getName().replace("ROLE_", "").toLowerCase())
                .map(e -> e.substring(0, 1).toUpperCase() + e.substring(1))
                .toList();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    //Роли в формате Admin, User, .....
    public List<String> getSimpleRoles() {
        return simpleRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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
