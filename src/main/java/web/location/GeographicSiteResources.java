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
import models.location.GeographicSite;
import web.Resource;

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
    public Object retrieve(@QueryParam("fields") String fields, @PathParam("id") long id) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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

    @PATCH
    @Path("{id}")
    @Transactional
    public Object patch(@PathParam("id") long id, JsonNode resource)
            throws JsonProcessingException, NoSuchFieldException, SecurityException, NoSuchMethodException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        return super.patch(id, resource);
    }

    @Override
    public Class<?> getModel() {
        return GeographicSite.class;
    }

}