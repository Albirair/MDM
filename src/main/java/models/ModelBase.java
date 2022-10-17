package models;

import java.util.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * The base class intended to be extended by persistent classes (models).
 * It defines 2 methods for individual & bulk retrieval.
 */
public abstract class ModelBase extends PanacheEntity {
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
	 */
	public static Object retrieve(Class<?> mod, String fields, long id) {
		if (null == fields)
			return findById(id);
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
}
