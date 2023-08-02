package servlet.Clubs;

import java.sql.*;
import java.util.*;
import servlet.Clubs.Club;
import servlet.Clubs.Persona;

public class consultaBD {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/escomclubs";
    
    static final String USER = "root";
    static final String PASS = "root123";
    
    public static Connection obtenerConexion(){
        Connection con = null;
        try{
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            
        }catch(Exception e){
            System.out.println("Error en conectar");
            System.out.println(e);
        }
        return con;
    }
    
    public static int registrarPersona(Persona p){
        int status = 0;
        try{
            Connection con = consultaBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("insert into alumnosinscritos(nombre, apellido, boleta, semestre, idClub) values (?,?,?,?,?);");
            
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3,p.getBoleta());
            ps.setInt(4, p.getSemestre());
            ps.setInt(5, p.getIdClub());
            status = ps.executeUpdate();
            con.close();
            
        }catch(Exception e){
            System.out.println("Error en registrar persona");
            System.out.println(e);
        }
        return status;
    }
    
    
    public static int guardarClub(Club c){
        int status = 0;
        try{
            Connection con = consultaBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(
                    "insert into clubs(Nombre, JefeClub, Descripcion, Horario, CreditoPorHora, Cupo, OcupacionActual, Anuncios, Lugar) values(?,?,?,?,?,?,?,?,?);"
            );
            
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getJefeClub());
            ps.setString(3, c.getDescripcion());
            ps.setString(4, c.getHorario());
            ps.setString(5, c.getCreditoHora());
            ps.setInt(6, c.getCupo());
            ps.setInt(7,c.getOcupacionActual());
            ps.setString(8, c.getAnuncios());
            ps.setString(9, c.getLugar());
            status = ps.executeUpdate();
            con.close();
            
                    
        }catch(Exception e){
            System.out.println(e);
        }
        
        return status;
    }
    
    public static int actualizarClubOcupacion(Club c, int nuevaOcupacion){
        int status = 0;
        try{
             Connection con = consultaBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("update clubs set OcupacionActual=? where idClub=?;");
            ps.setInt(1, nuevaOcupacion);
            ps.setInt(2, c.getId());
            ps.executeUpdate();
            con.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
         return status;
    }
    
     public Club buscarClub(String nombreClub){
        Club c = new Club();
        
        
        try{
            Connection con = consultaBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("select * from clubs where Nombre=?;");
            ps.setString(1, nombreClub);
            ResultSet rs = ps.executeQuery();
          
            if(rs.next()){
                 c.setId(rs.getInt(1));
                 c.setNombre(rs.getString(2));
                 c.setJefeClub(rs.getString(3));
                 c.setDescripcion(rs.getString(4));
                 c.setHorario(rs.getString(5));
                 c.setCreditoHora(rs.getString(6));
                 c.setCupo(rs.getInt(7));
                 c.setOcupacionActual(rs.getInt(8));
                 c.setAnuncios(rs.getString(9));
                 c.setLugar(rs.getString(10));
            }
            
            con.close();
        }catch(Exception e){
            System.out.println("Excpecion en Obtener datos");
            System.out.println(e);
        } 
        
        return c;        
    }
      public Persona bucarPersonaporBoleta(String boleta){
          Persona p = new Persona();
          try{
              Connection con = consultaBD.obtenerConexion();
              PreparedStatement ps = con.prepareStatement("select * from alumnosinscritos where boleta=?;");
              ps.setString(1, boleta);
              ResultSet rs = ps.executeQuery();
              if(rs.next()){
                  p.setIdAlumno(rs.getInt(1));
                  p.setNombre(rs.getString(2));
                  p.setApellido(rs.getString(3));
                  p.setBoleta(rs.getString(4));
                  p.setSemestre(rs.getInt(5));
                  p.setIdClub(rs.getInt(6));      
              }
          }catch(Exception e){
            System.out.println("Excpecion buscar persona");
            System.out.println(e);
          }
          return p;
      }
            
      public Club buscarClubPorId(int idClub){
        Club c = new Club();
        
        
        try{
            Connection con = consultaBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("select * from clubs where idClub=?;");
            ps.setInt(1, idClub);
            ResultSet rs = ps.executeQuery();
          
            if(rs.next()){
                 c.setId(rs.getInt(1));
                 c.setNombre(rs.getString(2));
                 c.setJefeClub(rs.getString(3));
                 c.setDescripcion(rs.getString(4));
                 c.setHorario(rs.getString(5));
                 c.setCreditoHora(rs.getString(6));
                 c.setCupo(rs.getInt(7));
                 c.setOcupacionActual(rs.getInt(8));
                 c.setAnuncios(rs.getString(9));
                 c.setLugar(rs.getString(10));
            }
            
            con.close();
        }catch(Exception e){
            System.out.println("Excpecion en Obtener datos");
            System.out.println(e);
        } 
        
        return c;        
    }
      
      

       
    public boolean comprobarUsuario(String boleta, String contrasena){
        boolean statusSesion = false;
        String boletaBD = "";
        String passwordBD = "";
        
        try{
            Connection con = consultaBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("select * from usuarios where boleta=?;");
            ps.setString(1, boleta);
            ResultSet rs = ps.executeQuery();
            //Comprobamos si la contraseña en valida
            if(rs.next()){
                boletaBD = rs.getString(2);
                passwordBD = rs.getString(3);
            }
            
            System.out.println("USER:"+boletaBD+"PASS:"+passwordBD);
            if(contrasena.equals(passwordBD)){
                statusSesion = true;
            }
            
                    
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Usuario o contraseña invalidos");
        }
        

        return statusSesion;
    }
    
    public int obtenerIDUsuario(String boleta){
        int id = -1;
        try{
            Connection con = consultaBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("select * from usuarios where boleta=?;");
            ps.setString(1, boleta);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                id = rs.getInt(1);
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return id;
    }
    
    public static List<Club> getClubsInscritos(int idAlumno){
        List <Club> lista = new ArrayList<Club>();
        
        try{
            Connection con = consultaBD.obtenerConexion();
            String boleta = consultaBD.buscarBoletaPersonaPorId(idAlumno);
            PreparedStatement ps = con.prepareStatement("select * from clubs left join alumnosinscritos on clubs.idClub = alumnosinscritos.idClub where alumnosinscritos.boleta=?;");
            ps.setString(1, boleta);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Club c = new Club();
                 c.setId(rs.getInt(1));
                 c.setNombre(rs.getString(2));
                 c.setJefeClub(rs.getString(3));
                 c.setDescripcion(rs.getString(4));
                 c.setHorario(rs.getString(5));
                 c.setCreditoHora(rs.getString(6));
                 c.setCupo(rs.getInt(7));
                 c.setOcupacionActual(rs.getInt(8));
                 c.setAnuncios(rs.getString(9));
                 c.setLugar(rs.getString(10));

                 lista.add(c);
            }
            
            con.close();
        }catch(Exception e){
            System.out.println("Excpecion en Obtener datos");
            System.out.println(e);
        } 
        
        return lista;        
    }
    
    public int darDeBajaClub(int id){
      
        int status=0;  
        try{  
            
            
            Connection con = consultaBD.obtenerConexion();
            String boleta = consultaBD.buscarBoletaPersonaPorId(id);
            PreparedStatement ps=con.prepareStatement("DELETE FROM alumnosinscritos where boleta=?;");  
            ps.setString(1,boleta);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){
            System.out.println("ERROR AL DAR DE BAJA");
            e.printStackTrace();
        }  
          
        return status;       
        
    }
    
    public static String buscarBoletaPersonaPorId(int idPersona){
           String boleta = null;
        
        
        try{
            Connection con = consultaBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("select * from usuarios where idusuarios=?;");
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
          
            if(rs.next()){
                 boleta = rs.getString(2);
            }
            
            con.close();
        }catch(Exception e){
            System.out.println("Excpecion en Obtener datos");
            System.out.println(e);
        } 
        
        return boleta;        
    }
           
    public static List<Club> getAllClubs(){
        List <Club> lista = new ArrayList<Club>();
        
        try{
            Connection con = consultaBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM clubs");
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Club c = new Club();
                 c.setId(rs.getInt(1));
                 c.setNombre(rs.getString(2));
                 c.setJefeClub(rs.getString(3));
                 c.setDescripcion(rs.getString(4));
                 c.setHorario(rs.getString(5));
                 c.setCreditoHora(rs.getString(6));
                 c.setCupo(rs.getInt(7));
                 c.setOcupacionActual(rs.getInt(8));
                 c.setAnuncios(rs.getString(9));
                 c.setLugar(rs.getString(10));

                 lista.add(c);
            }
            
            con.close();
        }catch(Exception e){
            System.out.println("Excpecion en Obtener datos");
            System.out.println(e);
        } 
        
        return lista;        
    }
   
}
