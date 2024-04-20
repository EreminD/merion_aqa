package ru.merion.aqa.lesson23.db;

import io.qameta.allure.Step;
import ru.merion.aqa.lesson23.db.model.CompanyEntity;

import java.sql.SQLException;
import java.util.List;

public interface CompanyRepository {

    /**
     * Возвращает список компаний из БД
     * <p>Примечание: возвращаются только компании, у которых deleted_at=null</p>
     *
     * @return список компаний (CompanyEntity)
     */
    List<CompanyEntity> getAll();

    /**
     * Возвращает информацию о компании по ее id
     * @param id идентификатор компании
     * @return {@link CompanyEntity}
     * @throws SQLException
     */
    CompanyEntity getById(int id) throws SQLException;

    /**
     * Создает в БД новую компанию
     *
     * @param name Название компании
     * @param description Описание компании
     * @return int идентификатор компании
     * @throws SQLException
     */
    int create(String name, String description) throws SQLException;

    long count() throws SQLException;

    long count(boolean isActive) throws SQLException;

    void deleteById(int id) throws SQLException;
}
