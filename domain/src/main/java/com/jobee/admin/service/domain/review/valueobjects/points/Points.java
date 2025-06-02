package com.jobee.admin.service.domain.review.valueobjects.points;

import com.jobee.admin.service.domain.ValueObject;
import com.jobee.admin.service.domain.validation.Error;

import java.util.*;
import java.util.stream.Collectors;

// FeedbackPoints
// Feedback
// ReviewPoints
public abstract class Points extends ValueObject<Set<String>> {

    protected static final int MAX_LENGTH = 30;
    protected static final int MAX_CHARACTER = 30;
    protected Set<String> points;

    protected Points(Set<String> points) {
        this.points = Objects.requireNonNullElseGet(points, HashSet::new);
        selfValidate();
    }

    public void add(String newValue) {

        validateSinglePoint(newValue);
        validateMaxLen();

        if (this.notification.hasError()) return;

        Set<String> copy = new HashSet<>(this.points);
        copy.add(newValue);
        this.points = copy;
    }

    public void remove(String value) {
        final var removed = this.points.remove(value);
        if (!removed) {
            this.notification.append(new Error("%s não encontrado".formatted(value)));
        }
    }

    public String asString() {
        return points
                .stream()
                .map(String::trim)
                .collect(Collectors.joining(","));
    }

    public void asSet(String values) {
        if (values == null || values.trim().isEmpty()) return;

        Arrays.stream(values.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .forEach(this::add);
    }

    private void validateSinglePoint(String value) {
        if (value == null || value.trim().isEmpty()) {
            this.notification.append(new Error("Nenhum ponto pode ser nulo ou vazio"));
        }

        if (value != null && value.length() > MAX_LENGTH) {
            this.notification.append(
                    new Error("Ponto excedeu o limite de " + MAX_CHARACTER + " caracteres: \"" + value + "\""));
        }
    }

    private void validateMaxLen() {
        if (this.points.size() >= MAX_LENGTH) {
            this.notification.append(new Error(getMaxSizeExceededMessage()));
        }
    }

    protected void selfValidate() {
        validateMaxLen();

        for (String actualValue : points) {
            validateSinglePoint(actualValue);
        }
    }

    protected abstract String getMaxSizeExceededMessage();

    @Override
    public Set<String> getValue() {
        return Collections.unmodifiableSet(this.points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Points points1)) return false;
        return Objects.equals(points, points1.points);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(points);
    }
}
