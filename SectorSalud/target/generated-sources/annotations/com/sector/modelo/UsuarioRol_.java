package com.sector.modelo;

import com.sector.modelo.Rol;
import com.sector.modelo.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-18T10:58:25")
@StaticMetamodel(UsuarioRol.class)
public class UsuarioRol_ { 

    public static volatile SingularAttribute<UsuarioRol, Integer> id;
    public static volatile SingularAttribute<UsuarioRol, Usuario> usuario;
    public static volatile SingularAttribute<UsuarioRol, String> eliminado;
    public static volatile SingularAttribute<UsuarioRol, Rol> rol;

}