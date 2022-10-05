package models;

import java.io.IOException;
import java.util.Set;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

public interface Patchable {
	public void setForeignKey(Object element)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException;

	public default void patchCollection(Set<?> collection, String key, JsonNode resource, JsonNode target,
			Class<?> elementClass)
			throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
			SecurityException {
		if (resource.has(key)) {
			for (Object element : collection)
				((PanacheEntity) element).delete();
			collection.clear();
			if (!resource.get(key).isNull()) {
				ObjectMapper mapper = new ObjectMapper();
				CollectionType type = mapper.getTypeFactory().constructCollectionType(Set.class, elementClass);
				collection.addAll(mapper.treeToValue(target.get(key), type));
				for (Object element : collection) {
					setForeignKey(element);
					((PanacheEntity) element).persist();
				}
			}
		}
	}
}
