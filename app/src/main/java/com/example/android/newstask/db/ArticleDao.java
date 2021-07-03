package com.example.android.newstask.db;

import com.example.android.newstask.models.Article;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticle(List<Article> articles);

    @Query("SELECT * FROM articles")
    LiveData<List<Article>> getAllArticles();

    @Query("DELETE FROM articles")
        void deleteAll();
}
