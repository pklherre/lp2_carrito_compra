package pe.com.cibertec.lp2_carrito_compra.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.lp2_carrito_compra.model.entity.UsuarioEntity;
import pe.com.cibertec.lp2_carrito_compra.repository.UsuarioRepository;
import pe.com.cibertec.lp2_carrito_compra.service.UsuarioService;
import pe.com.cibertec.lp2_carrito_compra.utils.Utilitarios;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioRepository usuarioRepository;
	
	@Override
	public void crearUsuario(UsuarioEntity usuarioEntity, MultipartFile foto) {
		// 1. Guardar foto en el servidor
		String nombreFoto = Utilitarios.guardarImagen(foto);
		usuarioEntity.setUrlImagen(nombreFoto);
		
		// 2. Extraer Hash del password
		String passwordHash = Utilitarios.extraerHash(usuarioEntity.getPassword());
		usuarioEntity.setPassword(passwordHash);
		
		// 3. Guardar usuario en base de datos
		usuarioRepository.save(usuarioEntity);	
	}

	@Override
	public boolean validarUsuario(UsuarioEntity usuarioFormulario) {
		// 1. Recuperar el usuario por correo
		UsuarioEntity usuarioEncontrado = usuarioRepository
				.findByCorreo(usuarioFormulario.getCorreo());
		
		// correo existe en la base de datos?
		if(usuarioEncontrado == null) {
			return false;
		}
		
		// 2. Validar password input con el hash de la base de datos
		if(!Utilitarios.checkPassword(usuarioFormulario.getPassword(), 
				usuarioEncontrado.getPassword())) {
			return false;
		}
		
		//3. login exitoso
		return true;
	}

}
