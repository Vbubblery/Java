package com.supinfo.sup.dao;

import java.util.ArrayList;

import com.supinfo.sup.entity.Category;

public interface CategoryDao {
		public Category insertcategory (Category category);
		public ArrayList<Category> retrieveAllCategory();
}
