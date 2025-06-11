package com.jobee.admin.service.configuration;

import com.jobee.admin.service.infrastructure.configuration.json.Json;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonTest {

    @Test
    public void givenAObjectValue_whenConvertToJson_shouldReturnJsonFormat() {
        final var expectedJson = "{\"name\":\"any\"}";
        final var json = Json.writeValueAsString(new DummyObject("any"));
        Assertions.assertEquals(expectedJson, json);
    }

    @Test
    public void givenARecordValue_whenConvertToJson_shouldReturnJsonFormat() {
        final var expectedJson = "{\"name\":\"any\"}";
        final var json = Json.writeValueAsString(new DummyRecord("any"));
        Assertions.assertEquals(expectedJson, json);
    }

    public  record DummyRecord(String name) {}

    public static class DummyObject  {
        private String name;

        public DummyObject(String name) {
            this.name = name;
        }


        public String getName() {
            return name;
        }
    }
}
