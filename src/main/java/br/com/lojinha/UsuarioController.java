package br.com.lojinha;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.lojinha.lojinhaDAO.UsuarioDAO;
import br.com.lojinha.model.Usuario;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
	
	private UsuarioDAO getUsuarioDAO(){
		if (this.usuarioDAO == null){
			this.usuarioDAO = new UsuarioDAO();
		}
		return this.usuarioDAO;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		index(request, response);
	}

	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/usuario.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		case "addUsuario":
			addUsuario(request, response);
			break;
		case "removeUsuario":
			removeUsuario(request, response);
			break;
		case "buscaUsuario":
			buscaUsuario(request, response);
			break;
		case "alteraUsuario":
			alteraUsuario(request, response);
			break;
		}
	}

	private void addUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setCodTipoUsuario(request.getParameter("tipoPermissao"));
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));

		UsuarioDAO usuarioDao = getUsuarioDAO();
		usuarioDao.adicionarNovoUsuario(usuario);
	}
	
	private void removeUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setId(Integer.valueOf(request.getParameter("usuarioId")));
		
		UsuarioDAO usuarioDao = getUsuarioDAO();
		usuarioDao.removerUsuario(usuario);
	}
	
	private void alteraUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setCodTipoUsuario(request.getParameter("tipoUsuario"));
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));
		
		UsuarioDAO usuarioDao = getUsuarioDAO();
		usuarioDao.updateUsuario(usuario);
		
	}

	private void buscaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setId(Integer.valueOf(request.getParameter("usuarioId")));
		
		UsuarioDAO usuarioDao = getUsuarioDAO();
		usuario = usuarioDao.buscarUsuario(usuario);
		
		String json = new Gson().toJson(usuario);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);		
	}
}
