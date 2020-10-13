package com.example.userdetail;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userdetail.databinding.UserListItemBinding;


import java.text.SimpleDateFormat;
import java.util.List;

class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private List<User> userList;

    SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yyyy");
    UserListInterface userListInterface;

    public UserListAdapter() {

    }

    interface UserListInterface {
        void addComment(int pos, String commen);
        void viewUser(int pos);
    }

    public void setUserListInterface(UserListInterface userListInterface) {
        this.userListInterface = userListInterface;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_list_item, parent, false);
        return new UserListAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserListAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bindData(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateData(List<User> data) {
        this.userList = data;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final UserListItemBinding binding;
        UserListViewModel userListViewModel = new UserListViewModel();

        public ViewHolder(UserListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setMViewModel(userListViewModel);
            this.binding.addComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String comment = ViewHolder.this.binding.commentArea.getText().toString();
                    if (userListInterface != null && getAdapterPosition() >= 0) {
                        userListInterface.addComment(getAdapterPosition(), comment);
                        ViewHolder.this.binding.commentArea.setText("");
                    }
                }
            });
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userListInterface != null && getAdapterPosition() >= 0) {
                        userListInterface.viewUser(getAdapterPosition());
                    }
                }
            });
        }

        public void bindData(User user) {
            userListViewModel.setData(user);
        }
    }


}
