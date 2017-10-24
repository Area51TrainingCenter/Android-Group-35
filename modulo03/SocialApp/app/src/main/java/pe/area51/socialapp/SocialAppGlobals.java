package pe.area51.socialapp;

/**
 * Created by segundo on 26/09/17.
 */

public class SocialAppGlobals {

    public static String api_domain = "http://segundo.platcel.com/";


    public static String api_version = "v1/";

    public static String api_controller_user = "user/";
    public static String api_controller_feed = "feed";

    public static String api_module_user_login = api_domain + api_version +
            api_controller_user + "login";

    public static String api_module_user_register = api_domain + api_version +
            api_controller_user + "register";

    public static String api_module_user_facebook = api_domain + api_version +
            api_controller_user + "facebook";

    public static String api_module_feed = api_domain + api_version +
            api_controller_feed;


    //================================================================
    // Parameters
    //================================================================
    public static String api_par_email = "email";
    public static String api_par_password = "password";
    public static String api_par_name = "name";
    public static String api_par_lastname = "lastname";
    public static String api_par_users_id = "id";
    // Nuevos parametros
    public static String api_par_facebook_token = "facebook_token";
    public static String api_par_facebook_id = "facebook_id";
    public static String api_par_fcm_token = "fcm_token";

    //================================================================
    // Response
    //================================================================
    public static String api_res_state = "state";
    public static String api_res_state_ok = "true";
    public static String api_res_message = "message";

    public static String api_res_data = "data";
    public static String api_res_email = "email";
    public static String api_res_name = "name";
    public static String api_res_lastname = "lastname";
    public static String api_res_users_id = "id";


    public static String api_res_title = "title";
    public static String api_res_images = "images";
    public static String api_res_comments = "comments";
    public static String api_res_favorites = "favorites";


}
