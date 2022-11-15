// package web.problem;

// import java.util.List;
// import javax.transaction.Transactional;
// import javax.ws.rs.*;
// import javax.ws.rs.core.Response;
// import javax.ws.rs.core.*;
// import org.jboss.resteasy.annotations.jaxrs.PathParam;
// import models.fault.Problem;
// import web.Resource;
// import java.io.IOException;
// import java.net.MalformedURLException;
// import java.text.ParseException;
// import javax.ws.rs.core.Response.Status;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.github.fge.jsonpatch.JsonPatchException;
// import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
// import events.*;
// import models.fault.*;

// @Path("api/problem")
// @Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
// public class ProblemResources extends Resource<Problem> {

//     @GET
//     public List<?> list(@QueryParam("fields") String fields) {
//         return super.list(fields);
//     }

//     @GET
//     @Path("{id}")
//     public Object retrieve(@QueryParam("fields") String fields, @PathParam("id") long id) {
//         return super.retrieve(fields, id);
//     }

//     @POST
//     @Transactional
//     public Response create(Problem m) {
//         return super.create(m);
//     }

//     @PUT
//     @Transactional
//     @Path("{id}")
//     public Response update(@PathParam int id, Problem model) {

//         Problem problem = Problem.findById(id);
//         if (problem == null) {
//             throw new WebApplicationException("Problem with this Id doesn't exsist! ", 404);
//         }
//         // problem.problem_id = model.problem_id;
//         problem.name = model.name;
//         problem.description = model.description;
//         problem.comment = model.comment;
//         problem.related_item_id = model.related_item_id;
//         problem.persist();
//         return Response.ok(problem).build();
//     }

//     @DELETE
//     @Transactional
//     @Path("{id}")
//     public Response delete(@PathParam("id") long id) {
//         return super.delete(id);
//     }

//     @PATCH
//     @Path("{id}")
//     @Transactional
//     public Object patch(@PathParam("id") long id, JsonNode resource)
//             throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
//             ClassNotFoundException, JsonPatchException, IOException, ParseException {
//         Problem updated = Problem.findById(id);
//         if (null == updated)
//             return Response.status(Status.NOT_FOUND).build();
//         JsonMergePatch patch = JsonMergePatch.fromJson(resource);
//         JsonNode target = patch.apply(new ObjectMapper().readTree(
//                 new ObjectMapper().writeValueAsString(updated)));
//         if (resource.has("name"))
//             updated.name = resource.get("name").isNull() ? null
//                     : target.get("name").asText();
//         if (resource.has("name"))
//             updated.name = resource.get("name").isNull() ? null
//                     : target.get("name").asText();
//         if (resource.has("description"))
//             updated.description = resource.get("description").isNull() ? null
//                     : target.get("description").asText();
//         if (resource.has("comment"))
//             updated.comment = resource.get("comment").isNull() ? null
//                     : target.get("comment").asText();

//         updated.patchCollection(updated.rca, "rca", resource, target, RCA.class);

//         updated.persist();
//         new Event<Problem>(updated, Type.AttributeValueChange).publish();
//         return updated;
//     }

//     @POST
//     @Path("hub")
//     @Transactional
//     public Response register(JsonNode j) throws MalformedURLException {
//         return super.register(j);
//     }

//     @DELETE
//     @Path("hub/{id}")
//     @Transactional
//     public Response unregister(@PathParam("id") long id) {
//         return super.unregister(id);
//     }

//     @Override
//     public Class<?> getModel() {
//         return Problem.class;
//     }
// }