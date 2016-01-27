/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.beans;

import com.sector.modelo.Adjunto;
import com.sector.modelo.Estatus;
import com.sector.modelo.Formato;
import com.sector.modelo.Gerencia;
import com.sector.servicios.FormatoFacadeLocal;
import com.sector.servicios.GerenciaFacadeLocal;
import com.sector.utils.EstatusEnum;
import com.sector.utils.FacesUtils;
import com.sesion.Sesion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class FormatoValidacionBean implements Serializable {

    @ManagedProperty(value = "#{sesion}")
    private Sesion sesion;

    @EJB
    private FormatoFacadeLocal formatoService;
    @EJB
    private GerenciaFacadeLocal gerenciaService;

    private List<Formato> listaFormato;
    private Formato elementoSeleccionado;

    private List<SelectItem> listaGerenciaItems;

    private Integer idGerenciaSeleccionado;
    private Adjunto adjunto;

    private List<Formato> listaFormatoEntrada;
    private List<Formato> listaFormatoAceptados;
    private List<Formato> listaFormatoDevueltos;
    private List<Formato> listaFormatoHistorial;

//    private StreamedContent file;
    private final String destination = "C:\\tmp\\";
////    private final String destination = "/local/";

    @PostConstruct
    public void init() {
        //traer tab pendientes
        cargarListasBandejaGerente();
    }

    private void cargarListasBandejaGerente() {
     this.listaFormatoEntrada = formatoService.obtenerFormatosParaValidacion(EstatusEnum.ENVIADO_PARA_VALIDACION.getId(), sesion.getUsuarioSesion().getId());
     this.listaFormatoAceptados = formatoService.obtenerFormatosParaValidacion(EstatusEnum.ACEPTADO.getId(), sesion.getUsuarioSesion().getId());
     this.listaFormatoDevueltos = formatoService.obtenerFormatosParaValidacion(EstatusEnum.DEVUELTO.getId(), sesion.getUsuarioSesion().getId());
     
    }

    public void preprarNuevo(ActionEvent event) {
        System.out.println("Preprar nuevo registro");

        elementoSeleccionado = new Formato();

        //cargar gerencias
        cargarGerenciasCombo();

    }

    public void cargarGerenciasCombo() {
        List<Gerencia> lista = getGerenciaService().findAll();

        listaGerenciaItems = new ArrayList<>();
        for (Gerencia u : lista) {
            getListaGerenciaItems().add(new SelectItem(u.getId(), u.getNombre()));
        }
    }


    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public List<Formato> getListaFormato() {
        return listaFormato;
    }

    public void setListaFormato(List<Formato> listaFormato) {
        this.listaFormato = listaFormato;
    }

    public Formato getElementoSeleccionado() {
        return elementoSeleccionado;
    }

    public void setElementoSeleccionado(Formato elementoSeleccionado) {
        this.elementoSeleccionado = elementoSeleccionado;
    }

    public List<SelectItem> getListaGerenciaItems() {
        return listaGerenciaItems;
    }

    public void setListaGerenciaItems(List<SelectItem> listaGerenciaItems) {
        this.listaGerenciaItems = listaGerenciaItems;
    }

    public Integer getIdGerenciaSeleccionado() {
        return idGerenciaSeleccionado;
    }

    public void setIdGerenciaSeleccionado(Integer idGerenciaSeleccionado) {
        this.idGerenciaSeleccionado = idGerenciaSeleccionado;
    }

    public FormatoFacadeLocal getFormatoService() {
        return formatoService;
    }

    public GerenciaFacadeLocal getGerenciaService() {
        return gerenciaService;
    }

    public Adjunto getAdjunto() {
        return adjunto;
    }

    public List<Formato> getListaFormatoEntrada() {
        return listaFormatoEntrada;
    }

    public List<Formato> getListaFormatoAceptados() {
        return listaFormatoAceptados;
    }

    public List<Formato> getListaFormatoDevueltos() {
        return listaFormatoDevueltos;
    }

    public List<Formato> getListaFormatoHistorial() {
        return listaFormatoHistorial;
    }

    public String getDestination() {
        return destination;
    }

}
