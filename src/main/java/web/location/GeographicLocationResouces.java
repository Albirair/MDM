package web.location;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import models.location.GeographicLocation;
import web.Resource;

@Path("api/GeographicLocationResouces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeographicLocationResouces extends Resource<GeographicLocation> {

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
    public Response create(GeographicLocation m) {
        return super.create(m);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam long id, GeographicLocation model) {

        GeographicLocation geographicLocation = GeographicLocation.findById(id);
        if (geographicLocation == null) {
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

    @PATCH
    @Path("{id}")
    @Transactional
    public Object patch(@PathParam("id") long id, JsonNode resource)
            throws JsonProcessingException, NoSuchFieldException, SecurityException, NoSuchMethodException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        return super.patch(id, resource);
    }

    @DELETE
    @Transactional
    @Path("{id}")
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
        return GeographicLocation.class;
    }

}