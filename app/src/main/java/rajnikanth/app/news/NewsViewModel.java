package rajnikanth.app.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import rajnikanth.app.news.model.NewsResponse;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<NewsResponse> mutableLiveData;
    private NewsRepository newsRepository;
    public void init(){
        if (mutableLiveData != null){
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getNews("techcrunch","349fb315242b419fa8038f07801a00b0");
    }
    public LiveData<NewsResponse> getNewsRepository() {
        return mutableLiveData;
    }
}
