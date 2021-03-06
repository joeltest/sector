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
import com.sector.servicios.AdjuntoFacadeLocal;
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
public class FormatoBean implements Serializable {

    @ManagedProperty(value = "#{sesion}")
    private Sesion sesion;

    @EJB
    private FormatoFacadeLocal formatoService;
    @EJB
    private GerenciaFacadeLocal gerenciaService;
    @EJB
    private AdjuntoFacadeLocal adjuntoService;

    private List<Formato> listaFormato;
    private List<Formato> listaFormatosEnviados;
    private List<Formato> listaFormatoDevueltos;
    private Formato elementoSeleccionado;

    private List<SelectItem> listaGerenciaItems;

    private Integer idGerenciaSeleccionado;
    private Adjunto adjunto;

//    private StreamedContent file;
    private final String destination = "C:\\tmp\\";
////    private final String destination = "/local/";

    @PostConstruct
    public void init() {
        //traer tab pendientes
        cargarLista();
    }

    private void cargarLista() {
        setListaFormato(formatoService.obteberListaFormatos(EstatusEnum.CAPTURADO.getId(), getSesion().getUsuarioSesion().getId()));
        this.listaFormatoDevueltos = formatoService.obteberListaFormatos(EstatusEnum.DEVUELTO.getId(), sesion.getUsuarioSesion().getId());
        this.listaFormatosEnviados = formatoService.obteberListaFormatos(EstatusEnum.ENVIADO_PARA_VALIDACION.getId(), sesion.getUsuarioSesion().getId());
    }

    public void preprarNuevo(ActionEvent event) {
        System.out.println("Preprar nuevo registro");

        elementoSeleccionado = new Formato();

        //cargar gerencias
        cargarGerenciasCombo();

    }

    public void preparaAdjuntarArchivo(ActionEvent ev) {
        System.out.println("Preparar nuevo formato");
        int idFormato = Integer.parseInt(FacesUtils.getRequestParameter("idFormato"));
        System.out.println("id formato");
        this.elementoSeleccionado = formatoService.find(idFormato);
    }

    public void guardarAdjunto() {
        adjuntoService.create(adjunto);
        elementoSeleccionado.setAdjunto(adjunto);
        formatoService.edit(elementoSeleccionado);
        cargarLista();
    }

    public void enviar(ActionEvent e) {
        int idFormato = Integer.parseInt(FacesUtils.getRequestParameter("idFormato"));

        this.elementoSeleccionado = formatoService.find(idFormato);

        elementoSeleccionado.setEstatus(new Estatus(EstatusEnum.ENVIADO_PARA_VALIDACION.getId()));
        elementoSeleccionado.setFechaEnvio(new Date());
        elementoSeleccionado.setHoraEnvio(new Date());
        formatoService.edit(elementoSeleccionado);
        SendMail.enviarCorreoEnvioFormatoValidacion(elementoSeleccionado);
        cargarLista();
        //enviar por correo
        FacesUtils.addInfoMessage("Se envio el formato para su validación " + elementoSeleccionado.getGerenciaAprueba().getNombre() + " " + elementoSeleccionado.getUsuarioAprueba().getNombre());

    }

    public void eliminarRegistro(ActionEvent e) {

        int idFormato = Integer.parseInt(FacesUtils.getRequestParameter("idFormato"));

        this.elementoSeleccionado = formatoService.find(idFormato);
        if (elementoSeleccionado.getAdjunto() != null) {
            eliminarArchivo(e);
        }
        elementoSeleccionado.setEliminado("True");
        formatoService.remove(elementoSeleccionado);
        
        cargarLista();

        FacesUtils.addInfoMessage("Se elimino el formato... ");

    }

    public void guardarRegistroFormato(ActionEvent event) {

        Gerencia gerencia = gerenciaService.find(idGerenciaSeleccionado);
        elementoSeleccionado.setFechaGenero(new Date());
        elementoSeleccionado.setHoraGenero(new Date());
        elementoSeleccionado.setEliminado("False");
        elementoSeleccionado.setEstatus(new Estatus(EstatusEnum.CAPTURADO.getId()));
        elementoSeleccionado.setGerenciaAprueba(gerencia);
        elementoSeleccionado.setUsuarioAprueba(gerencia.getUsuarioGerente());
        elementoSeleccionado.setUsuarioGenero(sesion.getUsuarioSesion());
        formatoService.create(elementoSeleccionado);
        System.out.println("guardado");
        cargarLista();
        FacesUtils.addInfoMessage("Se registró exitosamente el formato...");
    }

