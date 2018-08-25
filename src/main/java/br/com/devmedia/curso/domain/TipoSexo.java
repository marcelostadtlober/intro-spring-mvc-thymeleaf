package br.com.devmedia.curso.domain;

/**
 * Created by Ballem on 13/06/2017.
 */
public enum TipoSexo {

    FEMININO('F'), MASCULINO('M');

    private char desc;

    TipoSexo(char desc) {
        this.desc = desc;
    }

    public char getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
