package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.exception.ExceptionUtils;

import connection.SingleConnectionPostgresql;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/principal/*") /* Intercepta todas as requisições que virem do projeto ou mapeamento */
public class FilterAuthentication extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private static Connection connection = null;

	public FilterAuthentication() {
		super();

	}

	/* Encerra os processos quando o servidor é parado */
	public void destroy() {

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Intercepta todas as requisições e das respostas no sistema */
	/* todas as requisições passam pelo doFilter */
	// exemplo: validação auth
	// commit ou rollback do banco
	/* Validar e Redirecionar páginas */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpSession session = httpServletRequest.getSession();

			String user = (String) session.getAttribute("usuario");

			String urlAuth = httpServletRequest.getServletPath();

			if (user == null && !urlAuth.equalsIgnoreCase("/principal/ServletLogin")) {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlAuth);
				request.setAttribute("msg", "Por favor realize o login!");
				redirecionar.forward(request, response);
				return;
			} else
				chain.doFilter(request, response);

		} catch (Exception e) {
			RequestDispatcher redirect = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", ExceptionUtils.getStackTrace(e));
			redirect.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
				request.setAttribute("msg", ExceptionUtils.getStackTrace(e));
				redirecionar.forward(request, response);
			}
		}
	}

	/* Inicia os processos ou recursos quando o servidor sobe o projeto */
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionPostgresql.getConnection();
	}

}
