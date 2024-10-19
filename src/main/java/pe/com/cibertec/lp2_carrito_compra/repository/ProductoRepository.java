package pe.com.cibertec.lp2_carrito_compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.cibertec.lp2_carrito_compra.model.entity.ProductoEntity;

@Repository
public interface ProductoRepository 
extends JpaRepository<ProductoEntity, Integer>{

}
