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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;

public class PersonalFragment extends Fragment {

    private PersonalViewModel mViewModel;
    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }


    private Unbinder unbinder;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Nullable
    @BindView(R.id.avatar_personal)
    ImageView imageView;

    @Nullable
    @BindView(R.id.nikeName_personal)
    TextView nikeName_personal;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root1 = inflater.inflate(R.layout.personal_fragment, container, false);
        unbinder = ButterKnife.bind(this,root1);
        sharedPreferences = getActivity().getSharedPreferences("data",0);
        editor  =getActivity().getSharedPreferences("data",0).edit();

        return root1;
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

    @Optional
    @OnClick({R.id.feedback_personal,R.id.order_personal,R.id.info_personal,R.id.change_info,R.id.logout_personal})
    public void test(View view){
        switch (view.getId()){
            case R.id.feedback_personal:
                Intent intent = new Intent(getContext(), FeedbackActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.order_personal:
                Intent intent1 = new Intent(getContext(), OrderActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.info_personal:
                Intent intent2 = new Intent(getContext(), PersonalInfoActivity.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.change_personal:
                Intent intent3 = new Intent(getContext(), ChangePasswordActivity.class);
                getActivity().startActivity(intent3);
                break;
            case R.id.logout_personal:
                editor.clear().commit();
                Intent intent4 = new Intent(getContext(), LoginActivity.class);
                getActivity().startActivity(intent4);
                break;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PersonalViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}