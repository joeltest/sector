package com.sector.modelo;

import com.sector.modelo.Adjunto;
import com.sector.modelo.Formato;
import com.sector.modelo.Gerencia;
import com.sector.modelo.Sucursal;
import com.sector.modelo.TipoUsuario;
import com.sector.modelo.UsuarioRol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-28T21:01:02")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Sucursal> sucursal;
    public static volatile CollectionAttribute<Usuario, Adjunto> adjuntoCollection;
    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile SingularAttribute<Usuario, String> eliminado;
    public static volatile CollectionAttribute<Usuario, UsuarioRol> usuarioRolCollection;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Date> fechaGenero;
    public static volatile CollectionAttribute<Usuario, Gerencia> gerenciaCollection;
    public static volatile SingularAttribute<Usuario, TipoUsuario> tipoUsuario;
    public static volatile CollectionAttribute<Usuario, Formato> formatoCollection1;
    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile SingularAttribute<Usuario, String> correo;
    public static volatile CollectionAttribute<Usuario, Formato> formatoCollection;

}