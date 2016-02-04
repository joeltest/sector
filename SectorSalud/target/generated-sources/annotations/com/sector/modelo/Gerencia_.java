package com.sector.modelo;

import com.sector.modelo.Adjunto;
import com.sector.modelo.Formato;
import com.sector.modelo.Sucursal;
import com.sector.modelo.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-30T09:59:30")
@StaticMetamodel(Gerencia.class)
public class Gerencia_ { 

    public static volatile SingularAttribute<Gerencia, Integer> id;
    public static volatile SingularAttribute<Gerencia, String> nombre;
    public static volatile SingularAttribute<Gerencia, Sucursal> sucursal;
    public static volatile CollectionAttribute<Gerencia, Adjunto> adjuntoCollection;
    public static volatile SingularAttribute<Gerencia, String> eliminado;
    public static volatile SingularAttribute<Gerencia, Usuario> usuarioGerente;
    public static volatile CollectionAttribute<Gerencia, Formato> formatoCollection;

}