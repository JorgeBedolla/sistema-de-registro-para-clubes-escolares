
package servlet.Clubs;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuscarClub extends HttpServlet {
    
    private boolean busquedaStatus = false;
    private String nombreClub;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            out.println("<title>Consultar clubs</title>");
            out.println("<link rel=\"shortcut icon\" href='./img/boxLogo.png\'>");
            
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<div class=\"imgContenedor\">");
            out.println("<img class=\"imagenPrincipal\" src='./img/dado.png' alt=\"Logo\">");
            out.println("</div>");
            out.println("<div class=\"menuBottons\">");
            out.println("<a href=\"/Clubs/Inscribir\">Inscribir Club</a>");
            out.println("<a href=\"/Clubs/DesplegarClubs\">Desplegar Clubs</a>");
            out.println("</div>");
            out.println("</header>");
            out.println("<div class=\"contenido\">");
            
           
            //====================Formulario de busqueda===============
            out.println("<div class=\"form-container\">");
            out.println("<form class=\"form-busqueda\" action=\"/Clubs/BuscarClub\" method=\"post\">");
            out.println("<label for=\"nombreClub\">Nombre del club:</label>");
            out.println("<input class=\"search-box\" type=\"text\" id=\"nombreClub\" name=\"nombreClub\">");
            out.println("<button class=\"search-button\" type=\"submit\">Buscar</button>");
            out.println("</form>");
            out.println("</div>");
            
            //======================================== 
            //=================TABLA DE RESULTADOS============
            
            if(busquedaStatus == true){
                
                //efectuamos la busqueda
               
                consultaBD consulta = new consultaBD();
                Club c = new Club();
                c = consulta.buscarClub(nombreClub);//Buscamos el club
                              
                if(c.getNombre() == null){
                    //Desplegamos mensaje de resultados no encontrados
                    out.println("<h1>No se encontraron resultados</h1>");
                }else{
                    
                     out.println("<br><br><br>");
                     out.println("<h1>Resultados encontrados: </h1>");
                
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
                }
             }
                    
                    
              
            //================================================

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        busquedaStatus = false;
        processRequest(request, response);
        
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        busquedaStatus = true;
        nombreClub = request.getParameter("nombreClub");
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
