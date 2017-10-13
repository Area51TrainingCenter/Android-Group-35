package pe.area51.socialapp.models.app;

import io.realm.RealmObject;

/**
 * Created by segundo on 14/09/17.
 */

public class FeedModel extends RealmObject {

    private String id;
    private String photo;
    private String title;
    private int comments;
    private int favourites;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getFavourites() {
        return favourites;
    }

    public void setFavourites(int favourites) {
        this.favourites = favourites;
    }
}
