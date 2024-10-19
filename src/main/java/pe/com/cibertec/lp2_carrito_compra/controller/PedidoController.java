package pe.com.cibertec.lp2_carrito_compra.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import pe.com.cibertec.lp2_carrito_compra.model.entity.DetallePedidoEntity;
import pe.com.cibertec.lp2_carrito_compra.model.entity.Pedido;
import pe.com.cibertec.lp2_carrito_compra.model.entity.PedidoEntity;
import pe.com.cibertec.lp2_carrito_compra.model.entity.ProductoEntity;
import pe.com.cibertec.lp2_carrito_compra.model.entity.UsuarioEntity;
import pe.com.cibertec.lp2_carrito_compra.repository.PedidoRepository;

@Controller
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("/guardar_factura")
	public String guardarFactura(HttpSession sesion) {
		String correo = sesion.getAttribute("usuario").toString();
		UsuarioEntity usuarioInsertar = new UsuarioEntity();
		usuarioInsertar.setCorreo(correo);
		
		PedidoEntity pedidoEntity = new PedidoEntity();
		pedidoEntity.setFechaCompra(LocalDate.now());
		pedidoEntity.setUsuarioEntity(usuarioInsertar);
		
		List<DetallePedidoEntity>detallePedido = new ArrayList<>();
		
		List<Pedido>productoSesion = null;
		if(sesion.getAttribute("carrito") == null) {
			productoSesion = new ArrayList<>();
		}else {
			productoSesion = (List<Pedido>)sesion.getAttribute("carrito");
		}
		for(Pedido pedido: productoSesion) {
			DetallePedidoEntity detallePedidoEntity = new DetallePedidoEntity();
			ProductoEntity prodEntity = new ProductoEntity();
			prodEntity.setProductoId(pedido.getProductoId());
			
			detallePedidoEntity.setProductoEntity(prodEntity);
			detallePedidoEntity.setCantidad(pedido.getCantidad());
			detallePedidoEntity.setPedidoEntity(pedidoEntity);
			
			detallePedido.add(detallePedidoEntity);
		}
		pedidoEntity.setDetallePedido(detallePedido);
		pedidoRepository.save(pedidoEntity);
		sesion.removeAttribute("carrito");
		return "redirect:/menu";
	}
}
