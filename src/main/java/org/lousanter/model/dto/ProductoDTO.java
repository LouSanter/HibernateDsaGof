package org.lousanter.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductoDTO {

    private Long idProducto;
    private String nombre;
    private String descripcion;
    private String codigo;
    private Long idUbicacion;
    private Long idCategoria;
    private Long idProveedor;
    private int stock;
    private int stockMin;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private LocalDate fecha;



    private String nombreCategoria;
    private String nombreUbicacion;
    private String nombreProveedor;


    public ProductoDTO(LocalDate fecha, Long idProducto, String nombre, String descripcion, String codigo, Long idUbicacion, Long idCategoria, Long idProveedor, int stock, int stockMin, BigDecimal precioCompra, BigDecimal precioVenta) {
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.idUbicacion = idUbicacion;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.stock = stock;
        this.stockMin = stockMin;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public ProductoDTO() {
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Long idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }
}
