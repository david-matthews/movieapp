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

}
