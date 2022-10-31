package web.resource;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import models.resource.Resource;

@Path("/api/resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResourceResource extends web.Resource<Resource> {
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
    public Response create(Resource r) {
        return super.create(r);
    }

    @PATCH
    @Path("{id}")
    @Transactional
    public Object patch(@PathParam("id") long id, JsonNode resource)
            throws JsonProcessingException, NoSuchFieldException, SecurityException, NoSuchMethodException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        return super.patch(id, resource);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id) throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        return super.delete(id);
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
        return Resource.class;
    }
}