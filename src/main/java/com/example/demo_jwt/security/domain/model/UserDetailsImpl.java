package com.example.demo_jwt.security.domain.model;

import com.example.demo_jwt.users.domain.entity.Driver;
import com.example.demo_jwt.users.domain.entity.Supervisor;
import com.example.demo_jwt.users.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    /*public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorityList =
                user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(
                                role.getRoleName().name())).collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                authorityList);
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public Map<String, String> getClaims() {
        return Map.of(
                "username", username
        );
    }
}