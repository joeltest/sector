package com.sector.modelo;

import com.sector.modelo.Adjunto;
import com.sector.modelo.Gerencia;
import com.sector.modelo.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-28T20:45:22")
@StaticMetamodel(GerenciaFormato.class)
public class GerenciaFormato_ { 

    public static volatile SingularAttribute<GerenciaFormato, Integer> id;
    public static volatile SingularAttribute<GerenciaFormato, Usuario> genero;
    public static volatile SingularAttribute<GerenciaFormato, Date> fechaGenero;
    public static volatile SingularAttribute<GerenciaFormato, Adjunto> adjunto;
    public static volatile SingularAttribute<GerenciaFormato, String> eliminado;
    public static volatile SingularAttribute<GerenciaFormato, String> descripcion;
    public static volatile SingularAttribute<GerenciaFormato, Gerencia> gerencia;

}