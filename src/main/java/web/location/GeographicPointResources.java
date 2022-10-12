package web.location;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;
import models.location.GeographicPoint;
import web.Resource;

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

    @Override
    public Object patch(long id, JsonNode resource)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
            ClassNotFoundException, JsonPatchException, IOException, ParseException {
        // TODO Auto-generated method stub
        return null;
    }
}