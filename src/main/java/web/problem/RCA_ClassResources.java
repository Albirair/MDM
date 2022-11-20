package web.problem;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import models.fault.RCA_Class;
import web.Resource;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

@Path("api/RCA_Class")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RCA_ClassResources extends Resource<RCA_Class> {

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
    public Response create(RCA_Class m) {
        return super.create(m);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam int id, RCA_Class model) {

        RCA_Class rca_class = RCA_Class.findById(id);
        if (rca_class == null) {
            throw new WebApplicationException("RCA Item with this Id doesn't exsist! ", 404);
        }
        // rca.rca_id = model.rca_id;
        rca_class.name = model.name;
        rca_class.description = model.description;
        rca_class.catogary = model.catogary;
        rca_class.level = model.level;
        rca_class.ClassFamily_id = model.ClassFamily_id;
        rca_class.ItemClass_id = model.ItemClass_id;
        rca_class.persist();
        return Response.ok(rca_class).build();
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
        return RCA_Class.class;
    }

}