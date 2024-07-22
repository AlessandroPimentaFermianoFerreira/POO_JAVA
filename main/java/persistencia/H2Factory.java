package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public final class H2Factory {

//endereço (url) do arquivo
//no caso da base H2 é um arquivo chamado 'dados'
    private final String urlBD = "jdbc:h2:file:./data/dados;AUTO_SERVER=TRUE";
//nome da base de dados (precisa ser criada no banco de dados)
//no caso da base H2 não há nome pois é um aqrquivo
    private final String BD = "";
//usuário do banco de dados (root é default)
    private final String userBD = "root";
//senha do usuário do banco de dados (vai de cada um...)
    private final String passBD = "salesiano";
//instancia de H2Factory
    private static H2Factory instancia = null;

//torna inviável criar qualquer instancia de H2Factory fora dessa classe
    private H2Factory() {

    }

    /**
     * implementação do padrão singleton
     *
     * @see <a href="https://pt.wikipedia.org/wiki/Singleton">Singleton -
     * Wikipedia</a>
     * @return
     */
    public static H2Factory getInstancia() {
        if (instancia == null) {
            instancia = new H2Factory();
        }
        return instancia;
    }

    public Connection getConnection() {
        Connection conexao = null;
        try {
            Class.forName("org.h2.Driver");
            conexao = DriverManager.getConnection(urlBD + BD, userBD, passBD);

            conexao.setAutoCommit(false);
            return conexao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
