package servlet.Clubs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DesplegarClubs extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        consultaBD consulta = new consultaBD();
        List <Club> list = consulta.getAllClubs();
     
       
            
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            Cookie cookies[] = request.getCookies();         
            if(cookies == null || Integer.parseInt(cookies[0].getValue()) == -1){
                response.sendRedirect("/Clubs");
            }
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");            
            out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" href='./css/style.css'>");
            out.println("<title>Desplegar Clubs</title>");
            out.println("<link rel=\"shortcut icon\" href='./img/boxLogo.png\'>");
            
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<div class=\"imgContenedor\">");
            out.println("<img class=\"imagenPrincipal\" src='./img/dado.png' alt=\"Logo\">");
            out.println("</div>");
            out.println("<div class=\"menuBottons\">");
            out.println("<a href=\"/Clubs/Inscribir\">Inscribir Club</a>");
            out.println("<a href=\"/Clubs/BuscarClub\">Buscar Club</a>");
            out.println("</div>");
            out.println("</header>");
            out.println("<div class=\"contenido\">");
            
            out.println("<a style=\"color: rgb(0, 69, 153);\" href=\"/Clubs/VerClubsInscritos\">Ver clubs inscritos</a>");
            out.println("<a style=\"color: red;\" href=\"/Clubs/CerrarSesion\">Cerrar sesión</a>");
            //====================TABLA===============
            for(Club c:list){
                //out.println("<div>");
                out.println("<table>");
                out.println("<thead>");
                out.println("<tr class=\"encabezadosTabla\">");
                out.println("<th>Nombre del Club</th>");
                out.println("<th>"+c.getNombre()+"</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                out.println("<tr>");
                out.println("<th class=\"encabezadosTabla\">Jefe de club:</th>");
                out.println("<td>"+c.getJefeClub()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th class=\"encabezadosTabla\">Descripcion:</th>");
                out.println("<td>"+c.getDescripcion()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th class=\"encabezadosTabla\">Horario:</th>");
                out.println("<td>"+c.getHorario()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th class=\"encabezadosTabla\">Creditos por hora:</th>");
                out.println("<td>"+c.getCreditoHora()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th class=\"encabezadosTabla\">Cupo maximo:</th>");
                out.println("<td>"+c.getCupo()+" personas</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th class=\"encabezadosTabla\">Ocupacion actual:</th>");
                out.println("<td>"+c.getOcupacionActual()+" personas</td>");
                out.println("</tr>");
                out.println("<tr>");
                 out.println("<th class=\"encabezadosTabla\">Anuncions del club:</th>");
                out.println("<td>"+c.getAnuncios()+"</td>");
                out.println("</tr>");
                out.println("<th class=\"encabezadosTabla\">Lugar donde se imparte:</th>");
                out.println("<td>"+c.getLugar()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("</tbody>");
                out.println("</table>");
                //out.println("</div>");
                //out.println("<br>");
            }
            
            //======================================== 
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
