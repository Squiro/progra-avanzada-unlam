package com.emmettbrown.mensajes;

import java.io.Serializable;

public abstract class Msg implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Object realizarAccion(Object obj);
}
