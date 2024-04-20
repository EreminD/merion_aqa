package ru.merion.aqa.lesson23.db.model;

import jakarta.persistence.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Представление таблицы company
 */
@Entity
@Table(name = "company", schema = "public", catalog = "x_clients_xxet")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "create_timestamp", nullable = false)
    private Timestamp created;

    @Column(name = "change_timestamp", nullable = false)
    private Timestamp changed;

    @Column(name = "deleted_at", nullable = true)
    private Timestamp deleted;

    public static CompanyEntity of(ResultSet rs) throws SQLException {
        CompanyEntity entity = new CompanyEntity();
        entity.setId(rs.getInt("id"));
        entity.setName(rs.getString("name"));
        entity.setDescription(rs.getString("description"));
        entity.setActive(rs.getBoolean("is_active"));
        entity.setCreated();
        entity.setChanged();
        entity.setDeleted(rs.getTimestamp("deleted_at"));
        return entity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.setChanged();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.setChanged();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.setChanged();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        this.setChanged();
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated() {
        this.created = Timestamp.valueOf(LocalDateTime.now());
        this.setChanged();
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged() {
        this.changed = Timestamp.valueOf(LocalDateTime.now());
        ;
    }

    public Timestamp getDeleted() {
        return deleted;
    }

    public void setDeleted(Timestamp deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyEntity that)) return false;
        return getId() == that.getId() && isActive() == that.isActive() && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getCreated(), that.getCreated()) && Objects.equals(getChanged(), that.getChanged()) && Objects.equals(getDeleted(), that.getDeleted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), isActive(), getCreated(), getChanged(), getDeleted());
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", created=" + created +
                ", changed=" + changed +
                ", deleted=" + deleted +
                '}';
    }
}
