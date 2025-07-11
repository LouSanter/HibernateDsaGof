package org.lousanter.service;

import org.lousanter.model.dto.ProductoDTO;
import org.lousanter.model.entities.*;
import org.lousanter.model.mapper.ProductoMapper;
import org.lousanter.model.repo.*;
import org.lousanter.util.productoUtil.ProductoBST;
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
        pRepo.save(producto);
        dto.setIdProducto(producto.getIdProducto());
        pTree.insertar(dto);
        pStack.push(producto);
        if (producto.getStock() <= producto.getStockMin()) {
            pQueue.enqueue(producto);
        }


    }

    public void actualizarProducto(ProductoDTO dto, Ubicacion ubicacion, Proveedor proveedor, Categoria categoria) {
        Producto producto = ProductoMapper.toEntity(dto, categoria, ubicacion, proveedor );
        pRepo.update(producto);
        pTree.reemplazar(dto);
    }

    public void eliminarProducto(Long id) {
        System.out.println("id : " + id);
        Producto producto = pRepo.findById(id);
        if (producto != null) {
            try {
                pRepo.delete(producto);

                pTree.eliminar(producto.getIdProducto());

            } catch (RuntimeException e) {
                System.out.println("Error al eliminar el producto desde Service");
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
