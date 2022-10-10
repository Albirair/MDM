package web.location;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import models.location.GeographicAddress;

@Path("api/geographicAddress")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class GeographicAddressResouces {

    @GET
    public Response getList()  {

        List<GeographicAddress> geographicAddress = GeographicAddress.findAll().list();
        return Response.ok(geographicAddress)
        .build();
    }

    @GET
    @Path("{id}")
    public Response getSingle(@PathParam long id)  {

        GeographicAddress geographicAddress = GeographicAddress.findById(id);
        return Response.ok(geographicAddress)
        .build();
    }

    @POST
    @Transactional
    public Response save(GeographicAddress model){

        System.out.println("before persisitence");
        model.persist();
        System.out.println("after persisitence");
        return Response.ok(model).build();
    }




    @PUT
    @Transactional
    @Path("{id}")
    public Response update (@PathParam long id , GeographicAddress model){

        GeographicAddress geographicAddress = GeographicAddress.findById(id);
        if (geographicAddress==null){
            throw new WebApplicationException("geographicAddress with this Id doesn't exsist! ", 404);
        }
        geographicAddress.href = model.href;
        geographicAddress.streetNr = model.streetNr;
        geographicAddress.streetNrSuffix = model.streetNrSuffix;
        geographicAddress.streetNrLast = model.streetNrLast;
        geographicAddress.streetName = model.streetName;
        geographicAddress.streetType = model.streetType;
        geographicAddress.postcode = model.postcode;
        geographicAddress.streetSuffix = model.streetSuffix;
        geographicAddress.GeAdd_city = model.GeAdd_city;
        geographicAddress.GeographicSubAddress = model.GeographicSubAddress;
        geographicAddress.GeoAdd_Continent = model.GeoAdd_Continent;
        geographicAddress.GeoAdd_Country = model.GeoAdd_Country;
        geographicAddress.GeoAdd_State = model.GeoAdd_State;
        geographicAddress.GeoAdd_Locality = model.GeoAdd_Locality;
        geographicAddress.GeoAdd_BlockHay = model.GeoAdd_BlockHay;
        geographicAddress.persist();
        return Response.ok(geographicAddress).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete (@PathParam long id){

        GeographicAddress geographicAddress = GeographicAddress.findById(id);
        if (geographicAddress==null){
            throw new WebApplicationException("GeographicAddress with this Id doesn't exsist! ", 404);
        }
        geographicAddress.delete();
        return Response.ok("Delete successfully").build();
    }
}
