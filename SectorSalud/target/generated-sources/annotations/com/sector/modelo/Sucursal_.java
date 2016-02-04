package com.sector.modelo;

import com.sector.modelo.Gerencia;
import com.sector.modelo.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-30T09:59:30")
@StaticMetamodel(Sucursal.class)
public class Sucursal_ { 

    public static volatile SingularAttribute<Sucursal, Integer> id;
    public static volatile SingularAttribute<Sucursal, String> nombre;
    public static volatile SingularAttribute<Sucursal, String> ciudad;
    public static volatile SingularAttribute<Sucursal, String> numeroTelefono;
    public static volatile CollectionAttribute<Sucursal, Gerencia> gerenciaCollection;
    public static volatile SingularAttribute<Sucursal, String> estado;
    public static volatile CollectionAttribute<Sucursal, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Sucursal, String> eliminado;
    public static volatile SingularAttribute<Sucursal, String> encargado;
    public static volatile SingularAttribute<Sucursal, String> calle;
    public static volatile SingularAttribute<Sucursal, String> colonia;

}