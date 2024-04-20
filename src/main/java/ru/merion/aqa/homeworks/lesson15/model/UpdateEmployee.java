package ru.merion.aqa.homeworks.lesson15.model;

public record UpdateEmployee(String lastName,
                             String email,
                             String url,
                             String phone,
                             boolean isActive) {
}
