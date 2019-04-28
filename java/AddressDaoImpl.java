package com.oryode.repositories.implementation;

import com.oryode.models.Address;
import com.oryode.repositories.abstract_layer.AddressDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    private String ALL_FIELDS = "id, street, postalCode, city, country";

    @Override
    public List<Address> getAllAddresses() {
        return this.entityManager.createQuery("SELECT " + ALL_FIELDS +" FROM Address", Address.class)
                .getResultList();
    }

    @Override
    public Address getAddressById(Long id) {
        return this.entityManager.find(Address.class, id);
    }

    @Override
    public void add(Address address) {
        this.entityManager.persist(address);
    }

    @Override
    public void delete(Address address) {
        this.entityManager.remove(address);
    }

    @Override
    public void update(Address address) {
        Address existingAddress = getAddressById(address.getId());
        this.entityManager.flush();
    }
}
