package com.qa.business.service;

public interface iMovieSurface {
	
	String getAllMovies();

	String getAMovie(Long id);
	
	String createAMovie (String movieJSON);
	
	String deleteAMovie (Long id);
	
	String updateAnAccount (Long id, String movieToUpdate);
	
}
