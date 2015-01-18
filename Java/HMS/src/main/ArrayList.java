package main;

import java.util.LinkedList;
import java.util.List;

//generic class
public class ArrayList<T> {
	private List<T> data;

	public ArrayList() {
		data = new LinkedList<T>();
	}

	public boolean add(T val) {
		return data.add(val);
	}

	public T get(int index) {
		return data.get(index);
	}

	public int GetSize() {
		return data.size();
	}

}
