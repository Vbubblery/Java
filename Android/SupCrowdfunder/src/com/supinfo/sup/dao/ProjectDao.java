package com.supinfo.sup.dao;

import java.util.ArrayList;

import com.supinfo.sup.entity.Project;

public interface ProjectDao {
    public Project addproject(Project project);	
    
    public Project retrieveprojectById(Long id);	

    public ArrayList<Project> retrieveAllprojects();
    
    public void updateProject(Project project);
}
