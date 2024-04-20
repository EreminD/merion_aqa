package ru.merion.aqa.homeworks.lesson15.model;

public record CreateEmployee(String firstName,
                             String lastName,
                             int companyId,
                             String email,
                             String phone) {
}
