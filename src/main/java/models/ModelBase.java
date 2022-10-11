package models;

import java.util.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

public abstract class ModelBase extends PanacheEntity {
	public static List<?> listRows(Class<?> mod, String fields) {
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
