package com.example.userdetail;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userdetail.apicall.APIService;
import com.example.userdetail.apicall.RetroClass;
import com.example.userdetail.commentdatabase.DBUser;
import com.example.userdetail.commentdatabase.DatabaseClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
   public UserListAdapter userListAdapter = new UserListAdapter();
   public ArrayList<User> userArrayList = new ArrayList<>();
   public ArrayList<User> mUserArrayList = new ArrayList<>();
    public MutableLiveData<User> viewDetail = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        userListAdapter.updateData(mUserArrayList);
        userListAdapter.setUserListInterface(new UserListAdapter.UserListInterface() {
            @Override
            public void addComment(int pos, String commen) {
                addUserComment(commen, mUserArrayList.get(pos).getUserId());
            }

            @Override
            public void viewUser(int pos) {
                viewDetail.setValue(mUserArrayList.get(pos));
            }
        });
        userListAdapter.notifyDataSetChanged();

        getUsers();
    }

    private void addUserComment(final String comment, final int userId) {


        if (comment == null || comment.isEmpty()) {
            Toast.makeText(getApplication().getApplicationContext(), "Enter comment", Toast.LENGTH_SHORT).show();
            return;
        }


        class AddComment extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                DBUser user = new DBUser();
                user.setUserId(userId);
                user.setComment(comment);

                //adding to database
                DatabaseClient.getInstance(getApplication().getApplicationContext()).getAppDatabase()
                        .userDao()
                        .insert(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplication().getApplicationContext(), "comment added sucessfully", Toast.LENGTH_SHORT).show();
            }
        }

        AddComment add = new AddComment();
        add.execute();
    }

    private void getUsers() {
        RetroClass retroClass = new RetroClass();
        APIService apiService = retroClass.getApiService();
        Call<JsonArray> call = apiService.getUser();
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {
                    JsonArray responseData = response.body();
                    for (int i = 0; i < responseData.size(); i++) {
                        JsonObject userObj = responseData.get(i).getAsJsonObject();
                        JsonObject ownerObj = userObj.getAsJsonObject("owner");
                        User user = new User();
                        user.setName(userObj.get("name").isJsonNull() ? "" : userObj.get("name").getAsString());
                        user.setUserId(userObj.get("id").getAsInt());
                        user.setDescription(userObj.get("description").isJsonNull() ? "" : userObj.get("description").getAsString());
                        user.setImage(ownerObj.get("avatar_url").isJsonNull() ? "" : ownerObj.get("avatar_url").getAsString());
                        user.setGitUrl(ownerObj.get("html_url").isJsonNull() ? "" : ownerObj.get("html_url").getAsString());
                        userArrayList.add(user);
                    }
                    mUserArrayList.addAll(userArrayList.subList(0, 10));
                    userListAdapter.updateData(mUserArrayList);
//                    userListAdapter.updateData(userArrayList);
                    userListAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public RecyclerView.Adapter getUserListAdapter() {
        return userListAdapter;
    }
}
