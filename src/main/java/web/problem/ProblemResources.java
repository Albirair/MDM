package web.problem;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import models.fault.Problem;
import web.Resource;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

@Path("api/problem")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProblemResources extends Resource<Problem> {

    @GET
    public List<?> list(@QueryParam("fields") String fields) {
        return super.list(fields);
    }

    @GET
    @Path("{id}")
    public Object retrieve(@QueryParam("fields") String fields, @PathParam("id") long id) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return super.retrieve(fields, id);
    }

    @POST
    @Transactional
    public Response create(Problem m) {
        return super.create(m);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam int id, Problem model) {

        Problem problem = Problem.findById(id);
        if (problem == null) {
            throw new WebApplicationException("Problem with this Id doesn't exsist! ", 404);
        }
        // problem.problem_id = model.problem_id;
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
    public Response delete(@PathParam("id") long id) throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        return super.delete(id);
    }

    @PATCH
    @Path("{id}")
    @Transactional
    public Object patch(@PathParam("id") long id, JsonNode resource)
            throws JsonProcessingException, NoSuchFieldException, SecurityException, NoSuchMethodException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        return super.patch(id, resource);
    }

    @POST
    @Path("hub")
    @Transactional
    public Response register(JsonNode j) throws MalformedURLException {
        return super.register(j);
    }

    @DELETE
    @Path("hub/{id}")
    @Transactional
    public Response unregister(@PathParam("id") long id) {
        return super.unregister(id);
    }

    @Override
    public Class<?> getModel() {
        return Problem.class;
    }
}