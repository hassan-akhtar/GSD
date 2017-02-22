package com.uaeemployee.Network.Service;

import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.uaeemployee.Application.MyApplication;
import com.uaeemployee.Network.RequestDTOs.LoginDTO;
import com.uaeemployee.Network.ResponseDTOs.LoginResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.OrganizationsDTO;
import com.uaeemployee.Network.ResponseDTOs.OrganizationsResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.ResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.VacanciesDTO;
import com.uaeemployee.Network.ResponseDTOs.VacanciesResponseDTO;
import com.uaeemployee.Utils.SystemConstants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by hakhtar on 5/4/2016.
 */
public class GSDServiceImpl implements GSDService {


    private GSDRESTService adapter;
    //SharedPreferences sharedpreferences;
    //SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "My_Pref";
    //sharedPreferences.getString("AuthToken", "")

    GSDServiceImpl(Context context) {
        //sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(SystemConstants.READ_TIMEOUT_SECONDS,
                TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(SystemConstants.CONNECTION_TIMEOUT_SECONDS,
                TimeUnit.SECONDS);
        final RestAdapter retroAdapter = new RestAdapter.Builder()
                .setEndpoint(SystemConstants.URL_BASE)
                .setClient(new OkClient(okHttpClient)).build();
        adapter = retroAdapter.create(GSDRESTService.class);
    }


    @Override
    public void loginRequest(final LoginDTO loginFteDTO, final MyCallBack callback) {
        adapter.loginRequest(loginFteDTO.getUsername(), loginFteDTO.getPassword(), new Callback<LoginResponseDTO>() {
            @Override
            public void success(LoginResponseDTO loginResponseDTO, Response response) {
                loginResponseDTO.setCallBackId(loginFteDTO.getCallBackId());
/*                editor = sharedpreferences.edit();
                editor.putString("AuthToken", loginResponseDTO.getAuth_token());
                editor.commit();*/
                callback.onSuccess(loginResponseDTO);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error != null && error.getResponse() != null && error.getResponse().getStatus() != 0) {
                    MyApplication.getInstance().setStatusCode(error.getResponse().getStatus());
                    callback.onFailure(new ResponseDTO(error.getMessage()));
                } else {
                    MyApplication.getInstance().setStatusCode(1);
                    callback.onFailure(new ResponseDTO(error.getMessage()));
                }
            }
        });

    }

    @Override
    public void getOrganizations(final com.uaeemployee.Network.RequestDTOs.OrganizationsDTO organizationsDTO, final MyCallBack callback) {
        adapter.getOrganizations(organizationsDTO.getUserID(), new Callback<List<OrganizationsDTO>>() {
            @Override
            public void success(List<OrganizationsDTO> organizationsResponseDTO, Response response) {
                Log.e( "success: ", ""+organizationsResponseDTO );
                OrganizationsResponseDTO res = new OrganizationsResponseDTO();
                res.setOrganizationsDTO(organizationsResponseDTO);
                res.setCallBackId(organizationsDTO.getCallBackId());
               callback.onSuccess(res);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error != null && error.getResponse() != null && error.getResponse().getStatus() != 0) {
                    MyApplication.getInstance().setStatusCode(error.getResponse().getStatus());
                    callback.onFailure(new ResponseDTO(error.getMessage()));
                } else {
                    MyApplication.getInstance().setStatusCode(1);
                    callback.onFailure(new ResponseDTO(error.getMessage()));
                }
            }
        });
    }

    @Override
    public void getVacancies(final com.uaeemployee.Network.RequestDTOs.OrganizationsDTO organizationsDTO, final MyCallBack callback) {
        adapter.getVacancies(organizationsDTO.getUserID(), new Callback<List<VacanciesDTO>>() {
            @Override
            public void success(List<VacanciesDTO> vacanciesDTO, Response response) {
                Log.e( "success: ", ""+vacanciesDTO );
                VacanciesResponseDTO res = new VacanciesResponseDTO();
                res.setVacanciesDTO(vacanciesDTO);
                res.setCallBackId(organizationsDTO.getCallBackId());
                callback.onSuccess(res);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error != null && error.getResponse() != null && error.getResponse().getStatus() != 0) {
                    MyApplication.getInstance().setStatusCode(error.getResponse().getStatus());
                    callback.onFailure(new ResponseDTO(error.getMessage()));
                } else {
                    MyApplication.getInstance().setStatusCode(1);
                    callback.onFailure(new ResponseDTO(error.getMessage()));
                }
            }
        });
    }


}
