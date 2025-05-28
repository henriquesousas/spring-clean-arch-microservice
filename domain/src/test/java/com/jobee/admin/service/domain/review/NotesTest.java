package com.jobee.admin.service.domain.review;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class NotesTest {

    @Test
    public void givenAPositiveAndNegativeNotes_whenInstantiate_shouldCreateANotes() {
//
//        final var expectedPositiveNotes = Set.of("note1", "note2");
//        final var expectedNegativeNotes = Set.of("note1", "note2");
//
//        final var actualNote = Notes.newNote(expectedPositiveNotes, expectedNegativeNotes);
//
//        Assertions.assertEquals(actualNote.getPositives().size(), 2);
//        Assertions.assertEquals(actualNote.getNegatives().size(), 2);
    }

    @Test
    public void givenAnDefaultNotes_whenAdd_shouldUpdateNotes() {

//        final var expectedPositiveNotes = Set.of("note1", "note2");
//        final var expectedNegativeNotes = Set.of("note1", "note2");
//
//        final var actualNote = Notes.newNote(expectedPositiveNotes, expectedNegativeNotes);
//        actualNote.addPositive("n1");
//        actualNote.addNegative("n1");
//
//
//        Assertions.assertEquals(actualNote.getPositives().size(), 3);
//        Assertions.assertEquals(actualNote.getNegatives().size(), 3);
    }

    @Test
    public void givenAnDefaultNotes_whenUpdateWithEqualsValue_shouldNotUpdateNotes() {

//        final var expectedPositiveNotes = Set.of("note1", "note2");
//        final var expectedNegativeNotes = Set.of("note1", "note2");
//
//        final var actualNote = Notes.newNote(expectedPositiveNotes, expectedNegativeNotes);
//        actualNote.addPositive("note1");
//        actualNote.addPositive("note1");
//
//
//        Assertions.assertEquals(actualNote.getPositives().size(), 2);
//        Assertions.assertEquals(actualNote.getNegatives().size(), 2);
    }

    @Test
    public void givenADefaultValues_wheAddMoreThanLimit_shouldNotificationError() {

//        final var expectedPositiveNotes = Set.of("note1", "note2");
//        final var expectedNegativeNotes = Set.of("note1", "note2");
//
//        final var actualNote = Notes.newNote(expectedPositiveNotes, expectedNegativeNotes);
//        for (int i = 0; i < 29; i++)   actualNote.addPositive(i + "note");
//        for (int i = 0; i < 29; i++)   actualNote.addNegative(i + "note");
//
//        System.out.println(actualNote.getNotification().getErrors());
//
//        Assertions.assertTrue(actualNote.getNotification().hasError());
//        Assertions.assertEquals(actualNote.getNotification().getErrors().size(),2);
        //TODO: here
//        Assertions.assertEquals(actualNote.getNotification().getErrors().get(0).message(), "Nota positiva excede o tamanho máximo de 30 caracteres");
//        Assertions.assertEquals(actualNote.getNotification().getErrors().get(1).message(), "Nota negativa excede o tamanho máximo de 30 caracteres");
    }

    @Test
    public void givenAnInvalidNotes_whenInstantiate_shouldNotificationError() {

//        final var expectedPositiveNotes = new HashSet<String>();
//        final var expectedNegativeNotes = new HashSet<String>();
//        for (int i = 0; i<31; i++) {
//            expectedPositiveNotes.add("note" + i);
//            expectedNegativeNotes.add("note" + i);
//        }
//
//        final var actualNote = Notes.newNote(expectedPositiveNotes, expectedNegativeNotes);
//        Assertions.assertTrue(actualNote.getNotification().hasError());;
    }

    @Test
    public void givenANullOrEmptyNote_whenInstantiate_shouldNotificationError() {

//        final var expectedPositiveNotes = Set.of("");
//        final var expectedNegativeNotes = Set.of("");
//
//
//        final var actualNote = Notes.newNote(expectedPositiveNotes, expectedNegativeNotes);
//        Assertions.assertTrue(actualNote.getNotification().hasError());
//        Assertions.assertEquals(actualNote.getNotification().getErrors().size(),2);
        //TODO: here
//        Assertions.assertEquals(actualNote.getNotification().getErrors().get(0).message(),"Nota positiva não pode ser vazia ou nula");
//        Assertions.assertEquals(actualNote.getNotification().getErrors().get(1).message(),"Nota negativa não pode ser vazia ou nula");
    }
}
