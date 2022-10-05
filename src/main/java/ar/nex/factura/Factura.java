package ar.nex.factura;

import java.util.Objects;

/**
 *
 * @author Renzo
 */
public class Factura {

    private Long idFactura;

    private String denominacion;

    private String cuit;

    private String fecha;

    private String tipo;

    private String letra;

    private Integer puntoComprobante;

    private Integer numeroComprobante;

    private double netoGravado;

    private double netoNoGravado;

    private double otroX;

    private double IVA;

    private double total;

    private String origen;

    private Integer codigo;
    
    public Factura() {
    }

    public Factura(String Origen) {
        this.origen = Origen;
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String Denominacion) {
        this.denominacion = Denominacion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Integer getPuntoComprobante() {
        return puntoComprobante;
    }

    public void setPuntoComprobante(Integer puntoComprobante) {
        this.puntoComprobante = puntoComprobante;
    }

    public Integer getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(Integer numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public double getNetoGravado() {
        return netoGravado;
    }

    public void setNetoGravado(double netoGravado) {
        this.netoGravado = netoGravado;
    }

    public double getNetoNoGravado() {
        return netoNoGravado;
    }

    public void setNetoNoGravado(double netoNoGravado) {
        this.netoNoGravado = netoNoGravado;
    }

    public double getOtroX() {
        return otroX;
    }

    public void setOtroX(double otroX) {
        this.otroX = otroX;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.cuit);
        hash = 43 * hash + Objects.hashCode(this.puntoComprobante);
        hash = 43 * hash + Objects.hashCode(this.numeroComprobante);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.netoGravado) ^ (Double.doubleToLongBits(this.netoGravado) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.IVA) ^ (Double.doubleToLongBits(this.IVA) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final Factura other = (Factura) obj;
        if (Double.doubleToLongBits(this.netoGravado) != Double.doubleToLongBits(other.netoGravado)) {
            return false;
        }
        if (Double.doubleToLongBits(this.IVA) != Double.doubleToLongBits(other.IVA)) {
            return false;
        }
        if (Double.doubleToLongBits(this.total) != Double.doubleToLongBits(other.total)) {
            return false;
        }
        if (!Objects.equals(this.cuit, other.cuit)) {
            return false;
        }
        if (!Objects.equals(this.puntoComprobante, other.puntoComprobante)) {
            return false;
        }
        if (!Objects.equals(this.numeroComprobante, other.numeroComprobante)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Factura{" + "Denominacion=" + denominacion + ", cuit=" + cuit + ", fecha=" + fecha + ", tipo=" + tipo + ", letra=" + letra + ", puntoComprobante=" + puntoComprobante + ", numeroComprobante=" + numeroComprobante + ", IVA=" + IVA + ", total=" + total + '}';
    }

}
