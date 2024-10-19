package pe.com.cibertec.lp2_carrito_compra.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
	private Integer productoId;
	private Integer cantidad;
}
