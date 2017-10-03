package br.com.lojinha;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.lojinha.lojinhaDAO.ProdutoDAO;
import br.com.lojinha.model.Produto;


@WebServlet("/produto")
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdutoDAO produtoDAO;
	
	private ProdutoDAO getProdutoDAO(){
		if (this.produtoDAO == null){
			this.produtoDAO = new ProdutoDAO();
		}
		return this.produtoDAO;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String autenticado = String.valueOf(session.getAttribute("autenticado"));
		if (autenticado.equals("sim")){
			index(request, response);
		} else {
			request.getRequestDispatcher("/index.html").forward(request, response);
		}
	}

	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/produto.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		case "addProduto":
			addProduto(request, response);
			break;
		case "removeProduto":
			removeProduto(request, response);
			break;
		case "buscaProduto":
			buscaProduto(request, response);
			break;
		case "alteraProduto":
			alteraProduto(request, response);
			break;
		}
		
	}
	
	private void addProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Produto produto = new Produto();
		produto.setNome(request.getParameter("nomeProduto"));
		produto.setDescricaoProduto(request.getParameter("descricao"));
		produto.setPreco(request.getParameter("preco"));

		// add produto no banco de dados
		ProdutoDAO produtoDao = getProdutoDAO();
		produtoDao.adicionarNovoProduto(produto);
	}

	private void removeProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Produto produto = new Produto();
		produto.setId(Integer.valueOf(request.getParameter("produtoId")));

		ProdutoDAO produtoDao = getProdutoDAO();
		produtoDao.removerProduto(produto);
	}

	private void buscaProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Produto produto = new Produto();
		produto.setId(Integer.valueOf(request.getParameter("produtoId")));
		
		ProdutoDAO produtoDao = getProdutoDAO();
		produto = produtoDao.buscarProduto(produto);
				
		String json = new Gson().toJson(produto);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);		
	}
	
	private void alteraProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Produto produto = new Produto();
		produto.setId(Integer.valueOf(request.getParameter("id")));
		produto.setNome(request.getParameter("nomeProduto"));
		produto.setDescricaoProduto(request.getParameter("descricao"));
		produto.setPreco(request.getParameter("preco"));
		
		ProdutoDAO produtoDao = getProdutoDAO();
		produtoDao.updateProduto(produto);
	}

}
