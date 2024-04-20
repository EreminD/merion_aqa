package ru.merion.aqa.lesson23.db.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

public class CompanyDb {
    private final int id;
    private final String name;
    private final String description;
    private final boolean isActive;
    private final Timestamp createTimestamp;
    private final Timestamp changeTimestamp;
    private final Timestamp deletedAt;

    public CompanyDb(int id, String name, String description, boolean isActive, Timestamp createTimestamp, Timestamp changeTimestamp, Timestamp deletedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.createTimestamp = createTimestamp;
        this.changeTimestamp = changeTimestamp;
        this.deletedAt = deletedAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public Timestamp getChangeTimestamp() {
        return changeTimestamp;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public static CompanyDb of(ResultSet rs) throws SQLException {
        return new CompanyDb(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBoolean("is_active"),
                rs.getTimestamp("create_timestamp"),
                rs.getTimestamp("change_timestamp"),
                rs.getTimestamp("deleted_at")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyDb companyDb)) return false;
        return getId() == companyDb.getId() && isActive() == companyDb.isActive() && Objects.equals(getName(), companyDb.getName()) && Objects.equals(getDescription(), companyDb.getDescription()) && Objects.equals(getCreateTimestamp(), companyDb.getCreateTimestamp()) && Objects.equals(getChangeTimestamp(), companyDb.getChangeTimestamp()) && Objects.equals(getDeletedAt(), companyDb.getDeletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), isActive(), getCreateTimestamp(), getChangeTimestamp(), getDeletedAt());
    }

    @Override
    public String toString() {
        return "CompanyDb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", createTimestamp=" + createTimestamp +
                ", changeTimestamp=" + changeTimestamp +
                ", deleted_at=" + deletedAt +
                '}';
    }
}
