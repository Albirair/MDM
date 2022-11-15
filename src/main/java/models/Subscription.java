package models;

import java.net.*;
import javax.persistence.*;
import events.Type;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Class which registers event listeners in the database
 */
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

	/**
	 * @param c Callback URL to be notified when events occur. It must start with a
	 *          scheme
	 *          (e.g. http://www.example.com) to avoid throwing an exception
	 * @param t Event type string: {Create, AttributeValueChange, Delete} as defined
	 *          in {@code events.Type}
	 * @param m Class Object of the model whose events are of interest to the
	 *          subscriber
	 * @throws MalformedURLException
	 */
	public Subscription(String c, String t, Class<?> m) throws MalformedURLException {
		callback = new URL(c);
		eventType = Type.valueOf(t);
		model = m.getName();
	}
}
