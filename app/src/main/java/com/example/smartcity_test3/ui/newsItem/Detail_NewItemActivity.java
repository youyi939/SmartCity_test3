package com.example.smartcity_test3.ui.newsItem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.newsItem.adapter.CommentAdapter;
import com.example.smartcity_test3.ui.newsItem.adapter.RecommentAdapter;
import com.example.smartcity_test3.ui.newsItem.pojo.Comments;
import com.example.smartcity_test3.ui.newsItem.pojo.NewItem;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Detail_NewItemActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private List<Comments> comments = new ArrayList<>();
    private ListView commentListView;
    private List<NewItem> newItems = new ArrayList<>();
    private List<NewItem> small_newItems = new ArrayList<>();
    private RecyclerView recommend ;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__new_item);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        commentListView = findViewById(R.id.detail_news_item_comment);
        recommend = findViewById(R.id.detail_news_item_Recommend);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        Intent intent = getIntent();
        NewItem newItem = (NewItem) intent.getSerializableExtra("news_item");
//        Log.i("TAG", "onCreate: "+newItem);

        actionBar.setTitle(newItem.getTitle());
        ImageView imageView = findViewById(R.id.detail_news_item_img);
        Glide.with(Detail_NewItemActivity.this).load(newItem.getImgUrl()).into(imageView);

        TextView content = findViewById(R.id.detail_news_item_content);
        content.setText(newItem.getContent());

        int id = newItem.getId();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtil.Get("http://124.93.196.45:10002/press/comments/list?pageNum=1&pageSize=10&pressId="+id);
//                    Log.i("TAG", "run: "+json);
//                    Log.i("TAG123", "run: "+json);
                    JSONObject jsonObject = new JSONObject(json);
                    String total = jsonObject.getString("total");
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    List<String> strings = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String content1 = jsonObject1.getString("content");
                        strings.add(content1);
//                        Log.i("TAG", "run: "+content1);
                    }
                    comments.add(new Comments(total,strings));
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String json = KenUtil.Get("http://124.93.196.45:10002/press/press/list?isRecommend=1");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String updateTime = jsonObject1.getString("updateTime");
                        int id = jsonObject1.getInt("id");
                        String title = jsonObject1.getString("title");
                        String content = jsonObject1.getString("content");
                        String imgUrl = jsonObject1.getString("imgUrl");
                        String pressCategory = jsonObject1.getString("pressCategory");
                        int isRecommend = jsonObject1.getInt("isRecommend");
                        int likeNumber = jsonObject1.getInt("likeNumber");
                        int viewsNumber = jsonObject1.getInt("viewsNumber");
                        newItems.add(new NewItem(updateTime,id,title,content,"http://124.93.196.45:10002"+imgUrl,pressCategory,isRecommend,likeNumber,viewsNumber));
                    }
                    for (int i = 0; i < 3; i++) {
                        small_newItems.add(newItems.get(i));
                    }
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);

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
                    CommentAdapter commentAdapter = new CommentAdapter(Detail_NewItemActivity.this,R.layout.comment_item_layout,comments.get(0).getStrings());
                    commentListView.setAdapter(commentAdapter);
                    TextView total = findViewById(R.id.detail_news_item_comment_total);
                    total.setText("评论数："+comments.get(0).getTotal());

                    break;
                case 2:

                    RecommentAdapter recommentAdapter = new RecommentAdapter(small_newItems);
                    recommend.setLayoutManager(staggeredGridLayoutManager);
                    recommend.setAdapter(recommentAdapter);

                    break;
                default:
                    break;
            }
        }
    };
}