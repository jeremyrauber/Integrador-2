package dao;

import model.Usuario;

public class DaoUsuario extends DaoEntity<Usuario, Integer> {

	public DaoUsuario() {
		super(Usuario.class);
	}
}