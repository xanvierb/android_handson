package nl.quintor.myhandsonapp.model;

/**
 * Created by xbrouwer on 27-11-17.
 */

public class NewsItem {
    private String imageUrl;
    private String newsTitle;
    private String newsDescription;
    private String newsAuthor;
    private String newsAge;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsAge() {
        return newsAge;
    }

    public void setNewsAge(String newsAge) {
        this.newsAge = newsAge;
    }
}
