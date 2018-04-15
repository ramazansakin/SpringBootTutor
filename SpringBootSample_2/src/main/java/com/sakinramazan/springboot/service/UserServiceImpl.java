package com.sakinramazan.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sakinramazan.springboot.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static int counter = 0;
	private static List<User> users;

	static {
		users = DummyKullanicilar();
	}

	public List<User> findAllUsers() {
		return users;
	}

	public User findById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User findByName(String name) {
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	public void saveUser(User user) {
		user.setId(++counter);
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName()) != null;
	}

	public void deleteAllUsers() {
		users.clear();
	}

	private static List<User> DummyKullanicilar() {
		List<User> users = new ArrayList<User>();
		users.add(new User(++counter, "Ali", 20, 10000));
		users.add(new User(++counter, "Veli", 25, 20000));
		users.add(new User(++counter, "Selim", 35, 30000));
		users.add(new User(++counter, "Kazım", 43, 40000));
		return users;
	}

}
