package com.example.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.hibernate.dao.MangaDao;
import com.example.hibernate.entity.Manga;


@Service
public class MangaService {
	
	 @Autowired
	 private MangaDao mangaDao;
	 
	 public void createManga(Manga manga) {
	      mangaDao.save(manga);
	 }
	 
	 public Manga getManga(Integer id) {
		 return mangaDao.findById(id);
	 }
	 
	 public List<Manga> getAllMangas(){
		 return mangaDao.findAll();
	 }
	 
	 public void updateManga(Manga manga) {
		 mangaDao.update(manga);
	 }
	 
	 public void deleteManga(Integer id) {
		 mangaDao.deleteById(id);
	 }

}
