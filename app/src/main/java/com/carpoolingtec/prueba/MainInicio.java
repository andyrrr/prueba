package com.carpoolingtec.prueba;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.facebook.*;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class MainInicio extends AppCompatActivity {
    CallbackManager callbackManager;
    ImageView imgAvatar;
    Button btnSiguiente;
    String nombreCaptado, fechaCaptada, emailCaptado;
    Boolean registrado=false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String accesstoken = loginResult.getAccessToken().getToken();


                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("response", response.toString());

                        try {
                            nombreCaptado = object.getString("name");
                            fechaCaptada = object.getString("birthday");
                            emailCaptado = object.getString("email");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        enviarDatos();
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email,birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        if (AccessToken.getCurrentAccessToken() != null) {
            registrado=true;
        }

        btnSiguiente = (Button) findViewById(R.id.buttonSeguir);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registrado){
                    Intent seguir = new Intent(MainInicio.this, MainMapa.class);
                    startActivityForResult(seguir,0);
                }
            }
        });



    }

    public void enviarDatos() {
        Intent intent = new Intent(MainInicio.this, MainRegistro.class);
        intent.putExtra("name", nombreCaptado);
        intent.putExtra("birthday", fechaCaptada);
        intent.putExtra("email", emailCaptado);
        startActivityForResult(intent, 0);
    }


}
