/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacion;

import Dominio.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Se encarga del llenado de datos y que no sean incorrectos, la logica la hace
 * el gestor
 *
 * @author jorge
 */
public class VectorEstado {

    private double tiempoActual, tiempoEntreLlegadaCliente, tiempoLlegadaProximoCliente, tiempoLlenado, tiempoAtencion;
    private double rndLlegadaCliente, rndFinLlenado, rndFinAtencion;
    private ArrayList<Double> tiemposFinLlenado;
    private double[] tiemposFinAtencion;
    private Empleado[] empleados;
    private Cliente cliente;
    private ArrayList<Cliente> cola;
    private ArrayList<Cliente> clientes;
    private int clientesAtendidos;
    private double tiempoTotalEnBanco;
    private int siguienteEvento;//1:llegada; 2:finllenado; 3:finAtencion
    private int eventoActual;
    private int numEmp;
    private int numCliente;

    public VectorEstado() {
        empleados = new Empleado[3];
        tiemposFinAtencion = new double[3];
        tiemposFinLlenado = new ArrayList<>();
        cola = new ArrayList<>();
        clientes = new ArrayList<>();

    }

    @Override
    public String toString() {
        return "VectorEstado{" + "tiempoActual=" + tiempoActual
                + ", tiempoEntreLlegada=" + tiempoEntreLlegadaCliente
                + ", tiempoLlegadaProximoCliente=" + tiempoLlegadaProximoCliente
                + ", tiemposFinLlenado=" + tiemposFinLlenado.toString()
                + ", tiemposFinAtencion=" + Arrays.toString(tiemposFinAtencion) + ", empleados="
                + Arrays.toString(empleados) + ", cliente=" + cliente + ", cola=" + cola
                + ", clientes=" + clientes.toString()
                + ", eventoActual=" + eventoActual + ", numEmp=" + numEmp
                + ", numCliente=" + numCliente + '}';
    }

    public double getTiempoActual() {
        return tiempoActual;
    }

