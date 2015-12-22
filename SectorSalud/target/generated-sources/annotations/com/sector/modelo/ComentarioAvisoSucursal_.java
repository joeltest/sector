package com.sector.modelo;

import com.sector.modelo.AvisoSucursal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-21T18:30:49")
@StaticMetamodel(ComentarioAvisoSucursal.class)
public class ComentarioAvisoSucursal_ { 

    public static volatile SingularAttribute<ComentarioAvisoSucursal, Integer> id;
    public static volatile SingularAttribute<ComentarioAvisoSucursal, Date> hora;
    public static volatile SingularAttribute<ComentarioAvisoSucursal, String> titulo;
    public static volatile SingularAttribute<ComentarioAvisoSucursal, Date> fecha;
    public static volatile SingularAttribute<ComentarioAvisoSucursal, String> eliminado;
    public static volatile SingularAttribute<ComentarioAvisoSucursal, String> comentario;
    public static volatile SingularAttribute<ComentarioAvisoSucursal, AvisoSucursal> avisoSucursal;

}