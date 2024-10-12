package pe.com.cibertec.lp2_carrito_compra.service;

import org.springframework.web.multipart.MultipartFile;

import pe.com.cibertec.lp2_carrito_compra.model.entity.UsuarioEntity;

public interface UsuarioService {
	void crearUsuario(UsuarioEntity usuarioEntity, MultipartFile foto);
}