    public void setTiempoActual(double tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

    public double getTiempoEntreLlegadaCliente() {
        return tiempoEntreLlegadaCliente;
    }

    public void setTiempoEntreLlegadaCliente(double tiempoEntreLlegadaCliente) {
        this.tiempoEntreLlegadaCliente = tiempoEntreLlegadaCliente;
    }

    public double getTiempoLlegadaProximoCliente() {
        return tiempoLlegadaProximoCliente;
    }

    public void setTiempoLlegadaProximoCliente(double tiempoLlegadaProximoCliente) {
        this.tiempoLlegadaProximoCliente = tiempoLlegadaProximoCliente;
    }

    public double getTiempoLlenado() {
        return tiempoLlenado;
    }

    public void setTiempoLlenado(double tiempoLlenado) {
        this.tiempoLlenado = tiempoLlenado;
    }

    public double getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(double tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public double getRndLlegadaCliente() {
        return rndLlegadaCliente;
    }

    public void setRndLlegadaCliente(double rndLlegadaCliente) {
        this.rndLlegadaCliente = rndLlegadaCliente;
    }

    public double getRndFinLlenado() {
        return rndFinLlenado;
    }

    public void setRndFinLlenado(double rndFinLlenado) {
        this.rndFinLlenado = rndFinLlenado;
    }

    public double getRndFinAtencion() {
        return rndFinAtencion;
    }

    public void setRndFinAtencion(double rndFinAtencion) {
        this.rndFinAtencion = rndFinAtencion;
    }

    public ArrayList<Double> getTiemposFinLlenado() {
        return tiemposFinLlenado;
    }

    public void setTiemposFinLlenado(ArrayList<Double> tiemposFinLlenado) {
        this.tiemposFinLlenado = tiemposFinLlenado;
    }

    public double[] getTiemposFinAtencion() {
        return tiemposFinAtencion;
    }

    public void setTiemposFinAtencion(double[] tiemposFinAtencion) {
        this.tiemposFinAtencion = tiemposFinAtencion;
    }

    public Empleado[] getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleado[] empleados) {
        this.empleados = empleados;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Cliente> getCola() {
        return cola;
    }

    public void setCola(ArrayList<Cliente> cola) {
        this.cola = cola;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    public void setClientesAtendidos(int clientesAtendidos) {
        this.clientesAtendidos = clientesAtendidos;
    }

    public double getTiempoTotalEnBanco() {
        return tiempoTotalEnBanco;
    }

    public void setTiempoTotalEnBanco(double tiempoTotalEnBanco) {
        this.tiempoTotalEnBanco = tiempoTotalEnBanco;
    }

    public int getSiguienteEvento() {
        return siguienteEvento;
    }

    public void setSiguienteEvento(int siguienteEvento) {
        this.siguienteEvento = siguienteEvento;
    }

    public int getEventoActual() {
        return eventoActual;
    }

    public void setEventoActual(int eventoActual) {
        this.eventoActual = eventoActual;
    }

    public int getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(int numEmp) {
        this.numEmp = numEmp;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public void lineaCero() {
        tiempoActual = 0d;
        generarLlegada();
        empleados = new Empleado[3];
        for (int i = 0; i < empleados.length; i++) {
            empleados[i] = new Empleado();
        }
        clientesAtendidos = 0;
        tiempoTotalEnBanco = 0d;
        eventoActual = 0;
        siguienteEvento = 1;
    }

    public double getMenorTiempo() {
        double menorAten = menorTiempoAtencion();
        double menorLLen = menorTiempoLlenado();
        if (menorAten == -1 && menorLLen == -1) {
            siguienteEvento = 1;
            return tiempoLlegadaProximoCliente;
        }
        if (menorAten != -1 && menorLLen == -1) {
            if (menorAten < tiempoLlegadaProximoCliente) {
                siguienteEvento = 3;
                return menorAten;
            } else {
                siguienteEvento = 1;
                return tiempoLlegadaProximoCliente;
            }
        }
        if (menorAten == -1 && menorLLen != -1) {
            if (menorLLen < tiempoLlegadaProximoCliente) {
                siguienteEvento = 2;
                return menorLLen;
            } else {
                siguienteEvento = 1;
                return tiempoLlegadaProximoCliente;
            }
        }
        if (menorLLen < tiempoLlegadaProximoCliente && menorLLen < menorAten) {
            siguienteEvento = 2;
            return menorLLen;
        }
        if (menorAten < tiempoLlegadaProximoCliente && menorLLen > menorAten) {
            siguienteEvento = 3;
            return menorAten;
        }
        siguienteEvento = 1;
        return tiempoLlegadaProximoCliente;
    }

    private double menorTiempoLlenado() {
        if (tiemposFinLlenado.isEmpty()) {
            return -1;
        } else {
//            double aux = Collections.min(tiemposFinLlenado);
//            numCliente = tiemposFinLlenado.lastIndexOf(aux) + clientesAtendidos;
//            return aux;
            double menorLlen = tiemposFinLlenado.get(0);
            numCliente = 0;
            for (int i = 1; i < tiemposFinLlenado.size(); i++) {
                double aux = tiemposFinLlenado.get(i);
                if (menorLlen != 0) {
                    if (aux != 0 && menorLlen > aux) {
                        menorLlen = aux;
                        numCliente = i;
                    }
                } else {
                    menorLlen = aux;
                    numCliente = i;
                }
            }
            if (menorLlen == 0) {
                numCliente = 0;
                return -1;
            }
            return menorLlen;
        }
    }

    private double menorTiempoAtencion() {
        double menorT = tiemposFinAtencion[0];
        numEmp = 0;
        for (int i = 1; i < tiemposFinAtencion.length; i++) {
            if (menorT == 0) {
                menorT = tiemposFinAtencion[i];
                numEmp = i;
            } else {
                if (tiemposFinAtencion[i] != 0 && menorT > tiemposFinAtencion[i]) {
                    menorT = tiemposFinAtencion[i];
                    numEmp = i;
                }
            }
        }
        if (menorT == 0) {
            numEmp = -1;
            return -1;
        }

        return menorT;
    }
    /*
     crea el cliente que llega en el tiempoActual
     y genera una nueva llegada
     */

    public void llegadaCliente() {
        cliente = new Cliente(tiempoActual);
        generarLlegada();
        clientes.add(cliente);
    }

    public void llenarFormulario() {
        rndFinLlenado = Math.random();
        cliente.llenarFormulario(rndFinLlenado);
        tiempoLlenado = cliente.getTiempoLlenado();

        tiemposFinLlenado.add(tiempoActual + tiempoLlenado);
    }

    private void generarLlegada() {
        do {
            rndLlegadaCliente = Math.random();
        } while (rndLlegadaCliente == 1);
        tiempoEntreLlegadaCliente = -12 * Math.log(1 - rndLlegadaCliente);
        tiempoLlegadaProximoCliente = tiempoEntreLlegadaCliente + tiempoActual;

    }

    //se realiza cuando un cliente finaliza el llenado del formulario
    //por ende, pregunta el estado de los empleados
    public void asignarAEmpleado(int numero) {
        tiemposFinLlenado.set(numero, 0d);
        if (cola.isEmpty()) {
            for (int i = 0; i < empleados.length; i++) {
                if (empleados[i].estaLibre()) {
                    clientes.get(numero).setSiendoAtendido(i);
                    clientes.get(numero).atender();
                    do {
                        rndFinAtencion = Math.random();
                    } while (rndFinAtencion == 0 || rndFinAtencion == 1);
                    empleados[i].revisarPoliticaActual(rndFinAtencion);
                    tiempoAtencion = empleados[i].getTiempoRevision();
                    tiemposFinAtencion[i] = tiempoActual + tiempoAtencion;

                    break;
                }

            }
            if (rndFinAtencion == 0) {
                clientes.get(numero).esperar();
                cola.add(clientes.get(numero));
            }

        } else {
            clientes.get(numero).esperar();
            cola.add(clientes.get(numero));
        }
    }

    public void liberarEmpleado(int numero) {
        for (int i = 0; i < clientes.size(); i++) {
            if (numero == clientes.get(i).getSiendoAtendido()) {
                numCliente = i;
                break;
            }
        }
        tiempoTotalEnBanco += (tiempoActual - clientes.get(numCliente).getTiempoLlegada());
        eliminarCliente(numero);
        clientesAtendidos++;
        if (cola.isEmpty()) {
            empleados[numero].liberar();
            tiemposFinAtencion[numero] = 0d;
        } else {
            System.out.println("Entre aca al else, es decir, hay cola");
            Cliente client = cola.remove(0);
            System.out.println("Cliente de la cola: " + client.toString());
            rndFinAtencion = Math.random();
            System.out.println("Empleado: " + numero);
            empleados[numero].revisarPoliticaActual(rndFinAtencion);
            System.out.println("Tiempo Revision: " + empleados[numero].getTiempoRevision());
            tiempoAtencion = empleados[numero].getTiempoRevision();
            tiemposFinAtencion[numero] = tiempoActual + tiempoAtencion;
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getTiempoLlegada() == client.getTiempoLlegada()) {
                    clientes.get(i).setSiendoAtendido(numero);
                    clientes.get(i).atender();
                }
            }

        }

    }

    private void eliminarCliente(int numero) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getSiendoAtendido() == numero) {
                clientes.get(i).matar();
                break;
            }
        }
    }
}
