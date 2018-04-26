package com.qa.business.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.persistence.domain.Movie;

import com.qa.util.JSONUtil;

@Alternative
public class MovieMapRepository implements iMovieRepository {
	
	private final Long Start_Count = 1L;
	private Map<Long, Movie> movieMap;
	private Long id;
	
	public MovieMapRepository () {
		this.movieMap = new HashMap<Long, Movie>();
		id = Start_Count;
		startMovieMap();
	}
	
	private void startMovieMap() {
		Movie movie = new Movie("Up", "Family", "U");
		movieMap.put(Start_Count, movie);
		
	}

	@Inject 
	private JSONUtil util;

	@Override
	public String getAllMovies() {
		return util.getJSONforObject(movieMap.values());
		
	}

	@Override
	public String getAMovie(Long id) {
		
		return util.getJSONforObject(movieMap.get(id));
	}

	@Override
	public String createAMovie(String movieJSON) {
		id++;
		Movie newMovie = util.getObjectForJSON(movieJSON, Movie.class);
		movieMap.put(id, newMovie);
		return "{\"message\"; \"Movie successfully added\"}";
		
	}

	@Override
	public String deleteAMovie(Long id) {
		movieMap.remove(id);
		return "{\"message\"; \"Movie successfully removed\"}";
		
	}

	@Override
	public String updateAMovie(Long id, String movieToUpdate) {
		Movie updateMovie = util.getObjectForJSON(movieToUpdate, Movie.class);
		movieMap.put(id, updateMovie);
		return "{\"message\"; \"Movie successfully updated\"}";
		
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}	

}
