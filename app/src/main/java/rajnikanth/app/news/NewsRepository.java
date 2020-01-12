package rajnikanth.app.news;

import androidx.lifecycle.MutableLiveData;

import rajnikanth.app.news.model.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static NewsRepository newsRepository;

    public static NewsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private ApiInterface newsApi;

    public NewsRepository() {
        newsApi = ApiClient.cteateService(ApiInterface.class);
    }

    public MutableLiveData<NewsResponse> getNews(String source, String key) {
        final MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        newsApi.getHeadlines(source, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        }); return newsData;
    }
}
