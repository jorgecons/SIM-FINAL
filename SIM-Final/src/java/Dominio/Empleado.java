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
public class Empleado {

    private double tiempoRevision;
    private String estado;

    public Empleado() {
        this.estado = "LIBRE";
    }

    @Override
    public String toString() {
        return "Empleado{" + "estado=" + estado + '}';
    }
    

    public double getTiempoRevision() {
        return tiempoRevision;
    }

    public void setTiempoRevision(double tiempoRevision) {
        this.tiempoRevision = tiempoRevision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean estaLibre() {
        return estado.equals("LIBRE");
    }

    public void liberar() {
        this.estado = "LIBRE";
    }

    public void ocupar() {
        this.estado = "OCUPADO";
    }

    public void revisarPoliticaActual(double rnd) {
        this.tiempoRevision = 5 + rnd * 6;
        ocupar();
    }

    public void revisarPoliticaAlternativa(double rnd) {
        if (rnd < 0.7) {
            this.tiempoRevision = 9 + rnd * 6;
        } else {
            this.tiempoRevision = -12 * Math.log(1 - rnd);
        }
        ocupar();
    }
}
