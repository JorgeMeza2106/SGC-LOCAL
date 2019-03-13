package pe.com.fisi.cenpro.sigeco.mgc.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public interface ParamConfigService {

	static List<LocalDate> getLsFeriados() {
		return Arrays.asList(
				LocalDate.of(2017, 8, 30),
				LocalDate.of(2017, 10, 8),
				LocalDate.of(2017, 11, 1),
				LocalDate.of(2017, 12, 8),
				LocalDate.of(2017, 11, 25)
			);
	}
}
