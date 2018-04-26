package com.qa.business.repository;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.*;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

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

}
