package cinema.app.dao.impl;

import cinema.app.dao.AbstractDao;
import cinema.app.dao.RoleDao;
import cinema.app.model.Role;
import cinema.app.model.RoleName;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        String query = "FROM Role "
                + "WHERE name = : roleName";
        try (Session session = factory.openSession()) {
            Query queryRoleByName = session.createQuery(query);
            queryRoleByName.setParameter("roleName", RoleName.valueOf(roleName));
            return queryRoleByName.uniqueResultOptional();
        }
    }
}
