package com.example.android.newstask.db;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.newstask.models.Article;
import com.example.android.newstask.models.Source;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(
        entities = Article.class,
        version = 2
)

@TypeConverters(Converters.class)

public abstract class ArticleDatabase extends RoomDatabase {

    private static ArticleDatabase instance;
    public abstract ArticleDao getArticleDao();

    public static synchronized ArticleDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder
                    (context,ArticleDatabase.class,"articles")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
