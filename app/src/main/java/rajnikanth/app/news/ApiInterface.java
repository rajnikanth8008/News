package rajnikanth.app.news;

import rajnikanth.app.news.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("top-headlines")
    Call<NewsResponse> getHeadlines(@Query("sources")String sources, @Query("apiKey")String apiKey);
}
