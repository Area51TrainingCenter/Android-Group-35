package pe.area51.clase03.models;

import java.io.Serializable;

/**
 * Created by segundo on 22/08/17.
 */

public class UserModel implements Serializable {

    private String name;
    private String description;
    private String photo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
