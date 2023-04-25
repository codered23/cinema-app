package cinema.dao;

import java.util.Optional;
import cinema.model.Role;

public interface RoleDao {
    Role add(Role role);
    
    Optional<Role> getByName(String roleName);
}
