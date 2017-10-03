package br.com.lojinha;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.lojinha.lojinhaDAO.UsuarioDAO;
import br.com.lojinha.model.Usuario;

@WebServlet("/autentica")
public class LoginController extends HttpServlet {

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
		autenticaUsuario(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		autenticaUsuario(request, response);
	}
	
	private void autenticaUsuario(HttpServletRequest request, HttpServletResponse response){
		try {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			
			Usuario usuario = getUsuarioDAO().autenticaUsuario(login, password);
			if (usuario != null){
				HttpSession session = request.getSession();
				session.setAttribute("autenticado", "sim");
				session.setAttribute("usuario", usuario);
				
				response.sendRedirect("/lojinha/main.jsp");
				
			} else {
				response.getWriter().write("falha");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
