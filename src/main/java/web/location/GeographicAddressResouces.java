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
import models.location.GeographicAddress;
import web.Resource;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import events.*;


@Path("api/geographicAddress")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class GeographicAddressResouces extends Resource<GeographicAddress> {

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
    public Response create(GeographicAddress m) {
        return super.create(m);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update (@PathParam long id , GeographicAddress model){

        GeographicAddress geographicAddress = GeographicAddress.findById(id);
        if (geographicAddress==null){
            throw new WebApplicationException("geographicAddress with this Id doesn't exsist! ", 404);
        }
        geographicAddress.href = model.href;
        geographicAddress.streetNr = model.streetNr;
        geographicAddress.streetNrSuffix = model.streetNrSuffix;
        geographicAddress.streetNrLast = model.streetNrLast;
        geographicAddress.streetName = model.streetName;
        geographicAddress.streetType = model.streetType;
        geographicAddress.postcode = model.postcode;
        geographicAddress.streetSuffix = model.streetSuffix;
        geographicAddress.GeAdd_city = model.GeAdd_city;
        geographicAddress.GeographicSubAddress = model.GeographicSubAddress;
        geographicAddress.GeoAdd_Continent = model.GeoAdd_Continent;
        geographicAddress.GeoAdd_Country = model.GeoAdd_Country;
        geographicAddress.GeoAdd_State = model.GeoAdd_State;
        geographicAddress.GeoAdd_Locality = model.GeoAdd_Locality;
        geographicAddress.GeoAdd_BlockHay = model.GeoAdd_BlockHay;
        geographicAddress.persist();
        return Response.ok(geographicAddress).build();
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

    @PATCH
	@Path("{id}")
	@Transactional
	public Object patch(@PathParam("id") long id, JsonNode resource)
	        throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
			ClassNotFoundException, JsonPatchException, IOException, ParseException {
		GeographicAddress updated = GeographicAddress.findById(id);
		if (null == updated)
			return Response.status(Status.NOT_FOUND).build();
		JsonMergePatch patch = JsonMergePatch.fromJson(resource);
		JsonNode target = patch.apply(new ObjectMapper().readTree(
				new ObjectMapper().writeValueAsString(updated)));
		if (resource.has("href"))
			updated.href = resource.get("href").isNull() ? null
					: target.get("href").asText();
        if (resource.has("streetNr"))
            updated.streetNr = resource.get("streetNr").isNull() ? null
                    : target.get("streetNr").asText();
        if (resource.has("streetNrSuffix"))
            updated.streetNrSuffix = resource.get("streetNrSuffix").isNull() ? null
                    : target.get("streetNrSuffix").asText();
        if (resource.has("streetNrLast"))
			updated.streetNrLast = resource.get("streetNrLast").isNull() ? null
					: target.get("streetNrLast").asText();
        if (resource.has("streetName"))
            updated.streetName = resource.get("streetName").isNull() ? null
                    : target.get("streetName").asText();
        if (resource.has("streetType"))
            updated.streetType = resource.get("streetType").isNull() ? null
                    : target.get("streetType").asText();
		if (resource.has("familyName"))
			updated.streetSuffix = resource.get("streetSuffix").isNull() ? null
					: target.get("streetSuffix").asText();
		if (resource.has("familyNamePrefix"))
			updated.postcode = resource.get("postcode").isNull() ? null
					: target.get("postcode").asText();
		
        updated.persist();
		new Event<GeographicAddress>(updated, Type.AttributeValueChange).publish();
		return updated;
	}

    @DELETE
    @Path("hub/{id}")
    @Transactional
    public Response unregister(@PathParam("id") long id) {
        return super.unregister(id);
    }

    @Override
    public Class<?> getModel() {
        return GeographicAddress.class;
    }

    
}
