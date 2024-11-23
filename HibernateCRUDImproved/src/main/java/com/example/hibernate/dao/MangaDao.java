package com.example.hibernate.dao;

import java.util.List;

import com.example.hibernate.entity.Manga;

public interface MangaDao {

	public List<Manga> findAll();
	
	public Manga findById(int id);
	
	public Manga save(Manga employee); // insert and update
	
	public void deleteById(int id);	
}
