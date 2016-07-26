/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author jorge
 */
public class Cliente {

    private int siendoAtendido;//-1: no esta siendo atendido; 0,1,2: es el empleado
    private String estado;
    private double tiempoLlenado;
    private double tiempoLlegada;

    public Cliente(double tiempoLlegada) {
        this.siendoAtendido = -1;
        this.estado = "LLENANDO FORMULARIO";
        this.tiempoLlegada = tiempoLlegada;
    }

    public Cliente() {
    }

    
    

    @Override
    public String toString() {
        return "Cliente{" + "siendoAtendido=" + siendoAtendido + ", estado=" + estado + ", tiempoLlegada=" + tiempoLlegada + '}';
    }
    

    public double getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(double tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getSiendoAtendido() {
        return siendoAtendido;
    }

    public void setSiendoAtendido(int siendoAtendido) {
        //this.estado = "SIENDO ATENDIDO";
        this.siendoAtendido = siendoAtendido;
    }

    public double getTiempoLlenado() {
        return tiempoLlenado;
    }

    public void setTiempoLlenado(double tiempoLlenado) {
        this.tiempoLlenado = tiempoLlenado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void llenarFormulario(double rnd) {
        this.tiempoLlenado = -39 * Math.log(1 - rnd);
    }
    
    public void esperar(){
        this.estado = "ESPERANDO ATENCION";
    }
    public void atender(){
        this.estado="SIENDO ATENDIDO";
    }
    public void matar(){
        this.estado="MUERTO";
        this.siendoAtendido=67;//muerto
        this.tiempoLlegada=-1;
        this.tiempoLlenado=-1;
    }
}
