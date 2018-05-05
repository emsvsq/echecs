package webservice;

import Game.JsAnswer;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

@Path("echec")
public class mainengine extends HttpServlet{
    @Context
    private UriInfo context;
    public Game.Engine engine;
             
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }        
    
    @Context
    private HttpServletRequest request;
    
    /*@Path("test/{empno}")
    @GET   // this method process GET request from client
    //@Produces("application/json")   // sends JSON
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("empno") int empno) {  // empno represents the empno sent from client
       HttpSession session = request.getSession(true);
       engine = (Game.Engine)session.getAttribute("Engine");
       
    switch(empno) {
          case 1:
              return "{'name':'George Koch', 'age':58}";
              //return "{'name':'George Koch', 'age':'" + engine.test +"'}";
          case 2:
              return "{'name':'Peter Norton', 'age':50}";
          default:
              return "{'name':'unknown', 'age':-1}";
      } // end of switch
   } // end of getJson()
   */
    
    @Path("status/{status}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getStatus(@PathParam("status") String status) { 
        HttpSession session = request.getSession(true);
       request.setAttribute("Engine", engine);
       engine = (Game.Engine)session.getAttribute("Engine");
       //request.setAttribute("Engine", engine);
       
       //return "{'html':'" + engine.RenderOutput() + "'}";
       //String returnValue = "{'html':'" + engine.RenderOutput() + "'";
       String returnValue = "{'html':'" + engine.Stringify() + "'";
       returnValue+= ", 'zoom':'" + engine.Zoom() + "'";
       returnValue+= "}";
       //return returnValue;
       return Response.status(200).header("Access-Control-Allow-Origin", "*")
               .header("Access-Control-Allow-Methods", "GET")
                .entity(returnValue).type(MediaType.APPLICATION_JSON).build();
   }
    
    @Path("play/{coord}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response play(@PathParam("coord") String coord) {
       HttpSession session = request.getSession(true);
       engine = (Game.Engine)session.getAttribute("Engine");
       //Verifier coordonnées
       boolean bMoving = engine.Move(coord);
       //request.setAttribute("Engine", engine);
       boolean isUselessToRefresh = engine.isCaseVide(coord);
       String returnValue = "";
       //if (!isUselessToRefresh) {
            //returnValue = "{'html':'" + engine.RenderOutput() + "'";
            returnValue = "{'html':'" + engine.Stringify() + "'";
            if (engine.GetEchec() && bMoving) {
                returnValue+= ", 'echec':'" + engine.sMessageEchec + "'";
            }
            returnValue+= "}";
       //}
       //return returnValue;
       return Response.status(200).header("Access-Control-Allow-Origin", "*")
               .header("Access-Control-Allow-Methods", "GET")
                .entity(returnValue).type(MediaType.APPLICATION_JSON).build();
       //return "{'html':'" + engine.RenderOutput() + "'}";
   }
    
    @Path("options/{option}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response setOption(@PathParam("option") String option) {
       HttpSession session = request.getSession(true);
       engine = (Game.Engine)session.getAttribute("Engine");
       
       String options = option.trim().toLowerCase();
       switch(options.toLowerCase()) {
           case "aide" : engine.SetHelp(!engine.Help());
                        break;
           case "zoomplus" : engine.SetZoom("zoomplus");
               break;
           case "zoommoins" : engine.SetZoom("zoommoins");
               break;
           case "singleplayer" : 
               engine.Restart();
               engine.SetIa(true);
               break;
           case "multiplayer" : 
               engine.Restart();
               engine.SetIa(false);
               break;
           case "cancel" : engine.Cancel();
               break; 
           default:
               break;
       }
              // engine.SetHelp(!engine.Help());
       
       /*String returnValue = "{'html':'" + engine.RenderOutput() + "'";
       if (engine.GetEchec()) {
           returnValue+= ", 'echec':'" + engine.sMessageEchec + "'";
       }
       returnValue+= "}";*/
       if (!("aide").equals(options)) {
            //String returnValue = "{'html':'" + engine.RenderOutput() + "'";
            String returnValue = "{'html':'" + engine.Stringify() + "'";
            returnValue+= ", 'zoom':'" + engine.Zoom() + "'";
            //returnValue+= ", 'test':'" + engine.game.Afficher(engine.game.listeSauvegarde.get(engine.game.listeSauvegarde.size() - 1)) + "'";
            returnValue+= "}";
            //return returnValue;
            return Response.status(200).header("Access-Control-Allow-Origin", "*")
               .header("Access-Control-Allow-Methods", "*")
                .entity(returnValue).type(MediaType.APPLICATION_JSON).build();
       } else {
           String returnValue = "{'aide':'" + engine.Help() + "'}";
           //return "{'aide':'" + engine.Help() + "'}";
           return Response.status(200).header("Access-Control-Allow-Origin", "*")
               .header("Access-Control-Allow-Methods", "*")
                .entity(returnValue).type(MediaType.APPLICATION_JSON).build();
       }
       
       //return "{'html':'" + engine.RenderOutput() + "'}";
   }
    
    
    @Path("dialog/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response setDialogContent(@PathParam("type") String type) {
       HttpSession session = request.getSession(true);
       engine = (Game.Engine)session.getAttribute("Engine");
       
       String returnValue = "Impossible de satisfaire la requête";
       String options = type.trim().toLowerCase();
       
       switch(options.toLowerCase()) {
           case "save" : 
               returnValue = engine.Sauvegarder();
               break;
           case "load" : 
               returnValue = engine.Charger();
               break;
           case "joinmultiplayer" : 
               returnValue = engine.RejoindrePartie();
               break;
           default:
               break;
       }
       returnValue = "{'dialog':'" + returnValue + "'}";
       //return "{'dialog':'" + returnValue + "'}";
       return Response.status(200).header("Access-Control-Allow-Origin", "*")
               .header("Access-Control-Allow-Methods", "GET")
                .entity(returnValue).type(MediaType.APPLICATION_JSON).build();
   }
    
    @Path("executedialogsave/{type}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response excecuteDialogSave(@PathParam("type") String name) {
       HttpSession session = request.getSession(true);
       engine = (Game.Engine)session.getAttribute("Engine");
       
       JsAnswer bExecuted = engine.Sauvegarder(name);
       //String returnValue = Stringify(bExecuted.exception);
       String returnValue = Stringify("Impossible d'établir une connexion");
       if (bExecuted.returnBoolValue) {
           returnValue = "true";
       }
       returnValue = "{'dialog':'" + returnValue + "'}";
       //return "{'dialog':'" + returnValue + "'}";
       return Response.status(200).header("Access-Control-Allow-Origin", "*")
               .header("Access-Control-Allow-Methods", "*")
                .entity(returnValue).type(MediaType.APPLICATION_JSON).build();
   }
    
    @Path("executedialogload/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response excecuteDialogLoad(@PathParam("type") int idRow) {
       HttpSession session = request.getSession(true);
       engine = (Game.Engine)session.getAttribute("Engine");
       
       boolean bExecuted = engine.Charger(idRow);
       
       //String returnValue = engine.RenderOutput();
       String returnValue = engine.Stringify();
        
       returnValue = "{'loaded':'" + bExecuted + "', 'html':'" + returnValue + "'}";
       //return "{'dialog':'" + returnValue + "'}";
       return Response.status(200).header("Access-Control-Allow-Origin", "*")
               .header("Access-Control-Allow-Methods", "GET")
                .entity(returnValue).type(MediaType.APPLICATION_JSON).build();
   }
    
    @Path("executedialogdelete/{rowid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response excecuteDialogDelete(@PathParam("rowid") int idRow) {
       HttpSession session = request.getSession(true);
       engine = (Game.Engine)session.getAttribute("Engine");
       
       boolean bExecuted = engine.DeleteRow(idRow);
        
       String returnValue = "{'loaded':'" + bExecuted + "'}";
       //return "{'dialog':'" + returnValue + "'}";
       return Response.status(200).header("Access-Control-Allow-Origin", "*")
               .header("Access-Control-Allow-Methods", "*")
                .entity(returnValue).type(MediaType.APPLICATION_JSON).build();
   }
    
    public String Stringify(String text) {
        String returnValue = text;
        returnValue = returnValue.replace("'", " ").replace("\"", " ");
        returnValue = returnValue.replaceAll("[\\t\\n\\r]"," ");
        return returnValue;
    }
    /*@Path("executedialogdelete/{rowid}")
    @DELETE
    public void excecuteDialogDelete(@PathParam("rowid") int idRow) {
       HttpSession session = request.getSession(true);
       engine = (Game.Engine)session.getAttribute("Engine");
       boolean bExecuted = engine.DeleteRow(idRow);
   }*/
}
