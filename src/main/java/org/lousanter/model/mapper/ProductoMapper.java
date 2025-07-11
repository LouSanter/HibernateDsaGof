package org.lousanter.model.mapper;

import org.lousanter.model.entities.Categoria;
import org.lousanter.model.entities.Producto;
import org.lousanter.model.entities.Proveedor;
import org.lousanter.model.entities.Ubicacion;
import org.lousanter.model.dto.ProductoDTO;

public class ProductoMapper {

    public static ProductoDTO toDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setIdProducto(producto.getIdProducto());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setCodigo(producto.getCodigo());
        productoDTO.setPrecioCompra(producto.getPrecioCompra());
        productoDTO.setPrecioVenta(producto.getPrecioVenta());
        productoDTO.setStock(producto.getStock());
        productoDTO.setStockMin(producto.getStockMin());
        productoDTO.setIdProveedor(producto.getProveedor().getIdProveedor());
        productoDTO.setIdCategoria(producto.getCategoria().getIdCategoria());
        productoDTO.setIdUbicacion(producto.getUbicacion().getIdUbicacion());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setNombreCategoria(producto.getCategoria().getNombre());
        productoDTO.setNombreUbicacion(producto.getUbicacion().getNombre());
        productoDTO.setNombreProveedor(producto.getProveedor().getNombre());

        productoDTO.setFecha(producto.getFecha());
        return productoDTO;
    }

    public static Producto toEntity(ProductoDTO productoDTO, Categoria categoria, Ubicacion ubicacion, Proveedor proveedor) {
        Producto producto = new Producto();
        producto.setFecha(productoDTO.getFecha());
        producto.setIdProducto(productoDTO.getIdProducto());
        producto.setNombre(productoDTO.getNombre());
        producto.setCodigo(productoDTO.getCodigo());
        producto.setPrecioCompra(productoDTO.getPrecioCompra());
        producto.setPrecioVenta(productoDTO.getPrecioVenta());
        producto.setStock(productoDTO.getStock());
        producto.setStockMin(productoDTO.getStockMin());
        producto.setCategoria(categoria);
        producto.setUbicacion(ubicacion);
        producto.setProveedor(proveedor);
        return producto;
    }


}
