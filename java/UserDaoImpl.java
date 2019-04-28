package com.oryode.repositories.implementation;

import com.oryode.models.Company;
import com.oryode.models.User;
import com.oryode.repositories.abstract_layer.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getById(Long id) {
        return this.entityManager.find(User.class, id);
    }

    @Override
    public void add(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        User currentUser = getById(user.getId());
        this.entityManager.flush();
    }

    @Override
    public User findByUsername(String username){
        try {
            return (User) this.entityManager.createNamedQuery("@GET_BY_USERNAME")
                    .setParameter("username", username)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return this.entityManager.createNamedQuery("@GET_ALL_USERS")
                                 .getResultList();
    }

    @Override
    public List<User> getUsersByIds(Set<Long> ids) {
        return this.entityManager.createNamedQuery("@GET_USERS_BY_IDS")
                .setParameter("ids",ids)
                .getResultList();
    }

    @Override
    public List<User> getCompanyUsers(Company company) {
        return this.entityManager.createNamedQuery("@GET_ALL_COMPANY_USERS")
                .setParameter("companyName", company.getCompanyName())
                .getResultList();
    }

    @Override
    public void delete(User user) {
        this.entityManager.remove(user);
    }
}
