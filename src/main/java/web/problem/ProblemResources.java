package web.problem;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import models.fault.Problem;

@Path("api/problem")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProblemResources {

    @GET
    public Response getList()  {

        List<Problem> problem = Problem.findAll().list();
        return Response.ok(problem)
        .build();
    }

    @GET
    @Path("{id}")
    public Response getSingle(@PathParam int id)  {

        Problem problem = Problem.findById(id);
        return Response.ok(problem)
        .build();
    }

    @POST
    @Transactional
    public Response save(Problem model){

        model.persist();
        return Response.ok(model).build();
    }




    @PUT
    @Transactional
    @Path("{id}")
    public Response update (@PathParam int id , Problem model){

        Problem problem = Problem.findById(id);
        if (problem==null){
            throw new WebApplicationException("Problem with this Id doesn't exsist! ", 404);
        }
        problem.problem_id = model.problem_id;
        problem.name = model.name;
        problem.description = model.description;
        problem.comment = model.comment;
        problem.related_item_id = model.related_item_id;
        problem.persist();
        return Response.ok(problem).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete (@PathParam int id){

        Problem problem = Problem.findById(id);
        if (problem==null){
            throw new WebApplicationException("Problem with this Id doesn't exsist! ", 404);
        }
        problem.delete();
        return Response.ok("Delete successfully").build();
    }
}
