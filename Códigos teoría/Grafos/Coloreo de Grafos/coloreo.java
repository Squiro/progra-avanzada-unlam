/// COLOREO
		int colorActual = 1;
		for(int i = 0; i < cantNodos; i++) {
			int marcador = 1;
			int nodoI = nodos[i].getNodo();
			int colorI = nodos[i].getColor();
			if(colorI == 0) {
				nodos[i].setColor(colorActual);
				nodosDeEsteColor[0] = nodoI;
				for(int j = i + 1; j < cantNodos;j++) {
					int nodoJ = nodos[j].getNodo();
					int colorJ = nodos[j].getColor();
					if(nodoI != nodoJ && !esAdyacente(nodosDeEsteColor, marcador, nodoJ) && colorJ == 0) {
						nodos[j].setColor(colorActual);
						nodosDeEsteColor[marcador] = nodoJ;
						marcador++;
					}
				}
				colorActual++;
			}
		}// n^2