package es.progcipfpbatoi.todolistspring.controllers;

import es.progcipfpbatoi.todolistspring.exceptions.NotFoundException;
import es.progcipfpbatoi.todolistspring.exceptions.TareaYaExistenteException;
import es.progcipfpbatoi.todolistspring.model.entities.Tarea;
import es.progcipfpbatoi.todolistspring.model.entities.TipusPrioritat;
import es.progcipfpbatoi.todolistspring.model.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    @GetMapping("/tarea-form")
    public String tareaFormActionView(){
        return "tarea_form_view";
    }

    @PostMapping(value = "/tarea-add")
    @ResponseBody
    public String postAddAction(@RequestParam Map<String, String> params){
        int code = Integer.parseInt(params.get("code"));
        String user = params.get("user");
        String descripcion = params.get("description");
        TipusPrioritat prioritat = TipusPrioritat.fromText(params.get("prioritat"));
        boolean realizada = Boolean.parseBoolean(params.get("realizada"));
        LocalDate fechaVencimiento = LocalDate.parse(params.get("fechaVencimiento"));
        LocalTime horaVienciment = LocalTime.parse(params.get("horaVienciment"));
        
        Tarea tarea = new Tarea(code, user, descripcion, prioritat, realizada, fechaVencimiento ,horaVienciment);
        
        try {
        tareaRepository.add(tarea);	
        return "<html>" +
                "<body>Tarea " + tarea.getCodigo() + " recibida con éxito</body>" +
                "</html>";
        }catch(TareaYaExistenteException e) {
        	return e.getMessage();
        }catch(IllegalArgumentException e) {
        	return e.getMessage();
        }
    }
    
    @GetMapping("/tarea")
    @ResponseBody
    public String getTareaByCodigo(@RequestParam int code) {
        try {
            Tarea tarea = tareaRepository.get(code);
            return "<html><body>" +
                    "<h1>Tarea seleccionada</h1>" +
                    "<p>Codi: " + tarea.getCodigo() + "</p>" +
                    "<p>Usuario: " + tarea.getNombre() + "</p>" +
                    "<p>Descripcion: " + tarea.getDescripcion() + "</p>" +
                    "<p>Prioridad: " + tarea.getPrioritat() + "</p>" +
                    "<p>Realitzada: " + tarea.getIsRealizada() + "</p>" +
                    "<p>Venciment: " + tarea.getFechaVencimiento() + " a las " + tarea.getHoraVienciment() + " horas</p>" +
                    "</body></html>";
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }
    
    @GetMapping("/tarea-delete")
    @ResponseBody
    public String deleteTareaByCodigo(@RequestParam int code) {
        try {
            Tarea tarea = tareaRepository.get(code);
            tareaRepository.delete(tarea);
            return "Tarea " + code + " borrada con exito";
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }
    
    @GetMapping("/tareas")
    @ResponseBody
    public String listarTareas() {
        String filas = "";
        for (Tarea tarea : tareaRepository.findAll()) {
            filas += "<tr>" +
                     "<td>" + tarea.getCodigo() + "</td>" +
                     "<td>" + tarea.getDescripcion() + "</td>" +
                     "<td>" + tarea.getFechaVencimiento() + "</td>" +
                     "<td>" + tarea.getNombre() + "</td>" +
                     "<td>" + (tarea.getIsRealizada() ? "Sí" : "No") + "</td>" +
                     "<td><a href='/tarea?code=" + tarea.getCodigo() + "'>Ver detalle</a></td>" +
                     "</tr>";
        }

        return "<html><body>" +
               "<h1>Listado de tareas</h1>" +
               "<table border='1' style='border-collapse: collapse;'>" +
               "<tr>" +
               "<th>Codigo</th>" +
               "<th>Descripcion</th>" +
               "<th>Doas Vencimiento</th>" +
               "<th>Responsable</th>" +
               "<th>Realizado</th>" +
               "<th>Acciones</th>" +
               "</tr>" +
               filas + 
               "</table>" +
               "</body></html>";
    }
    

}
