package com.example.android.newstask.ui;

import android.app.Application;
import com.example.android.newstask.models.Article;
import com.example.android.newstask.repository.NewsRepository;
import java.util.List;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;
    private LiveData<List<Article>> articles;

    public NewsViewModel(Application application) {
        super(application);
        newsRepository=new NewsRepository(application);
        articles=newsRepository.getAllArticles();
    }
    public void insert(List<Article> list)
    {
        newsRepository.insert(list);
    }

    public LiveData<List<Article>> getAllArticles()
    {
        return articles;
    }
}
