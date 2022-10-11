package models;

import java.net.*;
import javax.persistence.*;
import events.Type;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Subscription extends PanacheEntity {
	@Column(nullable = false)
	public URL callback;
	@Column(nullable = false)
	public Type eventType;
	@Column(nullable = false)
	public String model;

	public Subscription() {
	}

	/* public Subscription(String c, String t, String m) throws MalformedURLException {
		callback = new URL(c);
		eventType = Type.valueOf(t);
		model = m;
	} */

	public Subscription(String c, String t, Class<?> m) throws MalformedURLException {
		callback = new URL(c);
		eventType = Type.valueOf(t);
		model = m.getName();
	}
}
