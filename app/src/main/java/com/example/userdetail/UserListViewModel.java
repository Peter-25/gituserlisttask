package com.example.userdetail;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;



public class UserListViewModel extends ViewModel {
    MutableLiveData<String> userName = new MutableLiveData<>();
    MutableLiveData<String> userImage = new MutableLiveData<>();
    MutableLiveData<String> desc = new MutableLiveData<>();


    public void setData(User user) {
        userName.setValue(user.getName());
        userImage.setValue(user.getImage());
        desc.setValue(user.getDescription());
    }

    public MutableLiveData<String> getDesc() {
        return desc;
    }


    public MutableLiveData<String> getUserImage() {
        return userImage;
    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }
}
