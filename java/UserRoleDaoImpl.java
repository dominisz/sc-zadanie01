package com.oryode.repositories.implementation;

import com.oryode.models.User;
import com.oryode.models.UserRole;
import com.oryode.repositories.abstract_layer.UserRoleDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserRole> getUserRoles(User user) {
        return this.entityManager.createQuery("FROM UserRole WHERE user = :user")
                .setParameter("user", user)
                .getResultList();
    }
    @Override
    public List<UserRole> getAll(){
        return this.entityManager.createQuery("FROM UserRole")
                    .getResultList();
    }

    @Override
    public void add(UserRole role) {
        this.entityManager.persist(role);
    }

    @Override
    public void delete(UserRole role) {
        this.entityManager.remove(role);
    }

    @Override
    public void delete(Long id) {
        this.entityManager.remove(this.entityManager.find(UserRole.class, id));
    }
}
