package pe.area51.socialapp;

/**
 * Created by segundo on 26/09/17.
 */

public class SocialAppGlobals {

    public static String api_domain = "http://segundo.platcel.com/";
    public static String api_version = "v1/";

    public static String api_controller_user = "user/";
    public static String api_controller_feed = "feed/";

    public static String api_module_user_login = api_domain + api_version +
            api_controller_user + "login";

    public static String api_module_user_register = api_domain + api_version +
            api_controller_user + "register";


    //================================================================
    // Parameters
    //================================================================
    public static String api_par_email = "email";
    public static String api_par_password = "password";
    public static String api_par_name = "name";
    public static String api_par_lastname = "lastname";
    public static String api_par_users_id = "id";

    //================================================================
    // Response
    //================================================================
    public static String api_res_state = "state";
    public static String api_res_state_ok = "ok";
    public static String api_res_message = "message";

    public static String api_res_data = "data";
    public static String api_res_email = "email";
    public static String api_res_name = "name";
    public static String api_res_lastname = "lastname";
    public static String api_res_users_id = "id";


}
