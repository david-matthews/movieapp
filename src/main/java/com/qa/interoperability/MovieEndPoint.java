package com.qa.interoperability;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.iMovieSurface;

@Path("/Movie")
public class MovieEndPoint {
	@Inject
	private iMovieSurface service;
	
	@GET
	@Path("/json")
	@Produces({"application/json"})
	public String getAllMovies() {
		return service.getAllMovies();
	}
	
	@GET
	@Path("/json/{id}")
	@Produces({"application/json"})
	public String getAMovie (@PathParam("id") Long id) {
		return service.getAMovie(id);
	}
	
	@POST
	@Path("/json")
	@Produces({"application/json"})
	public String createAMovie (String movieJSON) {
		return service.createAMovie(movieJSON);
	}
	
	@GET
	@Path("/json")
	@Produces({"application/json"})
	public String deleteAMovie (Long id) {
		return service.getAllMovies();
	}
	
	@POST
	@Path("/json")
	@Produces({"application/json"})
	public String updateAMovie (Long id, String movieJSON) {
		return service.updateAMovie(id, movieJSON);
	}
	
	
}
