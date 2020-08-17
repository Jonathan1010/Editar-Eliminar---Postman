/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.Departamento;

/**
 *
 * @author USUARIO
 */
@Stateless
@Path("modelo.departamento")
public class DepartamentoFacadeREST extends AbstractFacade<Departamento> {

    @PersistenceContext(unitName = "empleadoPU")
    private EntityManager em;

    public DepartamentoFacadeREST() {
        super(Departamento.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Departamento entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Departamento entity) {
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
    public Departamento find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Departamento> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Departamento> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    
    
    //Editar
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String editar(@FormParam ("codigo") int codigo, @FormParam("nombre") String nombre,
            @FormParam("presupuesto") double presupuesto , @FormParam("gastos") double gastos){
        Departamento objeto = super.find(codigo);
        objeto.setNombre(nombre);
        objeto.setPresupuesto(presupuesto);
        objeto.setPresupuesto(presupuesto);
        
        super.edit(objeto);
        return "El objeto se edito correctamente";     
    }
    
    //eliminar
    @POST
    @Path("eliminar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String eliminar (@FormParam("codigo") int codigo){
        
        Departamento objeto = super.find(codigo);
        
        super.remove(objeto);
        return "Se elmino correctamente";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
