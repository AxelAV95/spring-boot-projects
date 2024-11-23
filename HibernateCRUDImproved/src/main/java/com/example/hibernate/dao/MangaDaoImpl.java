package com.example.hibernate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hibernate.entity.Manga;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class MangaDaoImpl implements MangaDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Manga> findAll() {
		
		TypedQuery<Manga> query = entityManager.createQuery("from Manga",Manga.class);
	    List<Manga> mangas = query.getResultList();	
	    return mangas;
	}

	@Override
	public Manga findById(int id) {
		Manga manga = entityManager.find(Manga.class,id);
		return manga;
	}

	@Override
	public Manga save(Manga manga) {
		Manga dbManga = entityManager.merge(manga);
		return dbManga;
	}

	@Override
	public void deleteById(int id) {
		Manga manga = entityManager.find(Manga.class,id);
		entityManager.remove(manga);
		
	}


}
