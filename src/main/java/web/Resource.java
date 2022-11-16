package web;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import events.Event;
import events.Type;
import models.ModelBaseWithoutId;
import models.Subscription;

/**
 * Abstract generic class to be extended by classes with
 * {@code ModelBaseWithoutId}
 * specialization to
 * expose endpoints for CRUD operations
 */
public abstract class Resource<Model extends ModelBaseWithoutId> {
	/**
	 * Utility method necessary to disambiguate database operations
	 *
	 * @return Class object model which extends {@code ModelBaseWithoutId}
	 */
	public abstract Class<?> getModel();

	/**
	 * @param fields comma-seperated list of columns to read from database
	 * @return List&#60;Map&#60;String, Object>> where each list element is a
	 *         database row
	 *         and the fields / columns are utilized as keys in the map. However,
	 *         when {@code fields == null}
	 *         it returns List&#60;ModelBaseWithoutId>
	 */
	public List<?> list(String fields) {
		return ModelBaseWithoutId.retrieve(getModel(), fields);
	}

	/**
	 * @param fields comma-seperated list of columns to read from database
	 * @param id     id of the row / tuple to be retrieved
	 * @return Map&#60String, value> where fields / columns are utilized as keys in
	 *         the map.
	 *         However, when {@code fields == null} it returns ModelBaseWithoutId
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public Object retrieve(String fields, long id) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return ModelBaseWithoutId.retrieve(getModel(), fields, id);
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
								getClass().getSuperclass().getAnnotation(Path.class).value(),
								inserted.getId())))
				.entity(inserted).build();
	}

	public Object patch(long id, JsonNode resource)
			throws JsonProcessingException, NoSuchFieldException, SecurityException, NoSuchMethodException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		@SuppressWarnings("unchecked")
		Model updated = (Model) getModel().getMethod("findById", Object.class).invoke(null, id);
		if (null == updated)
			return Response.status(Status.NOT_FOUND).build();
		updated.patch(resource);
		new Event<Model>(updated, Type.AttributeValueChange).publish();
		return updated;
	}

	/**
	 * Deletes row
	 *
	 * @param id id of row to be deleted
	 * @return {@code javax.ws.rs.core.Response} with status code 204 (deleted)
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public Response delete(long id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		@SuppressWarnings("unchecked")
		Model tuple = (Model) getModel().getMethod("findById", Object.class).invoke(null, id);
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
