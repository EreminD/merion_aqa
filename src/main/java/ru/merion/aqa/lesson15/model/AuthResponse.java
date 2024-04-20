package ru.merion.aqa.lesson15.model;

public record AuthResponse(String userToken, String role, String displayName, String login) {
}
