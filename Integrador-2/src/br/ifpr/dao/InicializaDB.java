package br.ifpr.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


public class InicializaDB {

	public static void main(String[] args) {

//		inicializaCategorias();
//		incializaFilmes();
/*
	}
	private static void inicializaCategorias(){
		Categoria c1 = new Categoria("Ação");
		Categoria c2 = new Categoria("Animação");
		Categoria c3 = new Categoria("Aventura");
		Categoria c4 = new Categoria("Biografia");
		Categoria c5 = new Categoria("Comédia");
		Categoria c6 = new Categoria("Crime");
		Categoria c7 = new Categoria("Drama");
		Categoria c8 = new Categoria("Documentário");
		Categoria c9 = new Categoria("Família");
		Categoria c10 = new Categoria("Fantasia");
		Categoria c11 = new Categoria("Ficção");
		Categoria c12 = new Categoria("Guerra");
		Categoria c13 = new Categoria("Musical");
		Categoria c14 = new Categoria("Romance");
		Categoria c15 = new Categoria("Suspense");
		Categoria c16 = new Categoria("Terror");

		List<Categoria> categorias = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15,
				c16);

		DaoCategoria dc = new DaoCategoria();

		categorias.forEach(c -> dc.save(c));
	}

	private static void incializaFilmes() {
		DaoFilme df = new DaoFilme();
		InputStream input = null;
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			input = classLoader.getResourceAsStream("filmes.txt");

			BufferedReader br = null;
			String line = "";

			br = new BufferedReader(new InputStreamReader(input, "ISO-8859-1"));
			while ((line = br.readLine()) != null) {
				String tokens[] = line.split(";");
				Filme f = new Filme();
				f.setTitulo(tokens[0]);
				f.setAno(Integer.parseInt(tokens[1]));
				df.save(f);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/
	}
}
		