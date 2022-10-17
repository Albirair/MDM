package web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.text.ParseException;
import java.util.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;
import events.Event;
import events.Type;
import models.ModelBase;
import models.Subscription;

/**
 * Abstract generic class to be extended by classes with {@code ModelBase}
 * specialization to
 * expose endpoints for CRUD operations
 */
public abstract class Resource<Model extends ModelBase> {
	/**
	 * Utility method necessary to disambiguate database operations
	 *
	 * @return Class object model which extends {@code ModelBase}
	 */
	public abstract Class<?> getModel();

	/**
	 * @param fields comma-seperated list of columns to read from database
	 * @return List&#60;Map&#60;String, Object>> where each list element is a
	 *         database row
	 *         and the fields / columns are utilized as keys in the map. However,
	 *         when {@code fields == null}
	 *         it returns List&#60;ModelBase>
	 */
	public List<?> list(String fields) {
		return ModelBase.retrieve(getModel(), fields);
	}

	/**
	 * @param fields comma-seperated list of columns to read from database
	 * @param id     id of the row / tuple to be retrieved
	 * @return Map&#60String, value> where fields / columns are utilized as keys in
	 *         the map.
	 *         However, when {@code fields == null} it returns ModelBase
	 */
	public Object retrieve(String fields, long id) {
		return ModelBase.retrieve(getModel(), fields, id);
	}

	/**
	 * inserts a new row into the database
	 *
	 * @param inserted the tuple to be inserted
	 * @return {@code javax.ws.rs.core.Response} with status code 201 (created)
	 *         whose body is the new row
	 *         and location HTTP header is the new row id
	 */
	public Response create(Model inserted) {
		inserted.persist();
		new Event<Model>(inserted, Type.Create).publish();
		return Response
				.created(URI.create(
						String.format("%s/%d",
								getClass().getSuperclass().getAnnotation(Path.class).value(), inserted.id)))
				.entity(inserted).build();
	}

	public abstract Object patch(long id, JsonNode resource)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
			ClassNotFoundException, JsonPatchException, IOException, ParseException;

	/**
	 * Deletes row
	 *
	 * @param id id of row to be deleted
	 * @return {@code javax.ws.rs.core.Response} with status code 204 (deleted)
	 */
	public Response delete(long id) {
		Model tuple = Model.findById(id);
		if (null != tuple) {
			new Event<Model>(tuple, Type.Delete).publish();
			tuple.delete();
		}
		return Response.status(204).build();
	}

	/**
	 * Subscribes for events. It assumes the relative url within class is "hub"
	 *
	 * @param j json object which must have at least 2 properies - callback: a full
	 *          URL to receive
	 *          notifications, query: Event type string: {Create,
	 *          AttributeValueChange, Delete} as defined in
	 *          {@code events.Type}
	 * @return {@code javax.ws.rs.core.Response} with status code 201 (created)
	 *         whose body is the new row
	 *         and location HTTP header is the new row / subscription id
	 * @throws MalformedURLException
	 */
	public Response register(JsonNode j) throws MalformedURLException {
		Subscription s = new Subscription(j.get("callback").asText(),
				j.get("query").asText(),
				getModel());
		s.persist();
		return Response
				.created(URI.create(String.format("%s/hub/%d",
						getClass().getSuperclass().getAnnotation(Path.class).value(), s.id)))
				.entity(s).build();
	}

	/**
	 * Unsubscribes for events
	 *
	 * @param id id of the subscription to be removed
	 * @return {@code javax.ws.rs.core.Response} with status code 204 (deleted)
	 */
	public Response unregister(long id) {
		Subscription s = Subscription.findById(id);
		if (null != s)
			s.delete();
		return Response.status(204).build();
	}
}
