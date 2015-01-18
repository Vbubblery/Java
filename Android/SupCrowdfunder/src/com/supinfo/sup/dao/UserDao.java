package com.supinfo.sup.dao;

import android.content.Intent;
import android.os.IBinder;

import com.supinfo.sup.entity.User;

public interface UserDao {
	public User insertuser (User user);
	public void updateuser (User user);
	public User retrieveAlluserById(Long id);
}
