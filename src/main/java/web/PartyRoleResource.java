package web;

import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
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
import models.*;

@Path("/api/role")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PartyRoleResource {

    @GET
    public List<?> list(@QueryParam("fields") String fields) {
        if (null == fields)
            return PartyRole.listAll();
        List<?> l = PartyRole.list("SELECT " + fields + " FROM PartyRole");
        List<Map<String, Object>> result = new LinkedList<>();// ArrayList doesn't exist
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
            return PartyRole.findById(id);
        List<?> l = PartyRole.list("SELECT " + fields + " FROM PartyRole WHERE id = " + id);
        List<Map<String, Object>> result = new LinkedList<>();// ArrayList doesn't exist
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
    public Response create(PartyRole i) {
        i.persist();
        return Response.created(URI.create("/api/role/" + i.id)).build();
    }

    @PATCH
    @Path("{id}")
    @Transactional
    public Object patch(@PathParam("id") long id, JsonNode resource)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
            ClassNotFoundException, JsonPatchException, IOException, ParseException {
        PartyRole updated = PartyRole.findById(id);
        if (null == updated)
            return Response.status(Status.NOT_FOUND).build();
        JsonMergePatch patch = JsonMergePatch.fromJson(resource);
        JsonNode target = patch.apply(new ObjectMapper().readTree(
                new ObjectMapper().writeValueAsString(updated)));
        if (resource.has("name"))
            updated.name = resource.get("name").isNull() ? null
                    : target.get("name").asText();
        if (resource.has("status"))
            updated.status = resource.get("status").isNull() ? null
                    : target.get("status").asText();
        if (resource.has("statusReason"))
            updated.statusReason = resource.get("statusReason").isNull() ? null
                    : target.get("statusReason").asText();
        if (resource.has("validFrom"))
            updated.validFrom = resource.get("validFrom").isNull() ? null
                    : new Date(target.get("validFrom").asLong());
        if (resource.has("validUntil"))
            updated.validUntil = resource.get("validUntil").isNull() ? null
                    : new Date(target.get("validUntil").asLong());
        if (target.has("engageParty"))
            updated.engageParty = resource.get("engageParty")
                    .isNull() ? null
                            : new ObjectMapper().readValue(
                                    target.get("engageParty").traverse(), RelatedParty.class);
        if (resource.has("creditProfile")) {
            for (PartyCreditProfile creditProfile : updated.creditProfile)
            creditProfile.delete();
            updated.creditProfile.clear();
            if (!resource.get("creditProfile").isNull()) {
                updated.creditProfile.addAll(new ObjectMapper().readerFor(new TypeReference<Set<PartyCreditProfile>>() {
                }).readValue(target.get("creditProfile")));
                for (PartyCreditProfile creditProfile : updated.creditProfile) {
                    creditProfile.partyRole = updated;
                    creditProfile.persist();
                }
            }
        }
        if (resource.has("paymentMethod")) {
            for (PaymentMethodRef paymentMethod : updated.paymentMethod)
                paymentMethod.delete();
            updated.paymentMethod.clear();
            if (!resource.get("paymentMethod").isNull()) {
                updated.paymentMethod
                        .addAll(new ObjectMapper().readerFor(new TypeReference<Set<PaymentMethodRef>>() {
                        }).readValue(target.get("paymentMethod")));
                for (PaymentMethodRef paymentMethod : updated.paymentMethod) {
                    paymentMethod.partyRole = updated;
                    paymentMethod.persist();
                }
            }
        }
        if (resource.has("account")) {
            for (AccountRef account : updated.account)
                account.delete();
            updated.account.clear();
            if (!resource.get("account").isNull()) {
                updated.account.addAll(new ObjectMapper().readerFor(new TypeReference<Set<AccountRef>>() {
                }).readValue(target.get("account")));
                for (AccountRef account : updated.account) {
                    account.partyRole = updated;
                    account.persist();
                }
            }
        }
        if (resource.has("agreement")) {
            for (AgreementRef agreement : updated.agreement)
                agreement.delete();
            updated.agreement.clear();
            if (!resource.get("agreement").isNull()) {
                updated.agreement.addAll(new ObjectMapper().readerFor(new TypeReference<Set<AgreementRef>>() {
                }).readValue(target.get("agreement")));
                for (AgreementRef agreement : updated.agreement) {
                    agreement.partyRole = updated;
                    agreement.persist();
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
                    contactMedium.partyRole = updated;
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
                    relatedParty.partyRole = updated;
                    relatedParty.persist();
                }
            }
        }
        if (resource.has("characteristic")) {
            for (Characteristic characteristic : updated.characteristic)
                characteristic.delete();
            updated.characteristic.clear();
            if (!resource.get("characteristic").isNull()) {
                updated.characteristic
                        .addAll(new ObjectMapper().readerFor(new TypeReference<Set<Characteristic>>() {
                        }).readValue(target.get("characteristic")));
                for (Characteristic characteristic : updated.characteristic) {
                    characteristic.partyRole = updated;
                    characteristic.persist();
                }
            }
        }
        updated.persist();
        return updated;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id) {
        PartyRole.deleteById(id);
        return Response.status(204).build();
    }
}
