package com.junkers.musiclink.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.inject.Provider;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class GsonProvider implements Provider <Gson> {
    private static final String DEFAULT_FORMAT = "Y/M/d";

    @Override
    public Gson get() {
        return new GsonBuilder()
                .setFieldNamingStrategy(new HungarianFieldPolicy())
                .registerTypeAdapter(DateTime.class, new DateTimeSerializer())
                .registerTypeAdapter(DateTime.class, new DateTimeDeserializer())
                .create();
    }

    public static class HungarianFieldPolicy implements FieldNamingStrategy {
        @Override
        public String translateName(Field f) {
            String name = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(f);
            if (name.startsWith("m_")) {
                return name.substring(2);
            }
            return name;
        }
    }

    public static class DateTimeSerializer implements JsonSerializer<DateTime> {
        @Override
        public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString(DEFAULT_FORMAT));
        }
    }

    public static class DateTimeDeserializer implements JsonDeserializer<DateTime> {
        @Override
        public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return DateTime.parse(
                    json.getAsJsonPrimitive().getAsString(),
                    DateTimeFormat.forPattern(DEFAULT_FORMAT)
            );
        }
    }
}
