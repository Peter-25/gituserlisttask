package com.example.userdetail.userdetails;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userdetail.User;
import com.example.userdetail.commentdatabase.DBUser;
import com.example.userdetail.commentdatabase.DatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class UserDetailViewModel extends AndroidViewModel {
    UserCommentListAdapter userCommentListAdapter = new UserCommentListAdapter();
    ArrayList<DBUser> commentlist = new ArrayList<>();
    private MutableLiveData<String> image = new MutableLiveData<>();

    public MutableLiveData<String> getImage() {
        return image;
    }

    public void setImage(String url) {
        image.setValue(url);
    }

    public UserDetailViewModel(@NonNull Application application) {
        super(application);
        userCommentListAdapter.updateData(commentlist);
        userCommentListAdapter.notifyDataSetChanged();

    }

    public RecyclerView.Adapter getUserCommentListAdapter() {
        return userCommentListAdapter;
    }

    public void getComments(final int userId) {
        class getComment extends AsyncTask<Void, Void, List<DBUser>> {

            @Override
            protected List<DBUser> doInBackground(Void... voids) {
                List<DBUser> commentlist = DatabaseClient
                        .getInstance(getApplication().getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .getAll(userId);
                return commentlist;
            }

            @Override
            protected void onPostExecute(List<DBUser> comments) {
                super.onPostExecute(comments);
                commentlist.addAll(comments);
                userCommentListAdapter.updateData(commentlist);
                userCommentListAdapter.notifyDataSetChanged();
            }
        }

        getComment gt = new getComment();
        gt.execute();
    }

}
