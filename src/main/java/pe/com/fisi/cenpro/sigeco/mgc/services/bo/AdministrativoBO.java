package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Builder
@Value
@FieldDefaults(level=AccessLevel.PRIVATE)
public class AdministrativoBO {
	 UsuarioBO usuarioBO;
	
}
