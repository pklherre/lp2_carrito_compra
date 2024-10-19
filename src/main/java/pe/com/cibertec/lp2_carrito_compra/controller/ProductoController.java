package pe.com.cibertec.lp2_carrito_compra.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import pe.com.cibertec.lp2_carrito_compra.model.entity.DetallePedidoEntity;
import pe.com.cibertec.lp2_carrito_compra.model.entity.Pedido;
import pe.com.cibertec.lp2_carrito_compra.model.entity.ProductoEntity;
import pe.com.cibertec.lp2_carrito_compra.model.entity.UsuarioEntity;
import pe.com.cibertec.lp2_carrito_compra.service.ProductoService;
import pe.com.cibertec.lp2_carrito_compra.service.UsuarioService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/menu")
	public String mostrarMenu(HttpSession sesion, Model model) {
		if(sesion.getAttribute("usuario") == null) {
			return "redirect:/";
		}
		// Obtener valor de sesión
		String correo = sesion.getAttribute("usuario").toString();
		UsuarioEntity usuarioEncontrado = usuarioService
				.buscarUsuarioPorCorreo(correo);
		model.addAttribute("foto", usuarioEncontrado.getUrlImagen());
		
		List<Pedido>productoSesion = null;
		if(sesion.getAttribute("carrito") == null) {
			productoSesion = new ArrayList<>();
		}else {
			productoSesion = (List<Pedido>) sesion.getAttribute("carrito");
		}
		model.addAttribute("cant_carrito", productoSesion.size());
		
		// Ver carrito con datos
		List<DetallePedidoEntity>detallePedidoEntity = new ArrayList<>();
		Double totalPedido = 0.0;
		
		for(Pedido pedido: productoSesion) {
			DetallePedidoEntity det = new DetallePedidoEntity();
			ProductoEntity pro = productoService.buscarProductoPorId(
					pedido.getProductoId());
			det.setProductoEntity(pro);
			det.setCantidad(pedido.getCantidad());
			detallePedidoEntity.add(det);
			totalPedido += pedido.getCantidad() * pro.getPrecio();
		}
		
		model.addAttribute("carrito", detallePedidoEntity);
		model.addAttribute("total", totalPedido);
		// fin ver carrito con datos
		List<ProductoEntity>listaProductos = productoService.buscarTodosProductos();
		model.addAttribute("productos", listaProductos);
		return "menu";
	}
	
	@PostMapping("/agregar_producto")
	public String agregarProducto(HttpSession sesion,
			@RequestParam("prodId")String prod,
			@RequestParam("cant")String cant) {
		
		//instanciando pedido
		List<Pedido> productos = null;
		if(sesion.getAttribute("carrito") == null) {
			productos = new ArrayList<>();
		}else {
			productos = (List<Pedido>)sesion.getAttribute("carrito");
		}
		
		Integer prodId = Integer.parseInt(prod);
		Integer cantidad = Integer.parseInt(cant);
		Pedido pedido = new Pedido(prodId, cantidad);
		productos.add(pedido);
		sesion.setAttribute("carrito", productos);
		
		return "redirect:/menu";
	}
	
	
	
}

