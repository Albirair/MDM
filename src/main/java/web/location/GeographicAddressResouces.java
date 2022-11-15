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
import models.location.GeographicAddress;
import web.Resource;

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
    public Object retrieve(@QueryParam("fields") String fields, @PathParam("id") long id) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
    public Response update(@PathParam long id, GeographicAddress model) {

        GeographicAddress geographicAddress = GeographicAddress.findById(id);
        if (geographicAddress == null) {
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

    @PATCH
    @Path("{id}")
    @Transactional
    public Object patch(@PathParam("id") long id, JsonNode resource)
            throws JsonProcessingException, NoSuchFieldException, SecurityException, NoSuchMethodException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        return super.patch(id, resource);
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