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

public abstract class Resource<Model extends ModelBase> {
	public abstract Class<?> getModel();

	public List<?> list(String fields) {
		return ModelBase.listRows(getModel(), fields);
	}

	public Object retrieve(String fields, long id) {
		return ModelBase.retrieve(getModel(), fields, id);
	}

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

	public Response delete(long id) {
		Model tuple = Model.findById(id);
		if (null != tuple) {
			new Event<Model>(tuple, Type.Delete).publish();
			tuple.delete();
		}
		return Response.status(204).build();
	}

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

	public Response unregister(long id) {
		Subscription s = Subscription.findById(id);
		if (null != s)
			s.delete();
		return Response.status(204).build();
	}
}
