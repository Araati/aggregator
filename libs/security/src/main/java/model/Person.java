package model;

import com.ourcompany.content.model.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import role.Role;

import java.time.LocalDateTime;
import java.util.Collection;

public class Person implements UserDetails {

    private final Long id;

    private final String username;

    private final String password;

    private final Role role;

    private final boolean enabled;

    private final LocalDateTime createAt;

    private final LocalDateTime updateAt;

    public Person(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.createAt = user.getCreatedAt();
        this.updateAt = user.getUpdatedAt();
        this.enabled = user.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.authorities();
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
