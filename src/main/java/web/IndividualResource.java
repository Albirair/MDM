package web;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import events.*;
import models.party.*;

@Path("/api/individual")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IndividualResource {
    @GET
    public List<?> list(@QueryParam("fields") String fields) {
        if (null == fields)
            return Individual.listAll();
        List<?> l = Individual.list("SELECT " + fields + " FROM Individual");
        List<Map<String, Object>> result = new ArrayList<>();
        String[] f = fields.split(",");
        for (Object row : l) {
            Map<String, Object> r = new HashMap<>();
            for (int index = 0; index < f.length; ++index)
                r.put(f[index], ((Object[]) row)[index]);
            result.add(r);
        }
        return result;
    }

    @GET
    @Path("{id}")
    public Object retrieve(@QueryParam("fields") String fields, @PathParam("id") long id) {
        if (null == fields)
            return Individual.findById(id);
        List<?> l = Individual.list("SELECT " + fields + " FROM Individual WHERE id = " + id);
        List<Map<String, Object>> result = new ArrayList<>();
        String[] f = fields.split(",");
        Map<String, Object> r = new HashMap<>();
        Object[] row = (Object[]) l.get(0);
        for (int index = 0; index < f.length; ++index)
            r.put(f[index], row[index]);
        result.add(r);
        return r;
    }

    @POST
    @Transactional
    public Response create(Individual i) {
        i.persist();
        new Event<Individual>(i, Type.Create).publish();
        return Response.created(URI.create("/api/individual/" + i.id)).entity(i).build();
    }

    @PATCH
    @Path("{id}")
    @Transactional
    public Object patch(@PathParam("id") long id, JsonNode resource)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
            ClassNotFoundException, JsonPatchException, IOException, ParseException {
        Individual updated = Individual.findById(id);
        if (null == updated)
            return Response.status(Status.NOT_FOUND).build();
        JsonMergePatch patch = JsonMergePatch.fromJson(resource);
        JsonNode target = patch.apply(new ObjectMapper().readTree(
                new ObjectMapper().writeValueAsString(updated)));
        if (resource.has("aristoctraticTitle"))
            updated.aristoctraticTitle = resource.get("aristoctraticTitle").isNull() ? null
                    : target.get("aristoctraticTitle").asText();
        if (resource.has("birthDate"))
            updated.birthDate = resource.get("birthDate").isNull() ? null
                    : new Date(target.get("birthDate").asLong());
        if (resource.has("countryOfBirth"))
            updated.countryOfBirth = resource.get("countryOfBirth").isNull() ? null
                    : target.get("countryOfBirth").asText();
        if (resource.has("deathDate"))
            updated.deathDate = resource.get("deathDate").isNull() ? null
                    : new Date(target.get("deathDate").asLong());
        if (resource.has("familyName"))
            updated.familyName = resource.get("familyName").isNull() ? null
                    : target.get("familyName").asText();
        if (resource.has("familyNamePrefix"))
            updated.familyNamePrefix = resource.get("familyNamePrefix").isNull() ? null
                    : target.get("familyNamePrefix").asText();
        if (resource.has("formattedName"))
            updated.formattedName = resource.get("formattedName").isNull() ? null
                    : target.get("formattedName").asText();
        if (resource.has("fullName"))
            updated.fullName = resource.get("fullName").isNull() ? null
                    : target.get("fullName").asText();
        if (resource.has("gender"))
            updated.gender = resource.get("gender").isNull() ? null
                    : target.get("gender").asText();
        if (resource.has("generation"))
            updated.generation = resource.get("generation").isNull() ? null
                    : target.get("generation").asText();
        if (resource.has("givenName"))
            updated.givenName = resource.get("givenName").isNull() ? null
                    : target.get("givenName").asText();
        if (resource.has("legalName"))
            updated.legalName = resource.get("legalName").isNull() ? null
                    : target.get("legalName").asText();
        if (resource.has("location"))
            updated.location = resource.get("location").isNull() ? null
                    : target.get("location").asText();
        if (resource.has("maritalStatus"))
            updated.maritalStatus = resource.get("maritalStatus").isNull() ? null
                    : target.get("maritalStatus").asText();
        if (resource.has("middleName"))
            updated.middleName = resource.get("middleName").isNull() ? null
                    : target.get("middleName").asText();
        if (resource.has("nationality"))
            updated.nationality = resource.get("nationality").isNull() ? null
                    : target.get("nationality").asText();
        if (resource.has("placeOfBirth"))
            updated.placeOfBirth = resource.get("placeOfBirth").isNull() ? null
                    : target.get("placeOfBirth").asText();
        if (resource.has("preferredGivenName"))
            updated.preferredGivenName = resource.get("preferredGivenName").isNull() ? null
                    : target.get("preferredGivenName").asText();
        if (resource.has("title"))
            updated.title = resource.get("title").isNull() ? null
                    : target.get("title").asText();
        if (target.has("status"))
            updated.status = IndividualStateType.valueOf(target.get("status").asText());
        updated.patchCollection(updated.creditRating, "creditRating", resource, target, PartyCreditProfile.class);
        updated.patchCollection(updated.disability, "disability", resource, target, Disability.class);
        updated.patchCollection(updated.externalReference, "externalReference", resource, target,
                ExternalReference.class);
        updated.patchCollection(updated.otherName, "otherName", resource, target, OtherNameIndividual.class);
        updated.patchCollection(updated.partyCharacteristic, "partyCharacteristic", resource, target,
                Characteristic.class);
        updated.patchCollection(updated.languageAbility, "languageAbility", resource, target, LanguageAbility.class);
        updated.patchCollection(updated.individualIdentification, "individualIdentification", resource, target,
                IndividualIdentification.class);
        updated.patchCollection(updated.taxExcemtionCertificate, "taxExcemtionCertificate", resource, target,
                TaxExcemtionCertificate.class);
        updated.patchCollection(updated.contactMedium, "contactMedium", resource, target, ContactMedium.class);
        updated.patchCollection(updated.relatedParty, "relatedParty", resource, target, RelatedParty.class);
        updated.patchCollection(updated.skill, "skill", resource, target, Skill.class);
        updated.persist();
        new Event<Individual>(updated, Type.AttributeValueChange).publish();
        return updated;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id) {
        Individual i = Individual.findById(id);
        if (null != i) {
            new Event<Individual>(i, Type.Delete).publish();
            i.delete();
        }
        return Response.status(204).build();
    }
}
