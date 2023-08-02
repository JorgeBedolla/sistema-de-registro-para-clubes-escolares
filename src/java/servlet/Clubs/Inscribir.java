package servlet.Clubs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Inscribir extends HttpServlet {
    private String mensajeError;
    private boolean errorStatus = false;
    
    private boolean successStatus = false;
    private Club clubInscrito = new Club();
    
    private Persona personaAregistrar = new Persona();
    
    private consultaBD consulta = new consultaBD();
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List <Club> list = consulta.getAllClubs();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
             Cookie cookies[] = request.getCookies();         
            if(cookies == null || Integer.parseInt(cookies[0].getValue()) == -1){
                response.sendRedirect("/Clubs");
            }
           
            if(successStatus == false){
                out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");            
            out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" href='./css/style.css'>");
            out.println("<title>Inscribir Club</title>");
            out.println("<link rel=\"shortcut icon\" href='./img/boxLogo.png\'>");
            
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<div class=\"imgContenedor\">");
            out.println("<img class=\"imagenPrincipal\" src='./img/dado.png' alt=\"Logo\">");
            out.println("</div>");
            out.println("<div class=\"menuBottons\">");
            out.println("<a href=\"/Clubs/DesplegarClubs\">Desplegar Clubs</a>");
            out.println("<a href=\"/Clubs/BuscarClub\">Buscar Club</a>");
            out.println("</div>");
            out.println("</header>");
            out.println("<div class=\"contenido-de-form\">");
            //====================TABLA===============
            out.println("<a style=\"color: red;\" href=\"/Clubs/CerrarSesion\">Cerrar sesión</a>");
            out.println("<h1 class=\"titulo\">Inscribir Club</h1>");
            out.println("<div class=\"form-container\">");
            out.println("<form class=\"form-inscripcion\" action=\"/Clubs/Inscribir\" method=\"post\">");
            out.println("<label for=\"nombre\">Nombre:</label>");
            out.println("<br>");
            out.println("<input class=\"input-inscripcion\" type=\"text\" id=\"nombre\" name=\"nombre\">");
            out.println("<br>");
            out.println("<label for=\"apellidos\">Apellidos:</label>");
            out.println("<br>");
            out.println("<input class=\"input-inscripcion\" type=\"text\" name=\"apellidos\" id=\"apellidos\">");
            out.println("<br>");
            out.println("<label for=\"boleta\">Boleta:</label>");
            out.println("<br>");
            out.println("<input class=\"input-inscripcion\" type=\"text\" name=\"boleta\" id=\"boleta\">");
            out.println("<br>");
            out.println("<label for=\"semestre\">Semestre:</label>");
            out.println("<br>");
            out.println("<select class=\"select-inscripcion\" size=\"1\" name=\"semestre\" id=\"semestre\">");
            out.println("<option selected disabled value=\"0\">Selecciona una opción</option>");
            for(int i = 1; i < 9; i++){
                out.println("<option value=\""+i+"\">"+i+"</option>");
            }
            out.println("</select>");
            out.println("<br>");
            out.println("<label for=\"club\">Club:</label>");
            out.println("<br>");
            out.println("<select class=\"select-inscripcion\" size=\"1\" name=\"club\" id=\"club\">");
            out.println("<option selected disabled value=\"0\">Selecciona una opción</option>");
            for(Club c:list){
             out.println("<option value=\""+c.getId()+"\">"+c.getNombre()+"</option>");
            }
            
            out.println("</select>");
            out.println("<br>");
            if(errorStatus == true){
                out.println("<div class=\"mensajeError\">*"+mensajeError+"</div>");
            }
            out.println("<button class=\"button-inscripcion\" type=\"submit\">Inscribir Club</button>");
            out.println("</form>");
            out.println("</div>");
            
            //======================================== 
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            }else{
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
                out.println("<a href=\"/Clubs/BuscarClub\">Buscar Club</a>");
                out.println("</div>");
                out.println("</header>");
                out.println("<div class=\"contenido-de-form\">");
                //====================MENSAJE EXITO===============
                out.println("<h1 class=\"tituloS\">Se ha inscrito con exito al club: "+clubInscrito.getNombre()+" </h1>");
                out.println("<h2 class=\"subTitulo\">Con los siguentes datos</h2>");
                out.println("<p class=\"datosS\">Nombre: "+personaAregistrar.getNombre()+"</p>");
                out.println("<p class=\"datosS\">Apellido: "+personaAregistrar.getApellido()+"</p>");
                out.println("<p class=\"datosS\">Boleta: "+personaAregistrar.getBoleta()+"</p>");
                out.println("<p class=\"datosS\">Semestre: "+personaAregistrar.getSemestre()+"</p><br>");
                out.println("<a href=\"/Clubs/Inscribir\" class=\"button-inscripcionS\">Aceptar</a>");
                out.println("<br><br>");
                
                //======================================== 
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        successStatus = false;
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Persona persona = new Persona();
        persona.setNombre(request.getParameter("nombre"));
        persona.setApellido(request.getParameter("apellidos"));
        persona.setBoleta(request.getParameter("boleta"));
        
         
        
     
        
        try{
            int semestre = Integer.valueOf(request.getParameter("semestre"));
            persona.setSemestre(semestre);
        }catch(Exception e){
            errorStatus = true;
            mensajeError = "Por favor seleccione su semestre";
        }
        
        try{
            int club = Integer.valueOf(request.getParameter("club")); 
            persona.setIdClub(club);
        }catch(Exception e){
            errorStatus = true;
            mensajeError = "Por favor seleccione un club";
        }
        
        
        
        
        errorStatus = validar(persona);
        processRequest(request, response); 
       
        
       /*  response.setContentType("text/html;charset=UTF-8");
       
        Persona persona1 = consulta.bucarPersonaporBoleta(persona.getBoleta());
        try (PrintWriter out = response.getWriter()) {
            out.println(errorStatus);
            out.println(mensajeError);
            out.println(persona1);
            out.println(persona.getBoleta().length());
            
        }*/
        
    }


    @Override
    public String getServletInfo() {
        
        
        return "Short description";
    }// </editor-fold>
    
    public boolean validar(Persona p){
        
        String nombre = p.getNombre();
        String apellido = p.getApellido();
        String boleta = p.getBoleta();
        int semestre = p.getSemestre();
        int idClub = p.getIdClub();
         
        
        //Validar nombre
        if(nombre == null || nombre.length() == 0){
            mensajeError = "Por favor ingrese su nombre";
            return true;
        }
        
        if(!(nombre.length() <= 100)){
            mensajeError = "El tamaño del nombre debe ser menor a 100 carácteres";
            return true;
        }
        
        //Validar apellidos
        if(apellido == null || apellido.length() == 0){
            mensajeError = "Por favor ingrese sus apellidos";
            return true;
        }
        
        if(!(apellido.length() <= 150)){
            mensajeError = "El tamaño del apellido debe ser menor a 150 carácteres";
            return true;
        }
        
        
        //Validar boleta
        if(boleta == null || boleta.length() == 0){
            mensajeError = "Por favor ingrese su boleta";
            return true;
        }
        
        if(boleta.length() > 10){
            mensajeError = "La boleta debe tener un tamaño menor a 10 caracteres";
            return true;
        }
        
        //Validar semestre
        if(semestre == 0){
            mensajeError = "Por favor seleccione un semestre";
            return true;
        }
        
        //============ VALIDAMOS EL CLUB ===============
        if(idClub == 0){
            mensajeError = "Por favor seleccione un club";
            return true;
        }
        
        clubInscrito = consulta.buscarClubPorId(idClub);
        Persona persona1 = consulta.bucarPersonaporBoleta(boleta);
        //Verificamos que la persona no este registrada
        if(persona1.getNombre() != null){
            mensajeError = "Este usuario ya esta registrado";
            return true;
        }
        
        //Inscribimos al alumno
        
        int cupo = clubInscrito.getCupo();
        int ocupacionActual = clubInscrito.getOcupacionActual();
        if((ocupacionActual > 0)&&(ocupacionActual < cupo) ){
            
            ocupacionActual--;
            consulta.actualizarClubOcupacion(clubInscrito, ocupacionActual);
            consulta.registrarPersona(p);
            successStatus = true;
            personaAregistrar = p;
            return false;
        }else{
            mensajeError = "El club que ha seleccionado no tiene cupo";
            return true;
        }
             
    }

}
