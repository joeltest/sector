/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.utils;

/**
 *
 * @author jorodriguez
 */
public enum EstatusEnum {

    
    CAPTURADO(1),    
    ENVIADO_PARA_VALIDACION(2),
    ACEPTADO(3),
    DEVUELTO(4),    
    CANCELADO(5);
    
    
    private final int id;

    private EstatusEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
