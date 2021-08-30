package java.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletEjercicio1", urlPatterns = {"/ServletEjercicio1"})
public class ServletEjercicio1 extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String nombre = request.getParameter("nombre");
            String monto = request.getParameter("monto");
            String cuota = request.getParameter("cuota");
            String seguro1 = request.getParameter("seguro");
            String seguro2 = request.getParameter("seguro2");
            double ufCLP = 27284.16;
            double Interes=0;
            double cuotaMensual=0;
            double Total=0;
            //Valida que el nombre y el monto no sean incorrectos segun mis funciones
            if(verificaNombre(nombre)==false||verifica(monto)==false||!(cuota!=null)||
                    !(seguro1!=null)||!(seguro2!=null))
            {
                request.setAttribute("error","Hubo un error.");
                request.setAttribute("name", nombre);
                request.setAttribute("uf", monto);
                request.setAttribute("cuotas",cuota);
                request.setAttribute("seguro1",seguro1);
                request.setAttribute("seguro2", seguro2);
            }
            else{
                //Calcula el interes segun las condiciones del enunciado
                if (Integer.parseInt(cuota)<=10){Interes=0.3;}
                else if (Integer.parseInt(cuota)>10&&Integer.parseInt(cuota)<=20){Interes=0.35;}
                else {Interes=0.4;}
                //Suma interes si es que se selecciona uno o mas seguros
                if(seguro1.equals("Si")){Interes=Interes+0.1;}
                if(seguro2.equals("Si")){Interes=Interes+0.05;}
                //Se calcula el total del monto, que es monto + monto*interes (todo esto en CLP)
                Total = (Double.parseDouble(monto)+(Double.parseDouble(monto)*Interes))*ufCLP;
                //La cuota mensual se calcula dividiendo el valor total, en el numero de cuotas(año) y luego
                //dividiendo por 12 (porque son 12 meses por año) y por conversion de unidades queda en
                //cuota mensual
                cuotaMensual=(Total/Integer.parseInt(cuota))/12;

                request.setAttribute("seguro1",seguro1);
                request.setAttribute("seguro2", seguro2);
                request.setAttribute("cuotaMensual",cuotaMensual);
                request.setAttribute("finalizado", "Todo se hizo bien.");
                request.setAttribute("name", nombre);
                request.setAttribute("uf", monto);
                request.setAttribute("cuotas",cuota);
                request.setAttribute("Interes", Interes*100);
                request.setAttribute("Total",Total);
                request.setAttribute("InteresValor",Interes*Total);
                
            }
            request.getRequestDispatcher("Ejercicio1.jsp").forward(request,response);
        }
    }
    
    //Valida que el nombre solo contenga letras
    public boolean verificaNombre(String nombre){
        try {
            if (!(nombre!=null)){return false;}
            
            if(nombre.matches("[a-zA-Z]+")){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            
            }
        return false;
        
    }
    //Valida que el monto sea un numero y que sea valido
    public boolean verifica(String valor){
        try {
            if (!(valor!=null)){return false;}
            
            if (Double.parseDouble(valor)<=0){
                return false;
            }            
        } catch (Exception e) {
            return false;
        }
        return true;
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
