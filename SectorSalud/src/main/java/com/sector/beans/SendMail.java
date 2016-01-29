/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.beans;

import com.sector.modelo.Formato;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mail.SesionMail;

/**
 *
 * @author ihsa
 */
public class SendMail {

    public static void enviarCorreoEnvioFormatoValidacion(Formato formato){
    
        String  correo = "Se envio el formato nuemero <strong>"                
                +formato.getId()+"</strong> para su validación al gerente "+formato.getUsuarioAprueba().getNombre()+" "+formato.getUsuarioAprueba().getApellidos()
                +"<br/>"
                +" Capturó :"+formato.getUsuarioGenero().getNombre()+" "+formato.getUsuarioGenero().getApellidos()
                +" <br/> Comentarios : "+ formato.getComentarios();

        //correo dirigido al usuario gerente de la gerencia 
        SesionMail sesionMail = new SesionMail();
        sesionMail.setPara("velocirraptor79@hotmail.com");
        sesionMail.setAsunto("Envio de formato No. "+formato.getId());
        sesionMail.setCc("arminbambu@gmail.com");
        sesionMail.setCuerpo(correo);
      
        try {
            sesionMail.enviarMensaje();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
