package com.jobee.admin.service.domain.review.valueobjects;

import com.jobee.admin.service.domain.shared.ValueObject;
import com.jobee.admin.service.domain.shared.validation.Error;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Notes extends ValueObject<Notes> {

    private static final int MAX_LENGTH = 30;
    private Set<String> positive;
    private Set<String> negative;

    private Notes(Set<String> positives, Set<String> negatives) {
        this.positive = positives;
        this.negative = negatives;
        selfValidate();
    }

    public static Notes newNote() {
        return new Notes(Set.of(), Set.of());
    }

    public static Notes newNote(Set<String> positive, Set<String> negative) {
        return new Notes(positive, negative);
    }

    public void addPositive(String note) {
        if (validateCurrentNote(this.positive, note, "positiva")) return;

        Set<String> copy = new HashSet<>(this.positive);
        copy.add(note);
        this.positive = copy;
    }

    public void addNegative(String note) {
        if (validateCurrentNote(negative, note, "negativa")) return;

        Set<String> copy = new HashSet<>(this.negative);
        copy.add(note);
        this.negative = copy;
    }

    public void removePositive(String note) {
        removeNoteFrom(this.positive, note);
    }

    public void removeNegative(String note) {
        removeNoteFrom(this.negative, note);
    }

    public Set<String> getPositives() {
        return Collections.unmodifiableSet(positive);
    }

    public Set<String> getNegatives() {
        return Collections.unmodifiableSet(negative);
    }

    @Override
    protected void selfValidate() {
        validateNotes(positive, "positiva");
        validateNotes(negative, "negativa");
    }

    @Override
    public Notes getValue() {
        return this;
    }

    private void removeNoteFrom(Set<String> notes, String note) {
        final var actualNote = notes
                .stream()
                .filter(reviewPoint -> reviewPoint.equals(note))
                .findFirst()
                .orElse(null);

        if (actualNote == null) {
            this.notification.append(new Error("%s não encontrado".formatted(note)));
            return;
        }

        notes.remove(actualNote);
    }

    private void validateNotes(Set<String> notes, String type) {
        notes.stream()
                .filter(note -> note == null || note.isBlank())
                .forEach(note -> this.notification.append(new Error("Nota %s não pode ser vazia ou nula".formatted(type))));

        if (notes.size() > MAX_LENGTH) {
            this.notification.append(new Error("Nota %s excede o tamanho máximo de %d caracteres".formatted(type, MAX_LENGTH)));
        }
    }

    private boolean validateCurrentNote(Set<String> notes,String newNote,String type ) {
        if (newNote == null || newNote.isBlank()) {
            this.notification.append(new Error("Nota %s não pode ser vazia ou nula".formatted(type)));
            return true;
        }

        if (notes.size() >= MAX_LENGTH) {
            this.notification.append(new Error("Nota %s excede o tamanho máximo de %d caracteres".formatted(type, MAX_LENGTH)));
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notes notes)) return false;
        return Objects.equals(positive, notes.positive) && Objects.equals(negative, notes.negative);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positive, negative);
    }
}
