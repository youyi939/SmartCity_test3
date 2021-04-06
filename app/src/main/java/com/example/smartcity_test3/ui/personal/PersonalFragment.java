package com.example.smartcity_test3.ui.personal;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.personal.activity.ChangePasswordActivity;
import com.example.smartcity_test3.ui.personal.activity.FeedbackActivity;
import com.example.smartcity_test3.ui.personal.activity.LoginActivity;
import com.example.smartcity_test3.ui.personal.activity.OrderActivity;
import com.example.smartcity_test3.ui.personal.activity.PersonalInfoActivity;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PersonalFragment extends Fragment {

    private PersonalViewModel mViewModel;
    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }


    private Button logout_personal;
    private Button feedback_personal;
    private Button order_personal;
    private Button change_personal;
    private Button info_personal;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private ImageView imageView;
    private TextView nikeName_personal;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.personal_fragment, container, false);
        logout_personal  = root.findViewById(R.id.logout_personal);
        feedback_personal = root.findViewById(R.id.feedback_personal);
        order_personal = root.findViewById(R.id.order_personal);
        change_personal = root.findViewById(R.id.change_personal);
        info_personal = root.findViewById(R.id.info_personal);
        sharedPreferences = getActivity().getSharedPreferences("data",0);
        editor  =getActivity().getSharedPreferences("data",0).edit();
        imageView = root.findViewById(R.id.avatar_personal);
        nikeName_personal  =root.findViewById(R.id.nikeName_personal);

        logout_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear().commit();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });

        change_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                getActivity().startActivity(intent);
            }
        });

        info_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PersonalInfoActivity.class);
                getActivity().startActivity(intent);
            }
        });

        order_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OrderActivity.class);
                getActivity().startActivity(intent);
            }
        });

        feedback_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FeedbackActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (sharedPreferences.getString("password","k").equals("k") || sharedPreferences.getString("username","k").equals("k")){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            getActivity().startActivity(intent);
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String username = sharedPreferences.getString("username","k");
                        String password = sharedPreferences.getString("password","k");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("username",username);
                        jsonObject.put("password",password);
                        String json = KenUtil.Post("http://124.93.196.45:10002/login","",jsonObject.toString());
                        JSONObject object = new JSONObject(json);
                        int code = object.getInt("code");
                        if (code ==200){
                            String token = object.getString("token");
                            editor.putString("token",token);
                            editor.commit();
                            getUserInfo(token);
                            handler.sendEmptyMessage(2);
                        }else {
                            handler.sendEmptyMessage(1);
                        }

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }


    //获取用户详细信息
    public void getUserInfo(String token){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://124.93.196.45:10002/getInfo";
                    String json = KenUtil.Get_T(url,token);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject object = jsonObject.getJSONObject("user");
                    String userId = object.getString("userId");
                    editor.putString("userId",userId);
                    editor.commit();
                    String nickName = object.getString("nickName");
                    String avatar = "http://124.93.196.45:10002"+object.getString("avatar");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nikeName_personal.setText(nickName);
                            Glide.with(getContext()).load(avatar).into(imageView);
                        }
                    });
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(getContext(),"登陆失败",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getContext(),"登陆成功",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PersonalViewModel.class);
        // TODO: Use the ViewModel
    }

}