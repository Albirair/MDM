package models;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;
import javax.persistence.MappedSuperclass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * The base class intended to be extended by persistent classes (models).
 * It defines 2 methods for individual & bulk retrieval.
 */
@MappedSuperclass
public abstract class ModelBase extends PanacheEntity {
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Retrieves a list of rows from the database
	 *
	 * @param mod    class object representing the entity to retreive rows from
	 * @param fields comma-seperated list of columns to read from database
	 * @return List&#60;Map&#60;String, Object>> where each list element is a
	 *         database row
	 *         and the fields / columns are utilized as keys in the map. However,
	 *         when {@code fields == null}
	 *         it returns List&#60;ModelBase>
	 */
	public static List<?> retrieve(Class<?> mod, String fields) {
		if (null == fields)
			return listAll();
		find(fields);
		List<?> l = list("SELECT " + fields + " FROM " + mod.getName());
		List<Map<String, Object>> result = new ArrayList<>();
		String[] f = fields.split(",");
		if (f.length > 1)
			for (Object row : l) {
				Map<String, Object> r = new HashMap<>();
				for (int index = 0; index < f.length; ++index)
					r.put(f[index], ((Object[]) row)[index]);
				result.add(r);
			}
		else
			for (Object value : l) {
				Map<String, Object> r = new HashMap<>();
				r.put(f[0], r);
				for (int index = 0; index < f.length; ++index)
					r.put(f[index], value);
				result.add(r);
			}
		return result;
	}

	/**
	 * Retrieves a row from the database
	 *
	 * @param mod    class object representing the entity to retreive rows from
	 * @param fields comma-seperated list of columns to read from database
	 * @param id     id of the row / tuple to be retrieved
	 * @return Map&#60String, value> where fields / columns are utilized as keys in
	 *         the map.
	 *         However, when {@code fields == null} it returns ModelBase
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object retrieve(Class<?> mod, String fields, long id) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (null == fields)
			return mod.getMethod("findById", Object.class).invoke(null, id);
		List<?> l = list("SELECT " + fields + " FROM " + mod.getName() + " WHERE id = " + id);
		String[] f = fields.split(",");
		Map<String, Object> r = new HashMap<>();
		if (f.length > 1) {
			Object[] row = (Object[]) l.get(0);
			for (int index = 0; index < f.length; ++index)
				r.put(f[index], row[index]);
		} else
			r.put(f[0], l.get(0));
		return r;
	}
	/**
	 * Partial patching of entities
	 * @param resource json object containing the patch
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws JsonProcessingException
	 */
	public void patch(JsonNode resource)
			throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, ClassNotFoundException, JsonProcessingException {
		Iterator<Entry<String, JsonNode>> fields = resource.fields();
		while (fields.hasNext()) {
			Entry<String, JsonNode> f = fields.next();
			String key = f.getKey();
			Field cField = getClass().getDeclaredField(key);
			if (Collection.class.isAssignableFrom(cField.getType())) {
				Method getter = getClass().getMethod(String.format("get%s%s", key.substring(0, 1)
						.toUpperCase(), key.substring(1)));
				Collection<?> collection = (Collection<?>) getter.invoke(this);
				for (Object element : collection)
					((PanacheEntity) element).delete();
				collection.clear();
				if (!resource.get(key).isNull()) {
					String elementName = cField.getGenericType().getTypeName();
					CollectionType type = mapper.getTypeFactory().constructCollectionType(
							Set.class, Class.forName(
									elementName.substring(14, elementName.lastIndexOf(">"))));
					collection.addAll(mapper.treeToValue(f.getValue(), type));
					for (Object element : collection) {
						fixForeignKey((PanacheEntity) element);
						((PanacheEntity) element).persist();
					}
				}
			} else if (String.class.isAssignableFrom(cField.getType()))
				getClass().getMethod(String.format("set%s%s", key.substring(0, 1)
						.toUpperCase(), key.substring(1)), String.class).invoke(this,
								f.getValue().isNull() ? null : f.getValue().asText());
			else if (Date.class.isAssignableFrom(cField.getType()))
				getClass().getMethod(String.format("set%s%s", key.substring(0, 1)
						.toUpperCase(), key.substring(1)), Date.class).invoke(this,
								f.getValue().isNull() ? null : new Date(f.getValue().asLong()));
			else if (Enum.class.isAssignableFrom(cField.getType())) {
				Method valueOf = cField.getType().getMethod("valueOf", String.class);
				Object value = valueOf.invoke(null, f.getValue().asText());
				getClass().getMethod(String.format("set%s%s", key.substring(0, 1)
						.toUpperCase(), key.substring(1)), cField.getType())
						.invoke(this, value);
			} else if (ModelBase.class.isAssignableFrom(cField.getType())) {
				Method getter = getClass().getMethod(String.format("get%s%s", key.substring(0, 1)
						.toUpperCase(), key.substring(1)));
				ModelBase subentity = (ModelBase) getter.invoke(this);
				if (f.getValue().isNull())
					subentity.delete();
				else
					subentity.patch(f.getValue());
			} else
				throw new ClassNotFoundException(String.format("unimplemented patching for %s",
						cField.getType()));
		}
		persist();
	}

	/**
	 * Sets the foreign key in {@code element} to {@code this} using reflection
	 *
	 * @param element the target row which is assumed to have the foreign key column
	 *                named as the referred class & starts with lowercase
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	protected void fixForeignKey(PanacheEntity element) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		element.getClass().getMethod(String.format(
				"set%s", getClass().getSimpleName()), getClass()).invoke(element, this);
	}
}