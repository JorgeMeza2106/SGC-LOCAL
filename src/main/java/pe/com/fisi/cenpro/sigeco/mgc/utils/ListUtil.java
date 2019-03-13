package pe.com.fisi.cenpro.sigeco.mgc.utils;

import java.util.List;

public class ListUtil {

	public static <T> T getLast(List<T> list){
		return (list != null && !list.isEmpty()) ? list.get(list.size()-1) : null;
	}
}
