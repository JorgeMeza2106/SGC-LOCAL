package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.List;

public interface TransformerArrayObjects<BO> {

	public  List<BO> transformArrayToBO(List<Object[]> listObjects);
	
}
