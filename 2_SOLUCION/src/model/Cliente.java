package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que representa un cliente.
 */
public class Cliente {
    private String nombres;
    private String pasaporteCedula;
    private String ciudad;
    private String marca;
    private String modelo;
    private String numeroCelular;
    private double pagoMensual;
    private String email;
    private String direccion;
    private List<Integer> planesIds;

    public Cliente(String nombres, String pasaporteCedula, String ciudad, String marca, String modelo, String numeroCelular, double pagoMensual, String email, String direccion, List<Integer> planesIds) {
        this.nombres = nombres;
        this.pasaporteCedula = pasaporteCedula;
        this.ciudad = ciudad;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroCelular = numeroCelular;
        this.pagoMensual = pagoMensual;
        this.email = email;
        this.direccion = direccion;
        this.planesIds = planesIds;
    }

    // Métodos getter y setter

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPasaporteCedula() {
        return pasaporteCedula;
    }

    public void setPasaporteCedula(String pasaporteCedula) {
        this.pasaporteCedula = pasaporteCedula;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Integer> getPlanesIds() {
        return planesIds;
    }

    public void setPlanesIds(List<Integer> planesIds) {
        this.planesIds = planesIds;
    }
    
    /**
     * Agrega un ID de plan a la lista del cliente, con un máximo de 2 planes.
     * @param planId ID del plan a agregar.
     */
    public void agregarPlanId(int planId) {
        if (this.planesIds.size() < 2) {
            this.planesIds.add(planId);
        } else {
            throw new IllegalStateException("El cliente no puede tener más de 2 planes.");
        }
    }

    /**
     * Convierte la lista de IDs de planes a una cadena separada por comas.
     * @return Cadena con los IDs de planes.
     */
    public String planesIdsToString() {
        return String.join(",", planesIds.stream().map(String::valueOf).toArray(String[]::new));
    }

    /**
     * Convierte una cadena separada por comas a una lista de IDs de planes.
     * @param planesIdsStr Cadena con los IDs de planes.
     * @return Lista de IDs de planes.
     */
    public static List<Integer> stringToPlanesIds(String planesIdsStr) {
        if (planesIdsStr == null || planesIdsStr.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(planesIdsStr.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}
