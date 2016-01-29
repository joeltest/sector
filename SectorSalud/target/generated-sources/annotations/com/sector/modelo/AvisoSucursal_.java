package com.sector.modelo;

import com.sector.modelo.Sucursal;
import com.sector.modelo.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-28T21:01:02")
@StaticMetamodel(AvisoSucursal.class)
public class AvisoSucursal_ { 

    public static volatile SingularAttribute<AvisoSucursal, Integer> id;
    public static volatile SingularAttribute<AvisoSucursal, Sucursal> sucursal;
    public static volatile SingularAttribute<AvisoSucursal, Date> hora;
    public static volatile SingularAttribute<AvisoSucursal, String> titulo;
    public static volatile SingularAttribute<AvisoSucursal, Date> fecha;
    public static volatile SingularAttribute<AvisoSucursal, Usuario> usuario;
    public static volatile SingularAttribute<AvisoSucursal, String> eliminado;
    public static volatile SingularAttribute<AvisoSucursal, String> descripcion;

}