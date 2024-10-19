package pe.com.cibertec.lp2_carrito_compra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cibertec.lp2_carrito_compra.model.entity.ProductoEntity;
import pe.com.cibertec.lp2_carrito_compra.repository.ProductoRepository;
import pe.com.cibertec.lp2_carrito_compra.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public List<ProductoEntity> buscarTodosProductos() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public ProductoEntity buscarProductoPorId(Integer id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id).get();
	}

}
