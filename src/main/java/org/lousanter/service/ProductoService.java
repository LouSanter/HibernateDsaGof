package org.lousanter.service;

import org.lousanter.model.dto.ProductoDTO;
import org.lousanter.model.entities.*;
import org.lousanter.model.mapper.ProductoMapper;
import org.lousanter.model.repo.*;
import org.lousanter.util.productoUtil.ProductoBST;
import org.lousanter.util.productoUtil.ProductoHistorialEntry;
import org.lousanter.util.productoUtil.ProductoQueue;
import org.lousanter.util.productoUtil.ProductoStack;
import java.util.List;

public class ProductoService {

    private final ProductoRepo pRepo = new ProductoRepo(Producto.class);
    private final ProductoQueue pQueue = new ProductoQueue();
    private final ProductoStack pStack = new ProductoStack();
    private final ProductoBST pTree = new ProductoBST();

    public void registrarProducto(ProductoDTO dto, Ubicacion ubicacion, Proveedor proveedor, Categoria categoria) {
        Producto producto = ProductoMapper.toEntity(dto, categoria, ubicacion, proveedor);
        System.out.println("Entrando en service saveProd");

        producto.setIdProducto(null);
        producto = pRepo.save(producto);
        dto.setIdProducto(producto.getIdProducto());

        System.out.println("Producto guardado con ID: " + producto.getIdProducto());

        if (producto.getIdProducto() != null) {
            dto.setIdProducto(producto.getIdProducto());
            ProductoStack.push(dto, ProductoHistorialEntry.Accion.NUEVO);
            pTree.insertar(dto);

            if (producto.getStock() <= producto.getStockMin()) {
                pQueue.enqueue(producto);
            }
        } else {
            System.err.println("⚠No se gener un ID ");
        }
    }



    public void actualizarProducto(ProductoDTO dto, Ubicacion ubicacion, Proveedor proveedor, Categoria categoria) {
        ProductoDTO estadoAnterior = ProductoMapper.toDTO(
                pRepo.findById(dto.getIdProducto())
        );

        Producto producto = ProductoMapper.toEntity(dto, categoria, ubicacion, proveedor);
        pRepo.update(producto);

        ProductoStack.push(estadoAnterior, ProductoHistorialEntry.Accion.ACTUALIZADO);

        pTree.reemplazar(dto);

        if (producto.getStock() <= producto.getStockMin()) {
            pQueue.enqueue(producto);


        }
    }



    public void eliminarProducto(Long id) {
        System.out.println("id : " + id);
        Producto producto = pRepo.findById(id);

        if (producto != null) {
            try {

                producto.getCategoria().getNombre();
                producto.getProveedor().getNombre();
                producto.getUbicacion().getNombre();


                ProductoDTO dto = ProductoMapper.toDTO(producto);

                ProductoStack.push(dto, ProductoHistorialEntry.Accion.ELIMINADO);

                pRepo.delete(producto);
                
                pTree.eliminar(producto.getIdProducto());

            } catch (RuntimeException e) {
                System.out.println("Error al eliminar el producto desde Service: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }




    public List<ProductoDTO> buscarPorNombre(String nombre) {
        return pTree.buscar(nombre);
    }

    public List<ProductoDTO> listarProductos() {
        return pTree.getAll();
    }


}
