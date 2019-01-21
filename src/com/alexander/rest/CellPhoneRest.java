package com.alexander.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alexander.beans.CellPhoneDTO;
import com.alexander.services.CellPhoneService;

@Path("/cellphones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CellPhoneRest {
	CellPhoneService service = new CellPhoneService();

	@GET
	public Response getCellPhones() {
		ArrayList<CellPhoneDTO> cellPhones = service.cellPhones();

		if(cellPhones.size() == 0) {
			return Response.status(Response.Status.OK).entity("No hay celulares registrados").build();
		} else {
			return Response.status(Response.Status.OK).entity(cellPhones).build();
		}
	}
	
	@GET
	@Path("/{id}")
	public Response getCellPhoneById(@PathParam("id") int id) {
		CellPhoneDTO cellPhone = service.getCellPhoneById(id);
		
		if(cellPhone == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(String.format("El celular con id %s no existe", id)).build();
		} else {
			return Response.status(Response.Status.OK).entity(cellPhone).build();
		}
	}
	
	@POST
	public Response registerCellPhone(CellPhoneDTO cellPhone) {
		int affectedRows = service.registerCellPhone(cellPhone);
		
		if(affectedRows == -1) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo registrar el celular").build();
		} else {
			return Response.status(Response.Status.OK).entity(String.format("Se registró el celular %s satisfactoriamente", cellPhone.getModel())).build(); 
		}
	}
	
	@PUT
	@Path("/{id}")
	public Response updateCellPhone(@PathParam("id") int id, CellPhoneDTO cellPhone) {
		cellPhone.setId(id);
		int affectedRows = service.updateCellPhone(cellPhone);
		
		if (affectedRows == -1) {
			return Response.status(Response.Status.BAD_REQUEST).entity(String.format("No se pudo actualizar el celular con id %s", id)).build();
		} else {
			return Response.status(Response.Status.OK).entity(String.format("Se actualizó el celular con id %s satisfactoriamente", id)).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteCellPhone(@PathParam("id") int id) {
		int affectedRows = service.deleteCellPhone(id);
		
		if(affectedRows == -1) {
			return Response.status(Response.Status.OK).entity(String.format("No se pudo eliminar el celular con id %s", id)).build();
		} else {
			return Response.status(Response.Status.OK).entity(String.format("Se eliminó el celular con id %s satisfactoriamente", id)).build();
		}
	}
}
