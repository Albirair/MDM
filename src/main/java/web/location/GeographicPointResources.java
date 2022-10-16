package web.location;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import models.location.GeographicPoint;
import web.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import events.*;


@Path("api/GeographicPointResources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeographicPointResources extends Resource<GeographicPoint> {

    @GET
    public List<?> list(@QueryParam("fields") String fields) {
        return super.list(fields);
    }

    @GET
    @Path("{id}")
    public Object retrieve(@QueryParam("fields") String fields, @PathParam("id") long id) {
        return super.retrieve(fields, id);
    }

    @POST
    @Transactional
    public Response create(GeographicPoint m) {
        return super.create(m);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam long id, GeographicPoint model) {

        GeographicPoint geographicPoint = GeographicPoint.findById(id);
        if (geographicPoint == null) {
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
    public Response delete(@PathParam("id") long id) {
        return super.delete(id);
    }
    @PATCH
    @Path("{id}")
    @Transactional
    public Object patch(@PathParam("id") long id, JsonNode resource)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
            ClassNotFoundException, JsonPatchException, IOException, ParseException {
        GeographicPoint updated = GeographicPoint.findById(id);
        if (null == updated)
            return Response.status(Status.NOT_FOUND).build();
        JsonMergePatch patch = JsonMergePatch.fromJson(resource);
        JsonNode target = patch.apply(new ObjectMapper().readTree(
                new ObjectMapper().writeValueAsString(updated)));
        if (resource.has("x"))
            updated.x = resource.get("x").isNull() ? null
                    : target.get("x").asText();
        if (resource.has("y"))
            updated.y = resource.get("y").isNull() ? null
                    : target.get("y").asText();
        if (resource.has("z"))
            updated.z = resource.get("z").isNull() ? null
                    : target.get("z").asText();


        updated.persist();
        new Event<GeographicPoint>(updated, Type.AttributeValueChange).publish();
        return updated;
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
        return GeographicPoint.class;
    }

  
}