package com.example.smartcity_test3.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.home.adapter.LabelAdapter;
import com.example.smartcity_test3.ui.home.adapter.MyAdapter;
import com.example.smartcity_test3.ui.home.adapter.ServiceAdapter;
import com.example.smartcity_test3.ui.home.pojo.Img;
import com.example.smartcity_test3.ui.home.pojo.Item;
import com.example.smartcity_test3.ui.home.pojo.ItemData;
import com.example.smartcity_test3.ui.home.pojo.Item_Service;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    //xinwen
    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerLabel_home;
    private ListView listViewXinwen_home;

    //lunbo
    private ViewPager viewPager;
    private List<Img> imgList = new ArrayList<>();
    private MyAdapter myAdapter;

    //service
    private RecyclerView recyclerService_home;
    private List<Item_Service> serviceList = new ArrayList<>();
    private ServiceAdapter serviceAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerService_home = root.findViewById(R.id.recyclerService_home);
        viewPager = root.findViewById(R.id.viewPager_home);
        recyclerLabel_home = root.findViewById(R.id.recyclerLabel_home);
        listViewXinwen_home = root.findViewById(R.id.listViewXinwen_home);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLunbo();
        getService();
        getXinwen();
    }

    @Override
    public void onStart() {
        super.onStart();
        handler.sendEmptyMessage(1);
        handler.sendEmptyMessage(2);
        handler.sendEmptyMessage(4);
    }

    /**
     *1:service ok
     * 2:lunbo ok
     * 3:lunbo +1
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
                    serviceAdapter = new ServiceAdapter(serviceList);
                    recyclerService_home.setLayoutManager(manager);
                    recyclerService_home.setAdapter(serviceAdapter);
                    break;
                case 2:
                    myAdapter =new MyAdapter(imgList,getContext());
                    viewPager.setAdapter(myAdapter);
                    break;
                case 3:
                    int position = viewPager.getCurrentItem();
                    viewPager.setCurrentItem(position+1);
                    break;
                case 4:
                    LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
                    manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                    LabelAdapter labelAdapter = new LabelAdapter(itemList,handler);
                    recyclerLabel_home.setLayoutManager(manager1);
                    recyclerLabel_home.setAdapter(labelAdapter);
                    break;
                case 5:
                    int i = (int)msg.obj;
                    Log.i("Ken", "handleMessage: "+i);
                    break;
            }
        }
    };


    public void getService() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtil.Get("http://124.93.196.45:10002/service/service/list");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String imgUrl = "http://124.93.196.45:10002" + object.getString("imgUrl");
                        int id = object.getInt("id");
                        String serviceName = object.getString("serviceName");
                        serviceList.add(new Item_Service(id, serviceName, imgUrl));
                    }
                    serviceList.add(new Item_Service(0,"更多服务","123"));
                    serviceList.sort(new Comparator<Item_Service>() {
                        @Override
                        public int compare(Item_Service item_service, Item_Service t1) {
                            int a = item_service.getId();
                            int b = t1.getId();
                            return b-a;
                        }
                    });
                    handler.sendEmptyMessage(1);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getLunbo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtil.Get("http://124.93.196.45:10002/userinfo/rotation/list?pageNum=1&pageSize=10&type=45");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String imgUrl = "http://124.93.196.45:10002"+object.getString("imgUrl");
                        int sort = object.getInt("sort");
                        imgList.add(new Img(sort,imgUrl));
                    }
                    handler.sendEmptyMessage(2);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(3);
                        }
                    },0,2000);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getXinwen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtil.Get("http://124.93.196.45:10002/system/dict/data/type/press_category");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String dictLabel = object.getString("dictLabel");
                        int dictCode = object.getInt("dictCode");
                        List<ItemData> dataList = new ArrayList<>();
                        String json1 = KenUtil.Get("http://124.93.196.45:10002/press/press/list?pageNum=1&pageSize=10&pressCategory="+dictCode);
                        JSONObject jsonObject1 = new JSONObject(json1);
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("rows");
                        for (int j = 0; j < jsonArray1.length() ; j++) {
                            JSONObject object1 = jsonArray1.getJSONObject(j);
                            String createTime = object1.getString("createTime");
                            String title = object1.getString("title");
                            String content = object1.getString("content");
                            String imgUrl =  "http://124.93.196.45:10002"+object1.getString("imgUrl");
                            int viewsNumber = object1.getInt("viewsNumber");
                            dataList.add(new ItemData(createTime,imgUrl,title,content,viewsNumber));
                        }
                        itemList.add(new Item(dictLabel,dictCode,dataList));
                    }
                    handler.sendEmptyMessage(4);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }








}