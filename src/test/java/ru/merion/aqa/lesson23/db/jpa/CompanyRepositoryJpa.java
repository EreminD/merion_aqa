package ru.merion.aqa.lesson23.db.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ru.merion.aqa.lesson23.db.CompanyRepository;
import ru.merion.aqa.lesson23.db.model.CompanyEntity;

import java.sql.SQLException;
import java.util.List;

public class CompanyRepositoryJpa implements CompanyRepository {
    private final EntityManager entityManager;

    public CompanyRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CompanyEntity> getAll() {
        String sql = "SELECT c FROM CompanyEntity c WHERE c.deleted is null";
        TypedQuery<CompanyEntity> selectAll = entityManager.createQuery(sql, CompanyEntity.class);
        return selectAll.getResultList();
    }

    @Override
    public CompanyEntity getById(int id) {
        return entityManager.find(CompanyEntity.class, id);
    }

    @Override
    public int create(String name, String description) throws SQLException {
        CompanyEntity newCompany = new CompanyEntity();
        newCompany.setCreated();
        newCompany.setName(name);
        newCompany.setDescription(description);
        newCompany.setChanged();

        entityManager.getTransaction().begin();
        entityManager.persist(newCompany);

        return newCompany.getId();
    }

    @Override
    public long count() {
        String sql = "SELECT count(c) FROM CompanyEntity c WHERE c.deleted IS NULL";
        TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
        return query.getSingleResult();
    }

    @Override
    public long count(boolean isActive) {
        String sql = "SELECT count(c) FROM CompanyEntity c WHERE c.deleted IS NULL and c.isActive=:active";
        TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
        query.setParameter("active", isActive);
        long res = query.getSingleResult();
        return res;
    }
    @Override
    public void deleteById(int id) {
        CompanyEntity entity = entityManager.find(CompanyEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
