package restApi.Location;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import models.Location.GeographicSite;

@Path("api/geographicSite")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class GeographicSiteResources {
    
    @GET
    public Response getList()  {
        
        List<GeographicSite> geographicSite = GeographicSite.findAll().list();
        return Response.ok(geographicSite)
        .build();
    }

    @GET
    @Path("{id}")
    public Response getSingle(@PathParam int id)  {
        
        GeographicSite geographicSite = GeographicSite.findById(id);
        return Response.ok(geographicSite)
        .build();
    }

    @POST
    @Transactional
    public Response save(GeographicSite model){
     
        model.persist();
        return Response.ok(model).build();
    }



    
    @PUT
    @Transactional
    @Path("{id}")
    public Response update (@PathParam int id , GeographicSite model){

        GeographicSite geographicSite = GeographicSite.findById(id);
        if (geographicSite==null){
            throw new WebApplicationException("GeographicSite with this Id doesn't exsist! ", 404);
        }
        geographicSite.name = model.name;
        geographicSite.href = model.href;
        geographicSite.code = model.code;
        geographicSite.GBID = model.GBID;
        geographicSite.ESID = model.ESID;
        geographicSite.on_air_date = model.on_air_date;
        geographicSite.description = model.description;
        geographicSite.status = model.status;
        geographicSite.baseType = model.baseType;
        geographicSite.type = model.type;
        geographicSite.schemaLocation = model.schemaLocation;
        geographicSite.GeoAdd_GeSite = model.GeoAdd_GeSite;
        geographicSite.SiteRel_GeoSite = model.SiteRel_GeoSite;
        geographicSite.CalPeriod_GeoSite = model.CalPeriod_GeoSite;
        geographicSite.GeoLocat_GeoSite = model.GeoLocat_GeoSite;
        return Response.ok(geographicSite).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete (@PathParam int id){

        GeographicSite geographicSite = GeographicSite.findById(id);
        if (geographicSite==null){
            throw new WebApplicationException("geographicSite with this Id doesn't exsist! ", 404);
        }
        geographicSite.delete();
        return Response.ok("Delete successfully").build();
    }
}

