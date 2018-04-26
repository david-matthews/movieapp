package com.qa.business.repository;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;

import org.apache.log4j.*;


import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

@Default
public class MovieDBRepository implements iMovieRepository {
	
	private static final Logger LOGGER = Logger.getLogger(MovieDBRepository.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject 
	private JSONUtil util;

	@Override
	public String getAllMovies() {
		Query query = manager.createQuery("SELECT m FROM Movie m");
		Collection<Movie> movies = query.getResultList();
		return util.getJSONforObject(movies);
	}

	@Override
	public String getAMovie(Long id) {
		Movie aMovie = findMovie(id);
		if(aMovie != null) {
			return util.getJSONforObject(aMovie);
		}
		else {
			return "(\"message\":\"Movie not Found\")";
		}
		
	}

	private Movie findMovie(Long id) {
		return manager.find(Movie.class, id);
	}

	
	@Override
	@Transactional (REQUIRED)
	public String createAMovie(String movieJSON) {
		Movie aMovie = util.getObjectForJSON(movieJSON, Movie.class);
		manager.persist(aMovie);
		return "{\"message\": \"account has been sucessfully added\"}";
	}
	
	

	@Override
	@Transactional (REQUIRED)
	public String updateAMovie(Long id, String movieToUpdate) {
		Movie movieUpdate = util.getObjectForJSON(movieToUpdate, Movie.class);
		Movie existingMovie = findMovie(id);
		if (movieToUpdate != null) {
			existingMovie = movieUpdate;
			manager.merge(existingMovie);
			return "{\"message\": \"account sucessfully updated\"}";
		}
		else {
			return "{\"message\": \"account failed to update\"}";
		}
		
	}	
	

	@Override
	@Transactional (REQUIRED)
	public String deleteAMovie(Long id) {
		Movie aMovie = findMovie (id);
		if (aMovie != null) {
			manager.remove(aMovie);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}

}
