package pe.com.fisi.cenpro.sigeco.mgc.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface GenericService<Entity, K extends Serializable> {
	
	public void saveOrUpdate(Entity entity);

	public void save(Entity entity);
	
	public Entity read(K id);

	public void update(Entity entity);

	public void delete(Entity entity);
	
	public List<Entity> findAll();

    public List<Entity> findByParams(HashMap<String, Object> paramas, boolean and);
}