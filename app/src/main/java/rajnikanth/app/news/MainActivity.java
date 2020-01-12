package rajnikanth.app.news;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rajnikanth.app.news.model.Articles;
import rajnikanth.app.news.model.NewsResponse;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ArrayList<Articles> articleArrayList = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.init();
        newsViewModel.getNewsRepository().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                List<Articles> newsArticles = newsResponse.getArticles();
                articleArrayList.addAll(newsArticles);
                recyclerAdapter.notifyDataSetChanged();
            }
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (recyclerAdapter == null) {
            recyclerAdapter = new RecyclerAdapter(MainActivity.this, articleArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            recyclerAdapter.notifyDataSetChanged();
        }
    }
}
