package com.example;


import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CarRepository {

    @PersistenceContext
    private EntityManager em;

    public List<CarEntity> findAll() {
        return em.createNamedQuery("Cars.findAll", CarEntity.class).getResultList();
    }

    public Optional<CarEntity> findBy(int id) {
        return Optional.ofNullable(em.find(CarEntity.class, id));
    }

    @Transactional
    public void save(CarEntity carEntity) {
        if (carEntity.getId() != null && em.find(CarEntity.class, carEntity.getId()) != null) {
            em.merge(carEntity);
        } else {
            carEntity.setId(null);
            em.persist(carEntity);
        }
    }

    @Transactional
    public void delete(int id) {
        CarEntity entity = em.find(CarEntity.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

}
