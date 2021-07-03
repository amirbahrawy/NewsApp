package com.example.android.newstask.repository;

import android.app.Application;
import android.os.AsyncTask;
import com.example.android.newstask.db.ArticleDao;
import com.example.android.newstask.db.ArticleDatabase;
import com.example.android.newstask.models.Article;
import java.util.List;
import java.util.Locale;
import androidx.lifecycle.LiveData;


public class NewsRepository {
    ArticleDatabase database;
    ArticleDao dao;
    LiveData<List<Article>> allArticles;

    public NewsRepository(Application application) {
        database=ArticleDatabase.getInstance(application);
        dao=database.getArticleDao();
        allArticles=dao.getAllArticles();
    }


    public void insert(List<Article> articles){
        new InsertArticleAsyncTask(dao).execute(articles);
    }


    public LiveData<List<Article>> getAllArticles(){
        return allArticles;
    }

    private static class InsertArticleAsyncTask extends AsyncTask<List<Article>,Void,Void>{
        private ArticleDao articleDao;

        public InsertArticleAsyncTask(ArticleDao articleDao) {
            this.articleDao = articleDao;
        }

        @Override
        protected Void doInBackground(List<Article>... lists) {
            articleDao.insertArticle(lists[0]);
            return null;
        }
    }
}
