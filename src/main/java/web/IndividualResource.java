package web;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import events.*;
import models.*;

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
        new IndividualCreateEvent(i).publish();
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
        if (resource.has("creditRating")) {
            for (PartyCreditProfile profile : updated.creditRating)
                profile.delete();
            updated.creditRating.clear();
            if (!resource.get("creditRating").isNull()) {
                updated.creditRating.addAll(new ObjectMapper().readerFor(new TypeReference<Set<PartyCreditProfile>>() {
                }).readValue(target.get("creditRating")));
                for (PartyCreditProfile profile : updated.creditRating) {
                    profile.individual = updated;
                    profile.persist();
                }
            }
        }
        if (resource.has("disability")) {
            for (Disability disability : updated.disability)
                disability.delete();
            updated.disability.clear();
            if (!resource.get("disability").isNull()) {
                updated.disability.addAll(new ObjectMapper().readerFor(new TypeReference<Set<Disability>>() {
                }).readValue(target.get("disability")));
                for (Disability disability : updated.disability) {
                    disability.individual = updated;
                    disability.persist();
                }
            }
        }
        if (resource.has("externalReference")) {
            for (ExternalReference externalReference : updated.externalReference)
                externalReference.delete();
            updated.externalReference.clear();
            if (!resource.get("externalReference").isNull()) {
                updated.externalReference
                        .addAll(new ObjectMapper().readerFor(new TypeReference<Set<ExternalReference>>() {
                        }).readValue(target.get("externalReference")));
                for (ExternalReference externalReference : updated.externalReference) {
                    externalReference.individual = updated;
                    externalReference.persist();
                }
            }
        }
        if (resource.has("otherName")) {
            for (OtherNameIndividual otherName : updated.otherName)
                otherName.delete();
            updated.otherName.clear();
            if (!resource.get("otherName").isNull()) {
                updated.otherName.addAll(new ObjectMapper().readerFor(new TypeReference<Set<OtherNameIndividual>>() {
                }).readValue(target.get("otherName")));
                for (OtherNameIndividual otherName : updated.otherName) {
                    otherName.individual = updated;
                    otherName.persist();
                }
            }
        }
        if (resource.has("partyCharacteristic")) {
            for (Characteristic partyCharacteristic : updated.partyCharacteristic)
                partyCharacteristic.delete();
            updated.partyCharacteristic.clear();
            if (!resource.get("partyCharacteristic").isNull()) {
                updated.partyCharacteristic
                        .addAll(new ObjectMapper().readerFor(new TypeReference<Set<Characteristic>>() {
                        }).readValue(target.get("partyCharacteristic")));
                for (Characteristic partyCharacteristic : updated.partyCharacteristic) {
                    partyCharacteristic.individual = updated;
                    partyCharacteristic.persist();
                }
            }
        }
        if (resource.has("languageAbility")) {
            for (LanguageAbility languageAbility : updated.languageAbility)
                languageAbility.delete();
            updated.languageAbility.clear();
            if (!resource.get("languageAbility").isNull()) {
                updated.languageAbility.addAll(new ObjectMapper().readerFor(new TypeReference<Set<LanguageAbility>>() {
                }).readValue(target.get("languageAbility")));
                for (LanguageAbility languageAbility : updated.languageAbility) {
                    languageAbility.individual = updated;
                    languageAbility.persist();
                }
            }
        }
        if (resource.has("individualIdentification")) {
            for (IndividualIdentification individualIdentification : updated.individualIdentification)
                individualIdentification.delete();
            updated.individualIdentification.clear();
            if (!resource.get("individualIdentification").isNull()) {
                updated.individualIdentification
                        .addAll(new ObjectMapper().readerFor(new TypeReference<Set<IndividualIdentification>>() {
                        }).readValue(target.get("individualIdentification")));
                for (IndividualIdentification individualIdentification : updated.individualIdentification) {
                    individualIdentification.individual = updated;
                    individualIdentification.persist();
                }
            }
        }
        if (resource.has("taxExcemtionCertificate")) {
            for (TaxExcemtionCertificate taxExcemtionCertificate : updated.taxExcemtionCertificate)
                taxExcemtionCertificate.delete();
            updated.taxExcemtionCertificate.clear();
            if (!resource.get("taxExcemtionCertificate").isNull()) {
                updated.taxExcemtionCertificate
                        .addAll(new ObjectMapper().readerFor(new TypeReference<Set<TaxExcemtionCertificate>>() {
                        }).readValue(target.get("taxExcemtionCertificate")));
                for (TaxExcemtionCertificate taxExcemtionCertificate : updated.taxExcemtionCertificate) {
                    taxExcemtionCertificate.individual = updated;
                    taxExcemtionCertificate.persist();
                }
            }
        }
        if (resource.has("contactMedium")) {
            for (ContactMedium contactMedium : updated.contactMedium)
                contactMedium.delete();
            updated.contactMedium.clear();
            if (!resource.get("contactMedium").isNull()) {
                updated.contactMedium.addAll(new ObjectMapper().readerFor(new TypeReference<Set<ContactMedium>>() {
                }).readValue(target.get("contactMedium")));
                for (ContactMedium contactMedium : updated.contactMedium) {
                    contactMedium.individual = updated;
                    contactMedium.persist();
                }
            }
        }
        if (resource.has("relatedParty")) {
            for (RelatedParty relatedParty : updated.relatedParty)
                relatedParty.delete();
            updated.relatedParty.clear();
            if (!resource.get("relatedParty").isNull()) {
                updated.relatedParty.addAll(new ObjectMapper().readerFor(new TypeReference<Set<RelatedParty>>() {
                }).readValue(target.get("relatedParty")));
                for (RelatedParty relatedParty : updated.relatedParty) {
                    relatedParty.individual = updated;
                    relatedParty.persist();
                }
            }
        }
        if (resource.has("skill")) {
            for (Skill skill : updated.skill)
                skill.delete();
            updated.skill.clear();
            if (!resource.get("skill").isNull()) {
                updated.skill.addAll(new ObjectMapper().readerFor(new TypeReference<Set<Skill>>() {
                }).readValue(target.get("skill")));
                for (Skill skill : updated.skill) {
                    skill.individual = updated;
                    skill.persist();
                }
            }
        }
        updated.persist();
        new IndividualAttributeValueChangeEvent(updated).publish();
        return updated;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id) {
        Individual i = Individual.findById(id);
        if (null != i) {
            new IndividualDeleteEvent(i).publish();
            i.delete();
        }
        return Response.status(204).build();
    }
}
