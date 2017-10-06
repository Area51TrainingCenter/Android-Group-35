package pe.area51.socialapp.screens.feed.viewmodel;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import pe.area51.socialapp.SocialAppApplication;
import pe.area51.socialapp.SocialAppGlobals;
import pe.area51.socialapp.databinding.ActivityFeedBinding;
import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.helpers.network.SocialAppNetwork;
import pe.area51.socialapp.helpers.session.SocialAppSession;
import pe.area51.socialapp.models.app.FeedModel;
import pe.area51.socialapp.screens.feed.view.FeedAdapter;

/**
 * Created by segundo on 14/09/17.
 */

public class FeedViewModel implements SwipeRefreshLayout.OnRefreshListener {

    //Para manejar la sesion
    SocialAppSession session;

    Context context;
    ActivityFeedBinding binding;

    FeedAdapter adapter;
    ArrayList<FeedModel> feed;

    Realm realm;
    SocialAppNetwork network;

    public FeedViewModel(Context context, ActivityFeedBinding binding,
                         Realm realm) {

        this.context = context;
        this.binding = binding;
        this.realm = realm;

        network = new SocialAppNetwork(context);
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

        if (network.getNetwork()) {
            SocialAppLog.getMessage("Hay conexión de Red");
            getFeed();
        } else {
            SocialAppLog.getMessage("No Hay conexión de Red");
            getFeedOffline();
        }

        binding.refresh.setOnRefreshListener(this);


    }

    public void getFeedOffline() {

        //Mostramos el cargador
        binding.loaders.setVisibility(View.VISIBLE);
        binding.network.setVisibility(View.VISIBLE);

        RealmResults<FeedModel> results = realm.where(FeedModel.class)
                //.equalTo("comments", 58)
                .findAll();

        SocialAppLog.getMessage("results: " + results);

        for (FeedModel item : realm.where(FeedModel.class)
                .findAll()) {
            SocialAppLog.getMessage("results: " + item.getTitle());
            SocialAppLog.getMessage("results: " + item.getComments());

            FeedModel fm = new FeedModel();
            fm.setTitle(item.getTitle());
            fm.setPhoto(item.getPhoto());
            fm.setComments(item.getComments());
            fm.setFavourites(item.getFavourites());
            feed.add(fm);
        }

        adapter.notifyDataSetChanged();
        binding.loaders.setVisibility(View.GONE);
        binding.refresh.setRefreshing(false);


    }


    public void getFeed() {

        //Mostramos el cargador
        binding.loaders.setVisibility(View.VISIBLE);

        //Borramos los registros de la caché local
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.delete(FeedModel.class);

            }
        });


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

                                            //Guardar en Realm
                                            if (!realm.isClosed()) {

                                                realm.beginTransaction();

                                                FeedModel fmReal = realm.createObject(FeedModel.class);
                                                fmReal.setTitle(fm.getTitle());
                                                fmReal.setPhoto(fm.getPhoto());
                                                fmReal.setComments(fm.getComments());
                                                fmReal.setFavourites(fm.getFavourites());

                                                realm.commitTransaction();

                                            }


                                        }//fin del for

                                        adapter.notifyDataSetChanged();
                                        binding.loaders.setVisibility(View.GONE);
                                    }

                                }


                            } else {
                                //Error al traer la información
                                //Mensaje toast
                            }

                            binding.refresh.setRefreshing(false);

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


    @Override
    public void onRefresh() {

        if (network.getNetwork()) {
            getFeed();
        } else {
            getFeedOffline();
        }

    }
}
