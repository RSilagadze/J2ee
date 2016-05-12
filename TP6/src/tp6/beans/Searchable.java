package tp6.beans;

import java.lang.reflect.Field;

import tp6.entities.MagazinEntity;

public interface Searchable <T extends MagazinEntity> {
	
	public EntityList <T> searchByField (Field field, Object value);

	public EntityList <T> searchByFieldName (String fieldName, Object value);
	
	public EntityList <T> fullSearchByField (Field field, Object value);

	public EntityList <T> fullSearchByFieldName (String fieldName, Object value);
}
