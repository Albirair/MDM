package web.problem;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import models.fault.RCA;
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

@Path("api/rca")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RcaResources extends Resource<RCA> {

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
    public Response create(RCA m) {
        return super.create(m);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam int id, RCA model) {

        RCA rca = RCA.findById(id);
        if (rca == null) {
            throw new WebApplicationException("RCA with this Id doesn't exsist! ", 404);
        }
        // rca.rca_id = model.rca_id;
        rca.name = model.name;
        rca.description = model.description;
        rca.related_process_id = model.related_process_id;
        rca.catogary = model.catogary;
        rca.level = model.level;
        rca.parent_id = model.parent_id;
        rca.persist();
        return Response.ok(rca).build();
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
        RCA updated = RCA.findById(id);
        if (null == updated)
            return Response.status(Status.NOT_FOUND).build();
        JsonMergePatch patch = JsonMergePatch.fromJson(resource);
        JsonNode target = patch.apply(new ObjectMapper().readTree(
                new ObjectMapper().writeValueAsString(updated)));
        /*
         * if (resource.has("rca_id"))
         * updated.rca_id = resource.get("rca_id").isNull() ? null
         * : target.get("rca_id").asInt();
         */
        if (resource.has("name"))
            updated.name = resource.get("name").isNull() ? null
                    : target.get("name").asText();
        if (resource.has("related_process_id"))
            updated.related_process_id = resource.get("related_process_id").isNull() ? null
                    : target.get("related_process_id").asInt();
        if (resource.has("catogary"))
            updated.catogary = resource.get("catogary").isNull() ? null
                    : target.get("catogary").asText();
        if (resource.has("level"))
            updated.level = resource.get("level").isNull() ? null
                    : target.get("level").asInt();
        if (resource.has("parent_id"))
            updated.parent_id = resource.get("parent_id").isNull() ? null
                    : target.get("parent_id").asInt();

        updated.persist();
        new Event<RCA>(updated, Type.AttributeValueChange).publish();
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
        return RCA.class;
    }

}