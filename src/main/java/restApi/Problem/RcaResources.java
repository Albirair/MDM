package restApi.Problem;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import models.fault.RCA;

@Path("api/rca")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RcaResources {

    @GET
    public Response getList()  {
        
        List<RCA> rca = RCA.findAll().list();
        return Response.ok(rca)
        .build();
    }
    
    @GET
    @Path("{id}")
    public Response getSingle(@PathParam int id)  {
        
        RCA rca = RCA.findById(id);
        return Response.ok(rca)
        .build();
    }

    @POST
    @Transactional
    public Response save(RCA model){
     
        model.persist();
        return Response.ok(model).build();
    }
    
    @PUT
    @Transactional
    @Path("{id}")
    public Response update (@PathParam int id , RCA model){

        RCA rca = RCA.findById(id);
        if (rca==null){
            throw new WebApplicationException("RCA with this Id doesn't exsist! ", 404);
        }
        rca.rca_id = model.rca_id;
        rca.name = model.name;
        rca.description = model.description;
        rca.related_process_id = model.related_process_id;
        rca.catogary = model.catogary;
        rca.level = model.level;
        rca.parent_id = model.parent_id;
        rca.persist();
        return Response.ok(rca).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete (@PathParam int id){

        RCA rca = RCA.findById(id);
        if (rca==null){
            throw new WebApplicationException("RCA with this Id doesn't exsist! ", 404);
        }
        rca.delete();
        return Response.ok("Delete successfully").build();
    }
}
