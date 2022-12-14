// package web.problem;

// import java.util.List;
// import javax.transaction.Transactional;
// import javax.ws.rs.*;
// import javax.ws.rs.core.Response;
// import javax.ws.rs.core.*;
// import org.jboss.resteasy.annotations.jaxrs.PathParam;
// import models.fault.RCA_Item;
// import web.Resource;
// import java.lang.reflect.InvocationTargetException;
// import java.net.MalformedURLException;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonNode;

// @Path("api/RCA_Item")
// @Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
// public class RCA_ItemResources extends Resource<RCA_Item> {

//     @GET
//     public List<?> list(@QueryParam("fields") String fields) {
//         return super.list(fields);
//     }

//     @GET
//     @Path("{id}")
//     public Object retrieve(@QueryParam("fields") String fields, @PathParam("id") long id) throws IllegalAccessException,
//             IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//         return super.retrieve(fields, id);
//     }

//     @POST
//     @Transactional
//     public Response create(RCA_Item m) {
//         return super.create(m);
//     }

//     @PUT
//     @Transactional
//     @Path("{id}")
//     public Response update(@PathParam int id, RCA_Item model) {

//         RCA_Item rca_item = RCA_Item.findById(id);
//         if (rca_item == null) {
//             throw new WebApplicationException("RCA Item with this Id doesn't exsist! ", 404);
//         }
//         // rca.rca_id = model.rca_id;
//         rca_item.name = model.name;
//         rca_item.description = model.description;
//         rca_item.catogary = model.catogary;
//         rca_item.level = model.level;
//         rca_item.ItemClass_id = model.ItemClass_id;
//         rca_item.related_process_id = model.related_process_id;
//         rca_item.persist();
//         return Response.ok(rca_item).build();
//     }

//     @DELETE
//     @Transactional
//     @Path("{id}")
//     public Response delete(@PathParam("id") long id) throws IllegalAccessException, IllegalArgumentException,
//             InvocationTargetException, NoSuchMethodException, SecurityException {
//         return super.delete(id);
//     }

//     @PATCH
//     @Path("{id}")
//     @Transactional
//     public Object patch(@PathParam("id") long id, JsonNode resource)
//             throws JsonProcessingException, NoSuchFieldException, SecurityException, NoSuchMethodException,
//             IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
//         return super.patch(id, resource);
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
//         return RCA_Item.class;
//     }

// }