package com.example.android.newstask.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newstask.R;
import com.example.android.newstask.models.Article;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> articles;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter(List<Article> articles,OnItemClickListener onItemClickListener) {
        this.articles = articles;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new NewsViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position)   {
        Article article=articles.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvSource.setText(article.getSource().getName());
        String d=article.getPublishedAt();
        String d1=d.substring(0,10);
        String d2=d.substring(11,d.length()-1);
        holder.tvDate.setText(d1+" "+d2);
        holder.tvDescription.setText(article.getDescription());
        String imgUrl=article.getUrlToimage();
        Picasso.get().load(imgUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticles(List<Article> articles){
        this.articles=articles;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitle,tvSource,tvDate,tvDescription;
        ImageView imageView;
        OnItemClickListener onItemClickListener;
        public NewsViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvSource=itemView.findViewById(R.id.tvSource);
            tvDate=itemView.findViewById(R.id.tvPublishedAt);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            imageView=itemView.findViewById(R.id.ivArticleImage);
            itemView.setOnClickListener(this);
            this.onItemClickListener=onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
