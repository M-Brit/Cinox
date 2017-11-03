package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFilter implements Filter {

    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ACCES_PUBLIC = "connexion.jsp";
    /**
     *
     * @param config permet de recuperer les parametres d'initialisation du filtre et d'en passer
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        // ...
    }

    /**
     * Effectue les traitements de fitres
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        // besoin de cast car les objets request et response peuvent gerer n'importe quel type de requetes.
        // Pas specialisé comme ceux des servlets d'httpservlet.
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

       // Test si l'utilisateur est déjà connecté.
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            /* Redirection vers la page publique */
            // TODO : mettre la page d'index en haut dans la constante
           // response.sendRedirect( request.getContextPath() + ACCES_PUBLIC );
        } else {
            /* la requete est transmise au filtre suivant ou vers la ressource visé initialement */
            chain.doFilter( request, response );
        }
    }

    public void destroy() {
        // TODO
    }
}
