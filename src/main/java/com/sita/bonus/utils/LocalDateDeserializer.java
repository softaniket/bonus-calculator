package com.sita.bonus.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        SimpleDateFormat from = new SimpleDateFormat("MMM-dd-yyyy");
        SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        String dateString = p.getValueAsString();

        try {
            date = from.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Date to LocalDate conversion
        return LocalDate.parse(to.format(date));


    }
}