    public void cargarGerenciasCombo() {
        List<Gerencia> lista = gerenciaService.findAll();

        listaGerenciaItems = new ArrayList<>();
        for (Gerencia u : lista) {
            listaGerenciaItems.add(new SelectItem(u.getId(), u.getNombre()));
        }
    }

//
    public void eliminarArchivo(ActionEvent event) {
        final int id = Integer.parseInt(FacesUtils.getRequestParameter("idFormato"));

        this.elementoSeleccionado = formatoService.find(id);

        System.out.println("eliminarArchivo " + elementoSeleccionado.getAdjunto().getRuta());
        Adjunto adjunto = elementoSeleccionado.getAdjunto();

        File fichero = new File(adjunto.getRuta());
        if (fichero.exists()) {
            if (fichero.delete()) {
                elementoSeleccionado.setAdjunto(null);
                adjuntoService.remove(adjunto);
                System.out.println("Se elimino sin probolemas ");
                FacesUtils.addInfoMessage("Se eliminó el archivo " + adjunto.getNombre());
            } else {
                System.out.println("Existió un error al tratar de eliminar el archivo.. por favor contacte al administrador.");
                FacesUtils.addInfoMessage("Existió un error al tratar de eliminar el archivo.. por favor contacte al administrador..");
            }
        } else {
            FacesUtils.addInfoMessage("No existe el archivo.. por favor contacte al administrador..");
        }

    }

    public String crearFolder() {
        System.out.println("crearFolder");
//        String nombreDirectorio = carpetaSeleccionada.getRuta()+folderDto.getNombre()+"\\";
        String rutaCompleta = destination + "\\" + elementoSeleccionado.getId();
        System.out.println("Nombre del nuevo directorio " + rutaCompleta);
        File directorio = new File(rutaCompleta);
        directorio.mkdir();
        return rutaCompleta;
    }
//

    public void uploadAttachment(FileUploadEvent event) {
        System.out.println("uploadAttachment para la carpeta");
        try {
            UploadedFile file = event.getFile();
            this.adjunto = new Adjunto();
            getAdjunto().setNombre(file.getFileName());
            getAdjunto().setTipoArchivo(file.getContentType());
//            adjunto.setPeso(String.valueOf(file.getSize()));
            getAdjunto().setEliminado("False");
            getAdjunto().setFechaGenero(new Date());
            getAdjunto().setHoraGenero(new Date());
            getAdjunto().setEsRepositorio("False");
            getAdjunto().setGenero(sesion.getUsuarioSesion());
            getAdjunto().setSistema("False");

            String rutaCarpeta = crearFolder();

            getAdjunto().setRuta(rutaCarpeta + "\\" + file.getFileName());
            copyFile(getAdjunto().getRuta(), event.getFile().getInputstream());

            FacesUtils.addInfoMessage("Se adjunto el formato...");
            System.out.println("Se copio");
        } catch (IOException ex) {
            System.out.println("Excepcion al subir archivo" + ex.getMessage());
        }

    }

