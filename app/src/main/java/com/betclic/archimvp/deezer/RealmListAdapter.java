package com.betclic.archimvp.deezer;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import io.realm.RealmList;
import io.realm.RealmModel;

/**
 * Created by Mathieu Bertin on 15/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class RealmListAdapter<T extends RealmModel> extends JsonAdapter<RealmList<T>> {


    public static class Factory implements JsonAdapter.Factory {

        @Override
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
            Class<?> rawType = Types.getRawType(type);
            if(rawType == RealmList.class) {
                Type elementType = Types.collectionElementType(type, RealmList.class);
                JsonAdapter eltAdapter = moshi.adapter((Class<? extends RealmModel>)elementType);

                return new RealmListAdapter(eltAdapter).nullSafe();
            }

            return null;
        }
    }

    JsonAdapter<T> elementAdapter;

    private RealmListAdapter(JsonAdapter<T> elementAdapter) {
        this.elementAdapter = elementAdapter;
    }

    @Override
    public RealmList<T> fromJson(JsonReader reader) throws IOException {
        RealmList<T> result = new RealmList<T>();
        reader.beginArray();
        while (reader.hasNext()) {
            result.add(elementAdapter.fromJson(reader));
        }
        reader.endArray();
        return result;
    }

    @Override
    public void toJson(JsonWriter writer, RealmList<T> value) throws IOException {
        writer.beginArray();

        for (T element: value) {
            elementAdapter.toJson(writer, element);
        }

        writer.endArray();
    }
}
