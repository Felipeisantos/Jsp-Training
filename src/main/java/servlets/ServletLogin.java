package servlets;

import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;

import dao.DAOLoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

//@WebServlet(urlPatterns= { "/ServletLogin","/principal/ServletLogin"}) 
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

	public ServletLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
		request.setAttribute("msg", "informe o login e senha corretamente!");
		redirecionar.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		try {
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelLogin modelLogin = new ModelLogin();

				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);

				if (daoLoginRepository.authUser(modelLogin)) {
					request.getSession().setAttribute("usuario", modelLogin.getLogin());

					if (url == null || url.equals("null"))
						url = "principal/principal.jsp";

					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					request.setAttribute("msg", "Página Principal");
					redirecionar.forward(request, response);

				} else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "informe o login e senha corretamente!");
					redirecionar.forward(request, response);
				}

			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "informe o login e senha corretamente!");
				redirecionar.forward(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", ExceptionUtils.getStackTrace(e));
			redirecionar.forward(request, response);
		}

	}

}
