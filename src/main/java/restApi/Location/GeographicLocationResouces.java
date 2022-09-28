package restApi.Location;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import models.Location.GeographicLocation;

@Path("api/GeographicLocationResouces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class GeographicLocationResouces {
    
    @GET
    public Response getList()  {
        
        List<GeographicLocation> geographicLocation = GeographicLocation.findAll().list();
        return Response.ok(geographicLocation)
        .build();
    }

    @GET
    @Path("{id}")
    public Response getSingle(@PathParam long id)  {
        
        GeographicLocation geographicLocation = GeographicLocation.findById(id);
        return Response.ok(geographicLocation)
        .build();
    }

    @POST
    @Transactional
    public Response save(GeographicLocation model){
     
        System.out.println("before persisitence");
        model.persist();
        System.out.println("after persisitence");
        return Response.ok(model).build();
    }



    
    @PUT
    @Transactional
    @Path("{id}")
    public Response update (@PathParam long id , GeographicLocation model){

        GeographicLocation geographicLocation = GeographicLocation.findById(id);
        if (geographicLocation==null){
            throw new WebApplicationException("GeographicLocation with this Id doesn't exsist! ", 404);
        }
        geographicLocation.href = model.href;
        geographicLocation.name = model.name;
        geographicLocation.geometryType = model.geometryType;
        geographicLocation.accuracy = model.accuracy;
        geographicLocation.spatialRef = model.spatialRef;
        geographicLocation.geometry = model.geometry;
        geographicLocation.type = model.type;
        geographicLocation.schemaLocation = model.schemaLocation;
        geographicLocation.persist();
        return Response.ok(geographicLocation).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete (@PathParam long id){

        GeographicLocation geographicLocation = GeographicLocation.findById(id);
        if (geographicLocation==null){
            throw new WebApplicationException("GeographicLocation with this Id doesn't exsist! ", 404);
        }
        geographicLocation.delete();
        return Response.ok("Delete successfully").build();
    }
}


