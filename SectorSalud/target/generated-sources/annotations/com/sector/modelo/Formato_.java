package com.sector.modelo;

import com.sector.modelo.Adjunto;
import com.sector.modelo.Estatus;
import com.sector.modelo.Gerencia;
import com.sector.modelo.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-28T20:45:22")
@StaticMetamodel(Formato.class)
public class Formato_ { 

    public static volatile SingularAttribute<Formato, String> motivoCancelacion;
    public static volatile SingularAttribute<Formato, String> motivoDevuelto;
    public static volatile SingularAttribute<Formato, Usuario> usuarioGenero;
    public static volatile SingularAttribute<Formato, String> eliminado;
    public static volatile SingularAttribute<Formato, String> comentarios;
    public static volatile SingularAttribute<Formato, Date> horaGenero;
    public static volatile SingularAttribute<Formato, Gerencia> gerenciaAprueba;
    public static volatile SingularAttribute<Formato, String> enviadoAprobacion;
    public static volatile SingularAttribute<Formato, Integer> id;
    public static volatile SingularAttribute<Formato, Date> horaEnvio;
    public static volatile SingularAttribute<Formato, Date> fechaGenero;
    public static volatile SingularAttribute<Formato, Date> fechaEnvio;
    public static volatile SingularAttribute<Formato, Usuario> usuarioAprueba;
    public static volatile SingularAttribute<Formato, Adjunto> adjunto;
    public static volatile SingularAttribute<Formato, String> horaCancelacion;
    public static volatile SingularAttribute<Formato, String> fechaCancelacion;
    public static volatile SingularAttribute<Formato, Estatus> estatus;

}