package com.sector.modelo;

import com.sector.modelo.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-30T09:59:30")
@StaticMetamodel(TipoUsuario.class)
public class TipoUsuario_ { 

    public static volatile SingularAttribute<TipoUsuario, Integer> id;
    public static volatile SingularAttribute<TipoUsuario, String> nombre;
    public static volatile CollectionAttribute<TipoUsuario, Usuario> usuarioCollection;

}