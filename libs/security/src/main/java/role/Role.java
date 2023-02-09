package role;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

public enum Role {
    USER(Set.of(Permissions.USER)), ADMIN(Set.of(Permissions.USER, Permissions.ADMIN));

    final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> authorities(){
        return permissions.stream().map(n -> new SimpleGrantedAuthority(n.p)).toList();
    }
}
