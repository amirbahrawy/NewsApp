package com.example.android.newstask.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.newstask.R;
import com.example.android.newstask.adapters.NewsAdapter;
import com.example.android.newstask.api.ApiClient;
import com.example.android.newstask.models.Article;
import com.example.android.newstask.models.News;
import com.example.android.newstask.repository.NewsRepository;
import com.example.android.newstask.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<Article> articles;
    private NewsRepository newsRepository;
    private NewsViewModel newsViewModel;
    private String country;
    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        country=getCountry();
        key=Constants.API_KEY;
        articles=new ArrayList<>();
        newsRepository=new NewsRepository(getApplication());
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new NewsAdapter(articles,this);
        newsViewModel= ViewModelProviders.of(this).get(NewsViewModel.class);
        networkRequest();
        newsViewModel.getAllArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> list) {
                articles=list;
                recyclerView.setAdapter(adapter);
                adapter.setArticles(list);

            }
        });
    }

    @Override
        public void onItemClick(int position) {
        // intent
        Intent intent=new Intent(this,NewsDetailsActivity.class);
        intent.putExtra("url",articles.get(position).getUrl());
        startActivity(intent);

                                }
        private void networkRequest() {
        Call<News> call= ApiClient.getInstance().getApi().getNews(country, key);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful())
                {
                newsRepository.insert(response.body().getArticle());
                }


            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(MainActivity.this, "YOU ARE OFFLINE...", Toast.LENGTH_SHORT).show();
            }
        });
        }

        private String getCountry(){
        Locale locale=Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();
    }
}