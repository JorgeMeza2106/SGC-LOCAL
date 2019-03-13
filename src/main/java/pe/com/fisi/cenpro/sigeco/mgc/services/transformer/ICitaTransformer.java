package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.CitaForm;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Cita;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaBO;

public interface ICitaTransformer extends Transformer<Cita, CitaBO, CitaForm>, TransformerArrayObjects<CitaBO> {

}
