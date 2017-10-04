package pe.area51.socialapp.models.app;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by segundo on 19/09/17.
 */

public class FollowerModel extends RealmObject{

    private String photo;
    private String name;
    private String rol;
    private int follow;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }
}
