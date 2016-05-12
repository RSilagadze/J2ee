package tp6.beans;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

import tp6.entities.Article;
import tp6.entities.Client;
import tp6.entities.MagazinEntity;

public class EntityList <T extends MagazinEntity> extends ArrayList<T> 
							implements List<T>, RandomAccess, Cloneable, java.io.Serializable, Searchable<T>
{
	
	private static final long serialVersionUID = 1L;
	
	public EntityList () {
		super ();
	}
	
	public EntityList (boolean isPreFilled, Class <T> cls) {
		super ();
		if (isPreFilled)
			generateData (cls);
	}
	
	private T getWrapper (){
		if (this != null){
			if (this.size() > 0){
				return this.get(0);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void generateData (Class <T> cls) {
			if (cls == Article.class) {
				this.add((T) new Article (1,10,100,"Velo"));
				this.add((T) new Article (2,50, 25,"Razoir"));
				this.add((T) new Article (3,30,400,"PS4"));
				this.add((T) new Article (4,20, 50,"Trottinette"));
				this.add((T) new Article (5,15,120,"Skateboard"));
			}
			if (cls == Client.class) {
				this.add((T) new Client (1, "Toto", "Machin", "09/01/2016","login1","mdp1"));
				this.add((T) new Client (2, "Agueh", "Max", "10/01/2016","login2","mdp2"));
				this.add((T) new Client (3, "Laurence", "Patrick", "15/01/2016","login3","mdp3"));
				this.add((T) new Client (4, "Serrano", "Sandrine", "18/01/2016","login4","mdp4"));
				this.add((T) new Client (5, "Tabis", "Gisleine", "17/01/2016","login5","mdp5"));
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntityList <T> searchByField(Field field, Object value) {
			EntityList <T> lstTemp = new EntityList <T>();
			T wrapper = getWrapper();
			if (wrapper != null) {
				searchField((Class <T>)wrapper.getClass(),field, null, value, lstTemp);
			}
			return lstTemp;
	}
	
	private void searchField(Class <T> cls, Field searchField, String fieldName,
										Object value, List <T> tempList) {
		Field foundField = null;
		try {
			if (fieldName != null)
				foundField = cls.getDeclaredField(fieldName);
			if (searchField != null)
				foundField = cls.getDeclaredField(searchField.getName());
			
			foundField.setAccessible(true);
			for (T obj : this){
				if (foundField.get(obj).equals(value)){
					tempList.add(obj);
				}
			}
			}catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e ){
				e.getStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntityList<T> searchByFieldName(String fieldName, Object value) {
		EntityList <T> lstTemp = new EntityList <T>();
		T wrapper = getWrapper();
		if (wrapper != null) {
			searchField((Class <T>)wrapper.getClass(), null, fieldName, value, lstTemp);
		}
		return lstTemp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EntityList <T> fullSearchByField(Field field, Object value) {
		EntityList <T> lstTemp = new EntityList <T>();
		T wrapper = getWrapper();
		if (wrapper != null) {
			fullFieldSearch((Class<T>) wrapper.getClass(), null, field, value, lstTemp);
			if (wrapper.getClass().isAssignableFrom(wrapper.getClass())) {
				fullFieldSearch((Class<T>) wrapper.getClass(), 
											null, field, value, lstTemp);
				if (lstTemp.size() == 0){
					fullFieldSearch((Class<T>) wrapper.getClass().getSuperclass(), 
											null, field, value, lstTemp);
				}
			}
			else {
				fullFieldSearch((Class<T>) wrapper.getClass(), 
											null, field, value, lstTemp);
			}
		}
		return lstTemp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntityList <T> fullSearchByFieldName(String fieldName, Object value) {
		EntityList <T> lstTemp = new EntityList <T>();
		T wrapper = getWrapper();
		if (wrapper != null) {
			fullFieldSearch((Class<T>) wrapper.getClass(), fieldName, null, value, lstTemp);
			if (wrapper.getClass().isAssignableFrom(wrapper.getClass())) {
				fullFieldSearch((Class<T>) wrapper.getClass(),
											fieldName, null, value, lstTemp);	
				if (lstTemp.size() == 0){
					fullFieldSearch((Class<T>) wrapper.getClass().getSuperclass(),
											fieldName, null, value, lstTemp);
				}
									
			}
			else {
					fullFieldSearch((Class<T>) wrapper.getClass(),
											fieldName, null, value, lstTemp);	
			}
		}
		return lstTemp;
	}
	
	private void fullFieldSearch (Class <T> cls, String fieldName, Field searchField,
										Object value, List <T> lstTemp) {
		Field[] fields = cls.getDeclaredFields();
		boolean fFound = false;
		Field foundField = null;
		for (Field f : fields) {
			if(fieldName != null){
				if (f.getName().equals(fieldName)){
					fFound = true;
				}
			}
			if (searchField != null){
				if(f.equals(searchField)){
					fFound = true;
				}
			}
			if (fFound){
				foundField = f;
				break;
			}
		}
		if (fFound){
			try{
				foundField.setAccessible(true);
				for (T obj : this){
					if (foundField.get(obj).equals(value)){
						lstTemp.add(obj);
					}		
				}
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
