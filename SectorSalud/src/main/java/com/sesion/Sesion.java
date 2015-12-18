package com.sesion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sector.modelo.Usuario;
import com.sector.servicios.UsuarioFacadeLocal;
import com.sector.utils.MensajeUtils;
import com.sector.utils.UtilsRedirect;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

/**
 *
 * @author jorodriguez
 */
@Named(value = "sesion")
@SessionScoped
public class Sesion implements Serializable {
    @EJB
    private UsuarioFacadeLocal usuarioService;
           
    private Usuario usuarioSesion;
    private String usuarioTemp;
    private String claveTemp;
    
    public Sesion() {
        
    }
    @PostConstruct
    public void init(){
//        usuarioSesion = usuarioFacadeLocal.find(1);
        
    }
    
    public void login(ActionEvent event){
        this.setUsuarioSesion(usuarioService.login(usuarioTemp, claveTemp, 1));
        if(getUsuarioSesion() == null){
              MensajeUtils.addErrorMessage("Acceso denegado", "el usuario o la clave son incorrectos...");
        }else{
              System.out.println(" usuario "+usuarioSesion.toString());
              MensajeUtils.addInfoMessage("Bienvenido", "...");
        }
    }
    
   public void redireccionarPrincipal(ActionEvent event){
        UtilsRedirect.redireccionarContexto("/SArchivo");
   }
   

    /**
     * @return the usuarioSesion
     */
    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    /**
     * @param usuarioSesion the usuarioSesion to set
     */
    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }
    
     public void cerrarSesion(ActionEvent event) {
        this.setUsuarioSesion(null);
        this.claveTemp="";
        this.usuarioTemp = "";
//        Util.redireccionarContexto("/Restaurant-web");
    }

    /**
     * @return the usuarioTemp
     */
    public String getUsuarioTemp() {
        return usuarioTemp;
    }

    /**
     * @param usuarioTemp the usuarioTemp to set
     */
    public void setUsuarioTemp(String usuarioTemp) {
        this.usuarioTemp = usuarioTemp;
    }

    /**
     * @return the claveTemp
     */
    public String getClaveTemp() {
        return claveTemp;
    }

    /**
     * @param claveTemp the claveTemp to set
     */
    public void setClaveTemp(String claveTemp) {
        this.claveTemp = claveTemp;
    }
}