    public void copyFile(String rutaCompleta, InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            //OutputStream out = new FileOutputStream(new File(parametroRaiz.getValor() + fileName));
            OutputStream out = new FileOutputStream(new File(rutaCompleta));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

//
//    /* Compartir con */
//    
//    
//    public void iniciarCompartirCon(ActionEvent event) {
//        System.out.println("Cargar " + elementoSeleccionado.getNombre());
//        cargarUsuariosCombo();
////        return "compartirArchivo.xhtml?faces-redirect=true";
//    }
//    
//    public void cargarUsuariosCombo() {
////        List<Usuario> lista = usuarioService.findAllUsuariosCompartir(elementoSeleccionado.getId(),sesion.getUsuarioSesion().getId());
////        listaUsuarioCompartirItems = new ArrayList<>();
////        for (Usuario u : lista) {
////            listaUsuarioCompartirItems.add(new SelectItem(u.getId(), u.getNombre()));
////        }
//       
//    }
//
////    public void buscarUsuariosCompartir(ActionEvent event){
////        System.out.println("Usuarioc compartir con");
////        this.listaUsuarioCompartir = usuarioService.findAllUsuarios(this.nombreUsuarioCompartir);
////        
////    }
////    
//    
//    
//    public void compartirElemento(ActionEvent event) {
//        System.out.println("Compartir con");
//        //Integer idUsuario = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuarioCompartir"));
//        System.out.println("Id usario " + this.getIdUsuarioCompartir());
//
//        compartirService.compartirCon(getIdUsuarioCompartir(),elementoSeleccionado.getId(), true, comentario);
//
//        cargarCompartidos();
//    }
//    
//    private void cargarCompartidos(){
//        this.listaUsuarioCompartidos = compartirService.findAllCompartidos(elementoSeleccionado.getId());
//    }
//
//    public List<SelectItem> getListaUsuarioCompartirItems() {
//        return this.listaUsuarioCompartirItems;
//    }
//
//    
//    public void quitarCompartir(Integer id){
//        ComparteCon compar = compartirService.find(id);
//        compar.setEliminado("True");
//        this.compartirService.edit(compar);
//        System.out.println("Se quito el compartido ");
//        cargarCompartidos();
//        FacesUtils.addInfoMessage("Se elimino sin problemas..");
//    }
//    
//    /**
//     * @return the sesion
//     */
//    public Sesion getSesion() {
//        return sesion;
//    }
//
//    public TreeNode getCarpetaNode() {
//        return carpetaNode;
//    }
//
//    public void setCarpetaNode(TreeNode carpetaNode) {
//        this.carpetaNode = carpetaNode;
//    }
//
//    /**
//     * @param sesion the sesion to set
//     */
//    public void setSesion(Sesion sesion) {
//        this.sesion = sesion;
//    }
//
//    public TreeNode getRoot() {
//        return root;
//    }
//
//    public void setRoot(TreeNode root) {
//        this.root = root;
//    }
//
//    public List<ElementoDto> getListaElemento() {
//        return listaElemento;
//    }
//
//    public void setListaElemento(List<ElementoDto> listaElemento) {
//        this.listaElemento = listaElemento;
//    }
//
//    public ElementoDto getElementoSeleccionado() {
//        return elementoSeleccionado;
//    }
//
//    public void setElementoSeleccionado(ElementoDto elementoSeleccionado) {
//        this.elementoSeleccionado = elementoSeleccionado;
//    }
//
//    public ElementoDto getCarpetaSeleccionada() {
//        return carpetaSeleccionada;
//    }
//
//    public void setCarpetaSeleccionada(ElementoDto carpetaSeleccionada) {
//        this.carpetaSeleccionada = carpetaSeleccionada;
//    }
//
//    public TreeNode getCarpetaActiva() {
//        return getCarpetaNode();
//    }
//
//    public void setCarpetaActiva(TreeNode selectedCarpetaNode) {
//        this.setCarpetaNode(selectedCarpetaNode);
//    }
//
//    public TreeNode getNodoSeleccionado() {
//        return elementoNode;
//    }
//
//    public void setNodoSeleccionado(TreeNode nodoSeleccionado) {
//        this.elementoNode = nodoSeleccionado;
//    }
//
//    public FolderDto getFolderDto() {
//        return folderDto;
//    }
//
//    public void setFolderDto(FolderDto folderDto) {
//        this.folderDto = folderDto;
//    }
//
//    public String getNombreFolder() {
//        return nombreFolder;
//    }
//
//    public void setNombreFolder(String nombreFolder) {
//        this.nombreFolder = nombreFolder;
//    }
//
//    public String getNombreUsuarioCompartir() {
//        return nombreUsuarioCompartir;
//    }
//
//    public void setNombreUsuarioCompartir(String nombreUsuarioCompartir) {
//        this.nombreUsuarioCompartir = nombreUsuarioCompartir;
//    }
//
//    public List<Usuario> getListaUsuarioCompartir() {
//        return listaUsuarioCompartir;
//    }
//
//    public void setListaUsuarioCompartir(List<Usuario> listaUsuarioCompartir) {
//        this.listaUsuarioCompartir = listaUsuarioCompartir;
//    }
//
//    public String getComentario() {
//        return comentario;
//    }
//
//    public void setComentario(String comentario) {
//        this.comentario = comentario;
//    }
//
//    public int getIdUsuarioCompartir() {
//        return idUsuarioCompartir;
//    }
//
//    public void setIdUsuarioCompartir(int idUsuarioCompartir) {
//        this.idUsuarioCompartir = idUsuarioCompartir;
//    }
//
//    public List<ComparteCon> getListaUsuarioCompartidos() {
//        return listaUsuarioCompartidos;
//    }
//
//    public void setListaUsuarioCompartidos(List<ComparteCon> listaUsuarioCompartidos) {
//        this.listaUsuarioCompartidos = listaUsuarioCompartidos;
//    }
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

    public Adjunto getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(Adjunto adjunto) {
        this.adjunto = adjunto;
    }

    public List<Formato> getListaFormatosEnviados() {
        return listaFormatosEnviados;
    }

    public void setListaFormatosEnviados(List<Formato> listaFormatosEnviados) {
        this.listaFormatosEnviados = listaFormatosEnviados;
    }

    public List<Formato> getListaFormatoDevueltos() {
        return listaFormatoDevueltos;
    }

    public void setListaFormatoDevueltos(List<Formato> listaFormatoDevueltos) {
        this.listaFormatoDevueltos = listaFormatoDevueltos;
    }

}
