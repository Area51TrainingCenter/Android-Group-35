package pe.area51.socialapp.screens.feed.viewmodel;

import android.content.Context;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.area51.socialapp.SocialAppApplication;
import pe.area51.socialapp.SocialAppGlobals;
import pe.area51.socialapp.databinding.ActivityFeedBinding;
import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.helpers.session.SocialAppSession;
import pe.area51.socialapp.models.FeedModel;
import pe.area51.socialapp.screens.feed.view.FeedAdapter;

/**
 * Created by segundo on 14/09/17.
 */

public class FeedViewModel {

    //Para manejar la sesion
    SocialAppSession session;

    Context context;
    ActivityFeedBinding binding;

    FeedAdapter adapter;
    ArrayList<FeedModel> feed;

    public FeedViewModel(Context context, ActivityFeedBinding binding) {
        this.context = context;
        this.binding = binding;

        session = new SocialAppSession(context);

    }

    public void initView() {

        feed = new ArrayList<FeedModel>();

        /*
        for (int i = 0; i < 100; i++) {
            FeedModel model = new FeedModel();
            feed.add(model);
        }
        */

        adapter = new FeedAdapter(context, feed);
        binding.list.setAdapter(adapter);

        getFeed();

    }

    public void getFeed() {

        //Mostramos el cargador
        binding.loaders.setVisibility(View.VISIBLE);

        String url = SocialAppGlobals.api_module_feed;

        JSONObject parameters = new JSONObject();

        try {

            parameters.put(SocialAppGlobals.api_par_users_id, session.getId());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        SocialAppLog.getMessage("url: " + url);
        SocialAppLog.getMessage("parameters: " + parameters);

        JsonObjectRequest jor = new JsonObjectRequest(
                Request.Method.POST,
                url,
                parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        SocialAppLog.getMessage("response: " + response);

                        try {

                            if (response.has(SocialAppGlobals.api_res_state)) {
                                //Usuario valido

                                if (response.getString(SocialAppGlobals.api_res_state)
                                        .equals(SocialAppGlobals.api_res_state_ok)) {

                                    if (response.has(SocialAppGlobals.api_res_data)) {
                                        JSONArray data = response.getJSONArray(SocialAppGlobals.api_res_data);

                                        int total = data.length();
                                        JSONObject item = null;
                                        for (int i = 0; i < total; i++) {
                                            item = data.getJSONObject(i);

                                            FeedModel fm = new FeedModel();

                                            if (item.has(SocialAppGlobals.api_res_title)) {
                                                fm.setTitle(item.getString(SocialAppGlobals.api_res_title));
                                            }

                                            if (item.has(SocialAppGlobals.api_res_images)) {
                                                fm.setPhoto(item.getString(SocialAppGlobals.api_res_images));
                                            }

                                            if (item.has(SocialAppGlobals.api_res_comments)) {
                                                fm.setComments(item.getInt(SocialAppGlobals.api_res_comments));
                                            }

                                            if (item.has(SocialAppGlobals.api_res_favorites)) {
                                                fm.setFavourites(item.getInt(SocialAppGlobals.api_res_favorites));
                                            }

                                            feed.add(fm);
                                            adapter.notifyDataSetChanged();
                                            binding.loaders.setVisibility(View.GONE);

                                        }
                                    }

                                }


                            } else {
                                //Error al traer la información
                                //Mensaje toast
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        SocialAppApplication.getInstance().addToRequestQueue(jor);


    }


}