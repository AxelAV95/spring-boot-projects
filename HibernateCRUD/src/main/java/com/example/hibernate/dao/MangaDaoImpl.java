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
	@Transactional
	public void save(Manga manga) {
		
		entityManager.persist(manga);		
				
	}

	@Override
	public Manga findById(Integer id) {
		
		return entityManager.find(Manga.class, id);
		
	}

	@Override
	public List<Manga> findAll() {
		TypedQuery<Manga> query = entityManager.createQuery("FROM Manga", Manga.class);
		
		return query.getResultList();
	}

	@Override
	@Transactional
	public void update(Manga manga) {
		// TODO Auto-generated method stub
		entityManager.merge(manga);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		Manga manga = entityManager.find(Manga.class,id);
		entityManager.remove(manga);
		
	}

}
