package com.example.android.newstask.db;

import com.example.android.newstask.models.Source;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public String fromSourceToGson(Source source){
        return new Gson().toJson(source);
    }
    @TypeConverter
    public Source fromGsontoSource(String source){
        return new Gson().fromJson(source,Source.class);
    }
}
