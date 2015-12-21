package com.sector.modelo;

import com.sector.modelo.Formato;
import com.sector.modelo.Gerencia;
import com.sector.modelo.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-20T21:09:51")
@StaticMetamodel(Adjunto.class)
public class Adjunto_ { 

    public static volatile SingularAttribute<Adjunto, String> sistema;
    public static volatile SingularAttribute<Adjunto, String> extension;
    public static volatile SingularAttribute<Adjunto, String> nombre;
    public static volatile SingularAttribute<Adjunto, Integer> id;
    public static volatile SingularAttribute<Adjunto, Date> fechaGenero;
    public static volatile SingularAttribute<Adjunto, Usuario> genero;
    public static volatile SingularAttribute<Adjunto, String> eliminado;
    public static volatile SingularAttribute<Adjunto, String> esRepositorio;
    public static volatile SingularAttribute<Adjunto, String> tipoArchivo;
    public static volatile SingularAttribute<Adjunto, String> ruta;
    public static volatile SingularAttribute<Adjunto, Date> horaGenero;
    public static volatile SingularAttribute<Adjunto, Gerencia> gerencia;
    public static volatile CollectionAttribute<Adjunto, Formato> formatoCollection;

}