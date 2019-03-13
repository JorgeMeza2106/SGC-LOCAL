package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.List;

public interface Transformer<E, BO, F> {
	
	public  E transformToE(F f);
	public  BO transformToBO(E e);
	public  List<E> transformToE(List<BO> lbos);
	public  List<BO> transformToBO(List<E> lens);
	
}

