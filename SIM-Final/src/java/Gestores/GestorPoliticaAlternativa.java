/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Dominio.Cliente;
import Dominio.Empleado;
import Simulacion.VectorEstado;
import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public class GestorPoliticaAlternativa {

    private VectorEstado[] vectorEstado;
    private double tiempoActual, tiempoSimulacion;
    private ArrayList<VectorEstado> vectorMostrar;
    private int clientesTotal;
    private double promedio;
    private int cantidadClientesAtendidos;
    private double tiempoAcumulado;

    public GestorPoliticaAlternativa(double tiempoSimulacion) {
        vectorEstado = new VectorEstado[2];
        for (int i = 0; i < vectorEstado.length; i++) {
            vectorEstado[i] = new VectorEstado();
        }
        this.tiempoSimulacion = tiempoSimulacion;
        this.vectorMostrar = new ArrayList<>();

    }

    public VectorEstado[] getVectorEstado() {
        return vectorEstado;
    }

    public void setVectorEstado(VectorEstado[] vectorEstado) {
        this.vectorEstado = vectorEstado;
    }

    public double getTiempoActual() {
        return tiempoActual;
    }

    public void setTiempoActual(double tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

    public double getTiempoSimulacion() {
        return tiempoSimulacion;
    }

    public void setTiempoSimulacion(double tiempoSimulacion) {
        this.tiempoSimulacion = tiempoSimulacion;
    }

    public ArrayList<VectorEstado> getVectorMostrar() {
        return vectorMostrar;
    }

    public void setVectorMostrar(ArrayList<VectorEstado> vectorMostrar) {
        this.vectorMostrar = vectorMostrar;
    }

    public int getClientesTotal() {
        return clientesTotal;
    }

    public void setClientesTotal(int clientesTotal) {
        this.clientesTotal = clientesTotal;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public int getCantidadClientesAtendidos() {
        return cantidadClientesAtendidos;
    }

    public void setCantidadClientesAtendidos(int cantidadClientesAtendidos) {
        this.cantidadClientesAtendidos = cantidadClientesAtendidos;
    }

    public double getTiempoAcumulado() {
        return tiempoAcumulado;
    }

    public void setTiempoAcumulado(double tiempoAcumulado) {
        this.tiempoAcumulado = tiempoAcumulado;
    }

    public void simular() {
        tiempoActual = 0;
        VectorEstado vec = new VectorEstado();
        vec.lineaCero();
        vectorEstado[0] = vec;
        vectorMostrar.add(vectorEstado[0]);
        primeraLinea();
        for (int i = 2; i < tiempoSimulacion; i++) {
            siguienteLinea();
            if (tiempoSimulacion < tiempoActual) {
                break;
            }
        }
        clientesTotal = vectorEstado[1].getClientes().size();
        cantidadClientesAtendidos = vectorEstado[1].getClientesAtendidos();
        tiempoAcumulado = vectorEstado[1].getTiempoTotalEnBanco();
        promedio = tiempoAcumulado / cantidadClientesAtendidos;
    }

    private void siguienteLinea() {
        tiempoActual = vectorEstado[1].getMenorTiempoAlternativa();
        if (tiempoSimulacion < tiempoActual) {
            return;
        }
        VectorEstado vec = new VectorEstado();
        vec.setEventoActual(vectorEstado[1].getSiguienteEvento());
        vec.setTiempoActual(tiempoActual);
        copiar(vec, 1);
        if (vec.getEventoActual() == 1) {
            vec.llegadaCliente();
            vec.generarTipoAtencion();
            vec.asignarAEmpleadoAlternativa();
        }
        if (vec.getEventoActual() == 3) {
            int numEmpleado = vectorEstado[1].getNumEmp();
            vec.liberarEmpleado(numEmpleado);
            vec.setTiempoLlegadaProximoCliente(vectorEstado[1].getTiempoLlegadaProximoCliente());
        }

        addVector(vec);
        vectorMostrar.add(vec);

    }

    private void addVector(VectorEstado vec) {
        vectorEstado[0] = vectorEstado[1];
        vectorEstado[1] = vec;
    }

    private void primeraLinea() {
        tiempoActual = vectorEstado[0].getMenorTiempoAlternativa();
        VectorEstado vec = new VectorEstado();
        copiar(vec, 0);
        vec.setEventoActual(vectorEstado[0].getSiguienteEvento());
        vec.setTiempoActual(tiempoActual);
        vec.llegadaCliente();
        vec.generarTipoAtencion();
        vec.asignarAEmpleadoAlternativa();
        vectorEstado[1] = vec;
        vectorMostrar.add(vec);
    }

    private void copiar(VectorEstado vec, int pos) {
        //cola
        ArrayList colaAux = new ArrayList<>();
        for (int i = 0; i < vectorEstado[pos].getCola().size(); i++) {
            Cliente clienteAux = new Cliente();
            clienteAux.setEstado(vectorEstado[pos].getCola().get(i).getEstado());
            clienteAux.setSiendoAtendido(vectorEstado[pos].getCola().get(i).getSiendoAtendido());
            clienteAux.setTiempoLlegada(vectorEstado[pos].getCola().get(i).getTiempoLlegada());
            clienteAux.setTiempoLlenado(vectorEstado[pos].getCola().get(i).getTiempoLlenado());
            colaAux.add(clienteAux);
        }
        vec.setCola(colaAux);
        //tiempos fin de atencion
        double vectorAux[] = new double[3];
        System.arraycopy(vectorEstado[pos].getTiemposFinAtencion(), 0, vectorAux, 0, vectorEstado[pos].getTiemposFinAtencion().length);
        vec.setTiemposFinAtencion(vectorAux);

        //empleados
        Empleado vecAux[] = new Empleado[3];
        for (int i = 0; i < vectorEstado[pos].getEmpleados().length; i++) {
            Empleado aux = new Empleado();
            aux.setEstado(vectorEstado[pos].getEmpleados()[i].getEstado());
            aux.setTiempoRevision(vectorEstado[pos].getEmpleados()[i].getTiempoRevision());
            vecAux[i] = aux;
        }
        vec.setEmpleados(vecAux);

        //clientes
        ArrayList clientesAux = new ArrayList();
        for (int i = 0; i < vectorEstado[pos].getClientes().size(); i++) {
            Cliente cAux = new Cliente();
            cAux.setEstado(vectorEstado[pos].getClientes().get(i).getEstado());
            cAux.setSiendoAtendido(vectorEstado[pos].getClientes().get(i).getSiendoAtendido());
            cAux.setTiempoLlegada(vectorEstado[pos].getClientes().get(i).getTiempoLlegada());
            cAux.setTiempoLlenado(vectorEstado[pos].getClientes().get(i).getTiempoLlenado());
            clientesAux.add(cAux);
        }
        vec.setClientes(clientesAux);

        //datos estadisticos
        vec.setClientesAtendidos(vectorEstado[pos].getClientesAtendidos());
        vec.setTiempoTotalEnBanco(vectorEstado[pos].getTiempoTotalEnBanco());
    }

}
