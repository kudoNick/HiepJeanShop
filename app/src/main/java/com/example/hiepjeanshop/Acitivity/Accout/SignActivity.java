package com.example.hiepjeanshop.Acitivity.Accout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hiepjeanshop.Adapter.CitySpinnerAdapter;
import com.example.hiepjeanshop.Adapter.DistrictSpinnerAdapter;
import com.example.hiepjeanshop.Adapter.WardSpinnerAdapter;
import com.example.hiepjeanshop.Moder.DataCity;
import com.example.hiepjeanshop.Moder.DataDistrict;
import com.example.hiepjeanshop.Moder.DataWard;
import com.example.hiepjeanshop.R;
import com.example.hiepjeanshop.api.APIUrls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignActivity extends AppCompatActivity {

    private WardSpinnerAdapter wardSpinnerAdapter;
    private CitySpinnerAdapter spinnerAdapter;
    private DistrictSpinnerAdapter districtSpinnerAdapter;

    private AppCompatSpinner spiCity,spiDistrict,spiWard;
    public EditText edtFullName, edtUserName,edtPass,edtPassAgain,edtNumber, edtAddress;
    public Button btnSign;
    private LinearLayout lineLoading;

    private DataCity dataCity;
    private DataDistrict dataDistrict;
    private DataWard dataWard;

    private List<DataWard> dataWardList;
    private List<DataCity> cityList = new ArrayList<>();
    private List<DataDistrict> districtList;
    private String idAddress;

    private  String city,district,ward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        thamChieu();

        spinnerCity();
        chooseSpinnerCity();
        chooseSpinnerWard();




    }
    private void thamChieu(){
        edtFullName = findViewById(R.id.edtFullName);
        edtUserName = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPass);
        edtPassAgain = findViewById(R.id.edtPassAgain);
        edtNumber = findViewById(R.id.edtNumber);
        edtAddress = findViewById(R.id.edtaddress);
        btnSign = findViewById(R.id.btnSign);
        spiCity = findViewById(R.id.spiCity);
        spiDistrict = findViewById(R.id.spiDistrict);
        spiWard = findViewById(R.id.spiWard);
        lineLoading = findViewById(R.id.lineLoading);
    }

    @SuppressLint("ResourceAsColor")
    public void signUp(View view) {


        String fullName = edtFullName.getText().toString().trim();
        String userName = edtUserName.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        String passAgain = edtPassAgain.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String number = edtNumber.getText().toString().trim();
        String diaChi = address + ", " + ward + ", " + district + ", " + city;


        if(edtFullName.getText().toString().isEmpty() || edtUserName.getText().toString().isEmpty() || edtPass.getText().toString().isEmpty() ||edtPassAgain.getText().toString().isEmpty() ||edtAddress.getText().toString().isEmpty() ||edtNumber.getText().toString().isEmpty()){
            Toast.makeText(this, "Vui lòng không nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else if (!pass.equals(passAgain)) {
            edtPassAgain.setTextColor(R.color.red);
            Toast.makeText(this, "Mật khẩu nhập lại sai!", Toast.LENGTH_SHORT).show();
        }else {
            //loading
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.loading);
            dialog.show();


            AndroidNetworking.post(APIUrls.URL_GET_SIGNUP)
                    .addBodyParameter("userName",userName)
                    .addBodyParameter("role", "1")
                    .addBodyParameter("fullName", fullName)
                    .addBodyParameter("password", pass)
                    .addBodyParameter("address", diaChi)
                    .addBodyParameter("phoneNumber",number)
                    .addBodyParameter("cart","null")
                    .addBodyParameter("avatar","null")
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            dialog.dismiss();
                            try {
                                int status = Integer.parseInt(response.getString("status"));
                                if (status == 400){
                                    Toast.makeText(SignActivity.this, "tên đăng nhập đã có người sự dụng", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(SignActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(SignActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(SignActivity.this, anError.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void spinnerCity(){
        AndroidNetworking.get(APIUrls.URL_GET_CITY)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray city = response.getJSONArray("LtsItem");
                            for (int i = 0; i < city.length(); i++) {
                                dataCity = new DataCity(city.getJSONObject(i));
                                cityList.add(dataCity);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        spinnerAdapter = new CitySpinnerAdapter(cityList,SignActivity.this);
                        spiCity.setAdapter(spinnerAdapter);
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(SignActivity.this, anError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void chooseSpinnerCity(){
        spiCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dataCity = cityList.get(i);
                idAddress = dataCity.getId();
                city = dataCity.getTitle();
                //choose Spinner District
                districtList = new ArrayList<>();
                AndroidNetworking.get(APIUrls.URL_GET_CITY + idAddress +"/district")
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int j = 0; j < response.length(); j++) {
                                    try {
                                        dataDistrict = new DataDistrict(response.getJSONObject(j));
                                        districtList.add(dataDistrict);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                districtSpinnerAdapter = new DistrictSpinnerAdapter(districtList,SignActivity.this);
                                spiDistrict.setAdapter(districtSpinnerAdapter);
                            }
                            @Override
                            public void onError(ANError anError) {

                            }
                        });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(SignActivity.this, "ko chon gi", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void chooseSpinnerWard(){
        spiDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataDistrict = districtList.get(position);
                idAddress = dataDistrict.getId();
                district = dataDistrict.getTitle();
                //choose ward
                dataWardList = new ArrayList<>();
                AndroidNetworking.get("https://thongtindoanhnghiep.co/api/district/" + idAddress + "/ward")
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        dataWard = new DataWard(response.getJSONObject(i));
                                        dataWardList.add(dataWard);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                                wardSpinnerAdapter = new WardSpinnerAdapter(dataWardList,SignActivity.this);
                                spiWard.setAdapter(wardSpinnerAdapter);
                                lineLoading.setVisibility(View.GONE);
                            }
                            @Override
                            public void onError(ANError anError) {

                            }
                        });

                spiWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        dataWard = dataWardList.get(position);
                        ward = dataWard.getTitle();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}