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
import models.location.GeographicLocation;
import web.Resource;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import events.*;

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
    public Object retrieve(@QueryParam("fields") String fields, @PathParam("id") long id) {
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
	        throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
			ClassNotFoundException, JsonPatchException, IOException, ParseException {
		GeographicLocation updated = GeographicLocation.findById(id);
		if (null == updated)
			return Response.status(Status.NOT_FOUND).build();
		JsonMergePatch patch = JsonMergePatch.fromJson(resource);
		JsonNode target = patch.apply(new ObjectMapper().readTree(
				new ObjectMapper().writeValueAsString(updated)));
		if (resource.has("href"))
			updated.href = resource.get("href").isNull() ? null
					: target.get("href").asText();
        if (resource.has("name"))
            updated.name = resource.get("name").isNull() ? null
                    : target.get("name").asText();
        if (resource.has("geometryType"))
                updated.geometryType = resource.get("geometryType").isNull() ? null
                        : target.get("geometryType").asText();
        if (resource.has("accuracy"))
                updated.accuracy = resource.get("accuracy").isNull() ? null
                        : target.get("accuracy").asText();
        if (resource.has("spatialRef"))
            updated.spatialRef = resource.get("spatialRef").isNull() ? null
                    : target.get("spatialRef").asText();
        if (resource.has("type"))
            updated.type = resource.get("type").isNull() ? null
                    : target.get("type").asText();
		
        updated.persist();
		new Event<GeographicLocation>(updated, Type.AttributeValueChange).publish();
		return updated;
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
        return GeographicLocation.class;
    }

}