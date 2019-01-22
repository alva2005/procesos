package com.itt.ws.control;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.itt.ws.modelo.Persona;

@Path("datos")
public class WebServicePersona{
	
	@GET
	@Path("xml")
	@Produces({"text/xml"})
	public Persona personaXML() {
		Persona p = new Persona(1,"RODOLFO", "LANGOSTINO", 47);
		return p;	
	}
	
	@GET
	@Path("json")
	@Produces({"text/json"})
	public String personaJSON() {
		Persona p = new Persona(1,"RODOLFO", "LANGOSTINO", 47);
		Gson gson = new Gson();
		String json = gson.toJson(p);
		return json;	
	}	
}