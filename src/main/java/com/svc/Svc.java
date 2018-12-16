package com.svc;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.util.HibernateUtil;

/**
 * Aquí se realiza la comunicación con hibernate, para realizar las operaciones de
 * guardar, eliminar, actualizar y buscar, los métodos se crearon de tal forma que
 * se le pueda pasar por parámetro cualquier dto y funcione sin necesidad de hacer 
 * cambios en la clase.
 *
 * Se creo un método basics para evitar tener que hacer los 4 métodos con las 3 lineas 
 * que siempre se usan en estas peticiones y no se dejaron por fuera como globales para 
 * evitar errores conceptuales en construcción de servicios.
 *
 * El código esta hecho en ingles, la interfaz gráfica tiene las etiquetas en
 * español y los comentarios se dejan en español para facilidad de revisión
 *
 * @author Cristian Felipe Ramos Lopez
 * @version 1.0
 * @since 2018-12-14
 */

@SuppressWarnings("unchecked")
public class Svc {

	private static final String SAVE = "SAVE";
	private static final String DELETE = "DELETE";
	private static final String UPDATE = "UPDATE";

	private void basics(Object actual, String accion) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		if (accion.equals(SAVE)) {
			session.save(actual);
		} else if (accion.equals(DELETE)) {
			session.delete(actual);
		} else if (accion.equals(UPDATE)) {
			session.update(actual);
		}
		session.getTransaction().commit();
	}

	public void save(Object actual) {
		basics(actual, SAVE);
	}

	public void update(Object actual) {
		basics(actual, UPDATE);
	}

	public void delete(Object actual) {
		basics(actual, DELETE);
	}

	public <T> Object search(Object actual) throws Exception {
		Object value = null;
		for (Field field : actual.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String name = field.getName();
			if (!name.equals("id")) {
				continue;
			}
			value = field.get(actual);
			System.out.printf("%s: %s%n", name, value);
			break;
		}
		String name = actual.getClass().getSimpleName();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<T> query = session.createQuery("from " + name + " where id ='" + value + "'");
		return query.list().size() == 0 ? null : query.list().get(0);
	}

	public <T> List<T> consult(Object actual) {
		return consult(actual, null);
	}

	public <T> List<T> consult(Object actual, String order) {
		if (order == null) {
			order = "";
		}
		if (!order.equals("")) {
			order = " order by " + order + " desc";
		}
		String name = actual.getClass().getSimpleName();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<T> query = session.createQuery("from " + name + order);
		return query.list();
	}

}
