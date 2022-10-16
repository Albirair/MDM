package web.location;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.Date;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;
import models.location.GeographicSite;
import web.Resource;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import events.*;

@Path("api/geographicSite")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeographicSiteResources extends Resource<GeographicSite> {

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
    public Response create(GeographicSite m) {
        return super.create(m);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam int id, GeographicSite model) {

        GeographicSite geographicSite = GeographicSite.findById(id);
        if (geographicSite == null) {
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

    
    @PATCH
	@Path("{id}")
	@Transactional
	public Object patch(@PathParam("id") long id, JsonNode resource)
	        throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
			ClassNotFoundException, JsonPatchException, IOException, ParseException {
		GeographicSite updated = GeographicSite.findById(id);
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
        if (resource.has("code"))
                updated.code = resource.get("code").isNull() ? null
                    : target.get("code").asText();
        if (resource.has("GBID"))
                updated.GBID = resource.get("GBID").isNull() ? null
                    : target.get("GBID").asText();
        if (resource.has("ESID"))
            updated.ESID = resource.get("ESID").isNull() ? null
                    : target.get("ESID").asText();
        if (resource.has("on_air_date"))
            updated.on_air_date = resource.get("on_air_date").isNull() ? null
                    : new Date(target.get("on_air_date").asLong());
        if (resource.has("description"))
            updated.description = resource.get("description").isNull() ? null
                    : target.get("description").asText();
        if (resource.has("status"))
            updated.status = resource.get("status").isNull() ? null
                    : target.get("status").asText();
        if (resource.has("baseType"))
            updated.baseType = resource.get("baseType").isNull() ? null
                    : new Date(target.get("baseType").asLong());
        if (resource.has("type"))
            updated.type = resource.get("type").isNull() ? null
                    : target.get("type").asText();
        if (resource.has("status"))
            updated.status = resource.get("status").isNull() ? null
                    : target.get("status").asText();
        if (resource.has("schemaLocation"))
            updated.schemaLocation = resource.get("schemaLocation").isNull() ? null
                        : target.get("schemaLocation").asText();
		
        updated.persist();
		new Event<GeographicSite>(updated, Type.AttributeValueChange).publish();
		return updated;
	}


    @Override
    public Class<?> getModel() {
        return GeographicSite.class;
    }

   
}