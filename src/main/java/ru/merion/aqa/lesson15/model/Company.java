package ru.merion.aqa.lesson15.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Company(int id, String name, boolean isActive, String description) {
}
