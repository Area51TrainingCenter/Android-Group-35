package pe.area51.socialapp.screens.maps.view;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;


import pe.area51.socialapp.R;
import pe.area51.socialapp.SocialAppApplication;
import pe.area51.socialapp.SocialAppGlobals;
import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.helpers.network.SocialAppNetwork;
import pe.area51.socialapp.helpers.session.SocialAppSession;


public class SocialMapsActivity extends FragmentActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    GoogleMap map;
    GoogleApiClient client;
    LocationRequest request;

    SocialAppSession session;
    Context context;
    SocialAppNetwork network;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_maps);

        context = this;
        session = new SocialAppSession(context);
        network = new SocialAppNetwork(context);

        /*
        MapFragment mapFragment
                = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        */

        SupportMapFragment mapFragment
                = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //En esta version debemos pedir permisos al usuario
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                initClient();
                map.setMyLocationEnabled(true);

            } else {
                //Pedir permiso al usuario
                //Usar libreria easypermissions
            }

        } else {
            //En esta version no se piede permiso al usuario
            initClient();
            map.setMyLocationEnabled(true);
        }


    }

    protected synchronized void initClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }


    public void myLocation(Location location) {

        LatLng latLng =
                new LatLng(
                        location.getLatitude(),
                        location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions
                .position(latLng)
                .title("Actual ubicación")
                .icon(BitmapDescriptorFactory.defaultMarker(
                        //BitmapDescriptorFactory.HUE_MAGENTA
                ));

        map.clear(); //Limpiamos el mapa de los marcadores que hay
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory
                .newLatLngZoom(latLng, 17));

        //Verificamos la sesion iniciada
        // y la conexión de internet
        if (session.isLogin() && network.getNetwork()) {
            saveLocation(location);
        }

    }

    public void saveLocation(Location location) {

        SocialAppLog.getMessage("usando saveLocation");
        JSONObject parameters = new JSONObject();

        try {
            parameters.put(
                    SocialAppGlobals.api_par_user_id, session.getId());
            parameters.put(
                    SocialAppGlobals.api_par_latitud,
                    location.getLatitude());
            parameters.put(
                    SocialAppGlobals.api_par_longitud,
                    location.getLongitude());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = SocialAppGlobals.api_module_user_location;

        SocialAppLog.getMessage("url: " + url);
        SocialAppLog.getMessage("parameters: " + parameters);

        JsonObjectRequest jor = new JsonObjectRequest(
                Request.Method.POST, url, parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        SocialAppLog.getMessage("response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        SocialAppLog.getMessage("error: " + error.getMessage());
                    }
                }
        );

        //Ejecutamos el request de volley
        SocialAppApplication.getInstance().addToRequestQueue(jor);


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        request = new LocationRequest();
        request.setInterval(10000)
                .setFastestInterval(10000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            //Pide permiso al usuario
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                LocationServices.FusedLocationApi
                        .requestLocationUpdates(client, request
                                , this);
            } else {

                //Pedir permiso al usuario
                //Usar libreria easypermissions

            }
        } else {
            //No Pide permiso al usuario
            LocationServices.FusedLocationApi
                    .requestLocationUpdates(client, request
                            , this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        myLocation(location);

    }
}
