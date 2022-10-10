package web.location;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import models.location.GeographicPoint;

@Path("api/GeographicPointResources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class GeographicPointResources {

    @GET
    public Response getList()  {

        List<GeographicPoint> geographicPoint = GeographicPoint.findAll().list();
        return Response.ok(geographicPoint)
        .build();
    }

    @GET
    @Path("{id}")
    public Response getSingle(@PathParam long id)  {

        GeographicPoint geographicPoint = GeographicPoint.findById(id);
        return Response.ok(geographicPoint)
        .build();
    }

    @POST
    @Transactional
    public Response save(GeographicPoint model){

        System.out.println("before persisitence");
        model.persist();
        System.out.println("after persisitence");
        return Response.ok(model).build();
    }




    @PUT
    @Transactional
    @Path("{id}")
    public Response update (@PathParam long id , GeographicPoint model){

        GeographicPoint geographicPoint = GeographicPoint.findById(id);
        if (geographicPoint==null){
            throw new WebApplicationException("geographicPoint with this Id doesn't exsist! ", 404);
        }
        geographicPoint.x = model.x;
        geographicPoint.y = model.y;
        geographicPoint.z = model.z;
        geographicPoint.persist();
        return Response.ok(geographicPoint).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete (@PathParam long id){

        GeographicPoint geographicPoint = GeographicPoint.findById(id);
        if (geographicPoint==null){
            throw new WebApplicationException("GeographicPoint with this Id doesn't exsist! ", 404);
        }
        geographicPoint.delete();
        return Response.ok("Delete successfully").build();
    }
}
