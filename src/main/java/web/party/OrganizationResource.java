package web.party;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
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
import models.party.*;
import web.Resource;

@Path("/api/organization")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrganizationResource extends Resource<Organization> {

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
    public Response create(Organization o) {
        return super.create(o);
    }

    @PATCH
    @Path("{id}")
    @Transactional
    public Object patch(@PathParam("id") long id, JsonNode resource)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
            ClassNotFoundException, JsonPatchException, IOException, ParseException {
        Organization updated = Organization.findById(id);
        if (null == updated)
            return Response.status(Status.NOT_FOUND).build();
        JsonMergePatch patch = JsonMergePatch.fromJson(resource);
        JsonNode target = patch.apply(new ObjectMapper().readTree(
                new ObjectMapper().writeValueAsString(updated)));
        if (resource.has("isHeadOffice"))
            updated.isHeadOffice = resource.get("isHeadOffice").isNull() ? null
                    : target.get("isHeadOffice").asBoolean();
        if (resource.has("isLegalEntity"))
            updated.isLegalEntity = resource.get("isLegalEntity").isNull() ? null
                    : target.get("isLegalEntity").asBoolean();
        if (resource.has("name"))
            updated.name = resource.get("name").isNull() ? null
                    : target.get("name").asText();
        if (resource.has("nameType"))
            updated.nameType = resource.get("nameType").isNull() ? null
                    : target.get("nameType").asText();
        if (resource.has("type"))
            updated.type = resource.get("type").isNull() ? null
                    : target.get("type").asText();
        if (resource.has("tradingName"))
            updated.tradingName = resource.get("tradingName").isNull() ? null
                    : target.get("tradingName").asText();
        if (resource.has("existsSince"))
            updated.existsSince = resource.get("existsSince").isNull() ? null
                    : new Date(target.get("existsSince").asLong());
        if (resource.has("existsUntil"))
            updated.existsUntil = resource.get("existsUntil").isNull() ? null
                    : new Date(target.get("existsUntil").asLong());
        if (target.has("status"))
            updated.status = OrganizationStateType.valueOf(target.get("status").asText());
        if (target.has("organizationParentRelationship"))
            updated.organizationParentRelationship = resource.get("organizationParentRelationship")
                    .isNull() ? null
                            : new ObjectMapper().readValue(
                                    target.get("organizationParentRelationship").traverse(),
                                    OrganizationParentRelationship.class);
        if (resource.has("creditRating")) {
            for (PartyCreditProfile creditRating : updated.creditRating)
                creditRating.delete();
            updated.creditRating.clear();
            if (!resource.get("creditRating").isNull()) {
                updated.creditRating.addAll(new ObjectMapper().readerFor(new TypeReference<Set<PartyCreditProfile>>() {
                }).readValue(target.get("creditRating")));
                for (PartyCreditProfile creditRating : updated.creditRating) {
                    creditRating.organization = updated;
                    creditRating.persist();
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
                    externalReference.organization = updated;
                    externalReference.persist();
                }
            }
        }
        if (resource.has("otherName")) {
            for (OtherNameOrganization otherName : updated.otherName)
                otherName.delete();
            updated.otherName.clear();
            if (!resource.get("otherName").isNull()) {
                updated.otherName.addAll(new ObjectMapper().readerFor(new TypeReference<Set<OtherNameOrganization>>() {
                }).readValue(target.get("otherName")));
                for (OtherNameOrganization otherName : updated.otherName) {
                    otherName.organization = updated;
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
                    partyCharacteristic.organization = updated;
                    partyCharacteristic.persist();
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
                    relatedParty.organization = updated;
                    relatedParty.persist();
                }
            }
        }
        if (resource.has("organizationChildRelationship")) {
            for (OrganizationChildRelationship organizationChildRelationship : updated.organizationChildRelationship)
                organizationChildRelationship.delete();
            updated.organizationChildRelationship.clear();
            if (!resource.get("organizationChildRelationship").isNull()) {
                updated.organizationChildRelationship.addAll(new ObjectMapper().readerFor(
                        new TypeReference<Set<OrganizationChildRelationship>>() {
                        }).readValue(target.get("organizationChildRelationship")));
                for (OrganizationChildRelationship organizationChildRelationship : updated.organizationChildRelationship) {
                    organizationChildRelationship.organizationRelationship = updated;
                    organizationChildRelationship.persist();
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
                    contactMedium.organization = updated;
                    contactMedium.persist();
                }
            }
        }
        if (resource.has("organizationIdentification")) {
            for (OrganizationIdentification organizationIdentification : updated.organizationIdentification)
                organizationIdentification.delete();
            updated.organizationIdentification.clear();
            if (!resource.get("organizationIdentification").isNull()) {
                updated.organizationIdentification
                        .addAll(new ObjectMapper().readerFor(new TypeReference<Set<OrganizationIdentification>>() {
                        }).readValue(target.get("organizationIdentification")));
                for (OrganizationIdentification organizationIdentification : updated.organizationIdentification) {
                    organizationIdentification.organization = updated;
                    organizationIdentification.persist();
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
                    taxExcemtionCertificate.organization = updated;
                    taxExcemtionCertificate.persist();
                }
            }
        }
        updated.persist();
        new Event<Organization>(updated, Type.AttributeValueChange).publish();
        return updated;
    }

    @DELETE
    @Path("{id}")
    @Transactional
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
        return Organization.class;
    }
}
