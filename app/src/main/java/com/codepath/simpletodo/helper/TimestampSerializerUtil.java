package com.codepath.simpletodo.helper;

import com.activeandroid.serializer.TypeSerializer;

import java.sql.Timestamp;

/**
 * Created by carise on 8/13/16.
 */
public class TimestampSerializerUtil extends TypeSerializer {
    @Override
    public Class<?> getDeserializedType() {
        return Timestamp.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return Long.class;
    }

    @Override
    public Long serialize(Object data) {
        if (data == null) {
            return null;
        }
        return ((Timestamp) data).getTime();
    }

    @Override
    public Timestamp deserialize(Object data) {
        if (data == null) {
            return null;
        }
        return new java.sql.Timestamp((Long) data);
    }
}