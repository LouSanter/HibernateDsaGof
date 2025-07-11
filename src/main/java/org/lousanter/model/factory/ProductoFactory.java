package org.lousanter.model.factory;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.lousanter.model.dto.CategoriaDTO;
import org.lousanter.model.dto.ProductoDTO;
import org.lousanter.model.dto.ProveedorDTO;
import org.lousanter.model.dto.UbicacionDTO;


import java.math.BigDecimal;

public class ProductoFactory {

        public static ProductoDTO crearDesdeFormulario(
                TextField nombre,
                TextField codigo,
                TextField stock,
                TextField stockMin,
                TextField precioCompra,
                TextField precioVenta,
                ComboBox<CategoriaDTO> categoria,
                ComboBox<ProveedorDTO> proveedor,
                ComboBox<UbicacionDTO> ubicacion,
                TextField lblDes
        ) {
            ProductoDTO dto = new ProductoDTO();

            dto.setNombre(nombre.getText().trim());
            dto.setCodigo(codigo.getText().trim());
            dto.setStock(Integer.parseInt(stock.getText().trim()));
            dto.setStockMin(Integer.parseInt(stockMin.getText().trim()));
            dto.setPrecioCompra(BigDecimal.valueOf(Double.parseDouble(precioCompra.getText().trim())));
            dto.setPrecioVenta(BigDecimal.valueOf(Double.parseDouble(precioVenta.getText().trim())));
            dto.setIdCategoria(categoria.getSelectionModel().getSelectedItem().getId());
            dto.setDescripcion(lblDes.getText().trim());
            dto.setIdProveedor(proveedor.getSelectionModel().getSelectedItem().getIdProveedor());
            dto.setIdUbicacion(ubicacion.getSelectionModel().getSelectedItem().getIdUbicacion());
            dto.setNombreCategoria(categoria.getSelectionModel().getSelectedItem().getNombre());
            dto.setNombreProveedor(proveedor.getSelectionModel().getSelectedItem().getNombre());
            dto.setNombreUbicacion(ubicacion.getSelectionModel().getSelectedItem().getNombre());

            return dto;
        }




}
