package com.example.smartcity_test3.ui.newsItem;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.home.HomeViewModel;
import com.example.smartcity_test3.ui.newsItem.adapter.NewsItemLabelAdapter;
import com.example.smartcity_test3.ui.newsItem.adapter.NewsItemListAdapter;
import com.example.smartcity_test3.ui.newsItem.adapter.ViewPagerAdapter;
import com.example.smartcity_test3.ui.newsItem.pojo.NewItem;
import com.example.smartcity_test3.ui.newsItem.pojo.NewsItemCategory;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NewsItemFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View root;
    private List<NewsItemCategory> newsItemCategoryList = new ArrayList<>();

    private RecyclerView label_recyclerView ;
    private LinearLayoutManager linearLayoutManager;
    private ListView newsListView ;

    private ViewPager viewPager ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.frament_news_item, container, false);

        label_recyclerView = root.findViewById(R.id.news_item_category);
        linearLayoutManager = new LinearLayoutManager(root.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        newsListView = root.findViewById(R.id.news_item_list);

        viewPager = root.findViewById(R.id.news_item_img);

        return root;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtil.Get("http://124.93.196.45:10002/system/dict/data/type/press_category");
//                    Log.i("TAG", "run: "+json);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        int dictCode = jsonObject1.getInt("dictCode");
                        String dictLabel = jsonObject1.getString("dictLabel");
//                        Log.i("TAG", "run: "+dictLabel+dictCode);
                        String json1 = KenUtil.Get("http://124.93.196.45:10002/press/press/list?pressCategory="+dictCode);
                        JSONObject jsonObject2 = new JSONObject(json1);
                        JSONArray jsonArray1 = jsonObject2.getJSONArray("rows");
                        List<NewItem> newItems = new ArrayList<>();
                        for (int j = 0; j < jsonArray1.length(); j++) {
                            JSONObject jsonObject3 = jsonArray1.getJSONObject(j);
                            String updateTime = jsonObject3.getString("updateTime");
                            int id = jsonObject3.getInt("id");
                            String title = jsonObject3.getString("title");
                            String content = jsonObject3.getString("content");
                            String imgUrl = jsonObject3.getString("imgUrl");
                            String pressCategory = jsonObject3.getString("pressCategory");
                            int isRecommend = jsonObject3.getInt("isRecommend");
                            int likeNumber = jsonObject3.getInt("likeNumber");
                            int viewsNumber = jsonObject3.getInt("viewsNumber");

//                            Log.i("TAG", "run: "+id+title+imgUrl+pressCategory+isRecommend+likeNumber+viewsNumber);
                            newItems.add(new NewItem(updateTime,id,title,content,"http://124.93.196.45:10002"+imgUrl,pressCategory,isRecommend,likeNumber,viewsNumber));

                        }
                        newsItemCategoryList.add(new NewsItemCategory(dictCode,dictLabel,newItems));
                    }
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

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

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    NewsItemLabelAdapter newsItemLabelAdapter = new NewsItemLabelAdapter(newsItemCategoryList,handler);
                    label_recyclerView.setLayoutManager(linearLayoutManager);
                    label_recyclerView.setAdapter(newsItemLabelAdapter);
                    NewsItemListAdapter newsItemListAdapter = new NewsItemListAdapter(getContext(),R.layout.news_item_list_detail_layout,newsItemCategoryList.get(0).getNewItems());
                    newsListView.setAdapter(newsItemListAdapter);
                    viewPager.setAdapter(new ViewPagerAdapter(getContext(),newsItemCategoryList.get(0).getNewItems()));

                    break;
                case 2:
                    int position = (int)msg.obj;
                    NewsItemListAdapter newsItemListAdapter1 = new NewsItemListAdapter(getContext(),R.layout.news_item_list_detail_layout,newsItemCategoryList.get(position).getNewItems());
                    newsListView.setAdapter(newsItemListAdapter1);
                case 3:
                    int currentItem = viewPager.getCurrentItem();
                    viewPager.setCurrentItem(currentItem+1);
                default:
                    break;
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        if(newsItemCategoryList.size()!=0){
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }
}
