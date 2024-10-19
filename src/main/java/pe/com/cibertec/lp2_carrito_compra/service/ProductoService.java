package pe.com.cibertec.lp2_carrito_compra.service;

import java.util.List;

import pe.com.cibertec.lp2_carrito_compra.model.entity.ProductoEntity;

public interface ProductoService {
	List<ProductoEntity>buscarTodosProductos();
	ProductoEntity buscarProductoPorId(Integer id);
}
