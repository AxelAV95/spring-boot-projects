package com.example.hibernate.dao;

import java.util.List;

import com.example.hibernate.entity.Manga;

public interface MangaDao {

	public void save(Manga manga);
	public Manga findById(Integer id);
	public List<Manga> findAll();
	public void update(Manga manga);
	public void deleteById(Integer id);
}
