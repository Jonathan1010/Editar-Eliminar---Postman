/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static java.lang.Integer.sum;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.Departamento;
import modelo.Empleado;

/**
 *
 * @author USUARIO
 */
@Stateless
@Path("modelo.empleado")
public class EmpleadoFacadeREST extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "empleadoPU")
    private EntityManager em;

    public EmpleadoFacadeREST() {
        super(Empleado.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Empleado entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Empleado entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Empleado find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empleado> findAll() {
        return super.findAll();
    }

    @POST
    @Path("obtener")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empleado> buscarEst() {
        return super.findAll();
    }

    @POST
    @Path("Hola")
    public List<Empleado> MENSAJE() {
        return super.findAll();
    }

    @GET
    @Path("holanombre")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String Holanombre(@QueryParam("nombre") String nombre) {
        return "Bienvenido" + nombre ;
    }

    @GET
    @Path("suma")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String result(@QueryParam("a") int a, @QueryParam("b") int b) {
        int resultado = a + b;
        return "El resultado es" + resultado;

    }

    @POST
    @Path("multiplicacion")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String multiplicar(@FormParam("a") int a, @FormParam("b") int b) {
        int result = a * b;
        return "El resultado es" + result;

    }

    @GET
    @Path("mayor")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String mayor(@QueryParam("a") int a, @QueryParam("b") int b) {
        if (a > b) {
            return "El mayor es a";

        } else 
            return "El mayor es b";
    }

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String crear (@FormParam("codigo") Integer codigo , @FormParam("nif") String nif, @FormParam("nombre") String nombre,
            @FormParam("apellido1") String apellido1, @FormParam("apellido2") String apellido2){
        Empleado objeto = new Empleado(codigo,nif,nombre,apellido1,apellido2);
        super.create(objeto);
        return "El objeto se inserto correctamente";
    }
    
    @POST
    @Path("read")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empleado> leer (@FormParam("apellido1") String apellido1){
        TypedQuery consulta = getEntityManager().createQuery("SELECT e FROM Empleado e WHERE e.apellido1 = :apellido1" , Empleado.class);
        consulta.setParameter("apellido1", apellido1);
        consulta.getResultList();
        //return ("Se leeo correctamente");
        return null;
     
    }
    
    
    
    
    
    
    
    
    @POST
    @Path("Editar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String Editar (@FormParam("codigo") Integer codigo, @FormParam("nif") String nif , @FormParam("nombre") String nombre , 
            @FormParam("apellido1") String apellido1 , @FormParam("apellido2") String apellido2){
        //llamar metodo clase padre SUPER
        Empleado objeto = super.find(codigo);
        
        objeto.setNif(nif);
        objeto.setNombre(nombre);
        objeto.setApellido1(apellido1);
        objeto.setApellido2(apellido2);
        
        super.edit(objeto);
        return "Se edito con exito";
        
    }
    
    @POST
    @Path("delete")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String eliminar(@FormParam ("codigo") String codigo){
        Empleado objeto = super.find(codigo);
        super.remove(objeto);
        return "Se elimino con exito";
        
    }
    
    
    
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empleado> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countREST() {
        return (super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
