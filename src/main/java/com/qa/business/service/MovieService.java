package com.qa.business.service;

import javax.inject.Inject;

import com.qa.business.repository.iMovieRepository;


public class MovieService implements iMovieSurface {
	
	@Inject
	private iMovieRepository repo;
	

	@Override
	public String getAllMovies() {
		
		return repo.getAllMovies();
	}


	@Override
	public String getAMovie(Long id) {
		
		return repo.getAMovie(id);
	}


	@Override
	public String createAMovie(String movieJSON) {
		
		return repo.createAMovie(movieJSON);
	}


	@Override
	public String deleteAMovie(Long id) {
		
		return repo.deleteAMovie(id);
	}


	@Override
	public String updateAMovie(Long id, String movieToUpdate) {
		
		return repo.updateAMovie(id, movieToUpdate);
	}
	
	

}
