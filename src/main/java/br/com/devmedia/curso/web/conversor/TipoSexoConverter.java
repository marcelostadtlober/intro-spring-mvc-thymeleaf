package br.com.devmedia.curso.web.conversor;

import br.com.devmedia.curso.domain.TipoSexo;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Ballem on 13/06/2017.
 */
public class TipoSexoConverter implements Converter<String, TipoSexo> {

    @Override
    public TipoSexo convert(String value) {
        char tipo = value.charAt(0);
        return tipo == TipoSexo.FEMININO.getDesc() ? TipoSexo.FEMININO : TipoSexo.MASCULINO;
    }
}
