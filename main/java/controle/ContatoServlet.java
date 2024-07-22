package controle;

import modelo.Contato;
import persistencia.H2Factory;
import repositorio.ContatoDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ContatoServlet", urlPatterns = {"/ContatoServlet"})
public class ContatoServlet extends HttpServlet {

//dao para operacionalizar as ações
    protected ContatoDAO dao;
//lista a ser usada nas ações
    protected List<Contato> lista;
//entidade a ser usada nas ações
    protected Contato t;
//diretório das páginas
    protected String diretorio = "";
//página padrão para o crud
    protected String pagina = "";

//cria a tabela contato
    static {
        Connection conexao = H2Factory.getInstancia().getConnection();
        PreparedStatement ps = null;
        try {
            String comando = "CREATE TABLE if not exists contato (\n"
                    + " id INT NOT NULL AUTO_INCREMENT,\n"
                    + " nome VARCHAR(250) NOT NULL,\n"
                    + " placa VARCHAR(250) NOT NULL,\n"
                    + " PRIMARY KEY (id));";
            ps = conexao.prepareStatement(comando);
            ps.executeUpdate();
            conexao.commit();

        } catch (Exception ex) {
            try {
                conexao.rollback();
            } catch (Exception e) {

            }

            throw new RuntimeException(ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (Exception e) {

            }
        }
    }

    /**
     * método construtor
     */
    public ContatoServlet() {
        this.dao = new ContatoDAO(H2Factory.getInstancia());
        this.diretorio = "/contato/";
    }

    /**
     * sobrescreve o processaAcao da superclasse
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processaAcao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.t = new Contato();
            this.t.setId(Integer.parseInt((request.getParameter("id") != null) ? request.getParameter("id") : "-1"));
            this.t.setNome(request.getParameter("nome"));
            this.t.setPlaca(request.getParameter("placa"));
        } catch (Exception ex) {

        }
        try {
//captura a ação a ser efetivada
            String acao = (request.getParameter("acao") != null) ? request.getParameter("acao") : "index";
            try {
//faz o tratamento da ação padrão
                switch (acao) {
                    case "abreCadastro":
                        pagina = "cadastro.html";
                        break;

                    case "cadastra":
                        dao.insere(this.t);
                        this.lista = this.dao.selecionaVarios();
                        request.setAttribute("lista", this.lista);
                        pagina = "lista.jsp";
                        break;

                    case "exclui":
                        dao.exclui(this.t);
                        this.lista = this.dao.selecionaVarios();
                        request.setAttribute("lista", this.lista);
                        pagina = "lista.jsp";
                        break;

                    case "lista":
                        this.lista = this.dao.selecionaVarios();
                        request.setAttribute("lista", this.lista);
                        pagina = "lista.jsp";
                        break;

                    case "seleciona":
                        this.t = this.dao.seleciona(Integer.parseInt(request.getParameter("id")));
                        pagina = "index.html";
                        break;
                    
                    case "visualiza":
                        this.t = this.dao.seleciona(Integer.parseInt(request.getParameter("id")));
                        request.setAttribute("contato", t);
                        pagina = "visualiza.jsp";
                        break;
                        
                    case "atualiza":
                        this.dao.atualiza(this.t);
                        this.lista = this.dao.selecionaVarios();
                        request.setAttribute("lista", this.lista);
                        pagina = "lista.jsp";
                        break;

                    default:
                        pagina = "index.html";
                }

            } catch (Exception ex) {
                request.setAttribute("erro", ex);
                pagina = "erro.jsp";
            }

//efetua o despacho da requisição para a página correta com os atributos devidamente setados
            RequestDispatcher dispatcher = request.getRequestDispatcher(diretorio + pagina);
            dispatcher.forward(request, response);
        } catch (Exception ex) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processaAcao(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processaAcao(request, response);
    }
}
