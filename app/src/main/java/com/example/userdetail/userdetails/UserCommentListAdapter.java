package com.example.userdetail.userdetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userdetail.R;
import com.example.userdetail.User;
import com.example.userdetail.UserListViewModel;
import com.example.userdetail.commentdatabase.DBUser;
import com.example.userdetail.databinding.UserListCommentBinding;
import com.example.userdetail.databinding.UserListItemBinding;

import java.text.SimpleDateFormat;
import java.util.List;

class UserCommentListAdapter extends RecyclerView.Adapter<UserCommentListAdapter.ViewHolder> {
    private List<DBUser> userList;


    public UserCommentListAdapter() {

    }


    @Override
    public UserCommentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserListCommentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_list_comment, parent, false);
        return new UserCommentListAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserCommentListAdapter.ViewHolder holder, int position) {
        DBUser user = userList.get(position);
        holder.binding.comment.setText(user.getComment());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateData(List<DBUser> data) {
        this.userList = data;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final UserListCommentBinding binding;


        public ViewHolder(UserListCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }


}
