package com.sector.modelo;

import com.sector.modelo.UsuarioRol;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-28T21:01:02")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, Integer> id;
    public static volatile SingularAttribute<Rol, String> nombre;
    public static volatile SingularAttribute<Rol, String> eliminado;
    public static volatile CollectionAttribute<Rol, UsuarioRol> usuarioRolCollection;

}