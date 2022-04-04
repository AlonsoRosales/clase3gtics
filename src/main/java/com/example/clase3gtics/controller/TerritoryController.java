package com.example.clase3gtics.controller;


import com.example.clase3gtics.entity.Region;
import com.example.clase3gtics.entity.Territory;
import com.example.clase3gtics.repository.RegionRepository;
import com.example.clase3gtics.repository.TerritoryRepository;
import com.example.clase3gtics.entity.Territory;
import com.example.clase3gtics.repository.RegionRepository;
import com.example.clase3gtics.repository.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/territory")
public class TerritoryController {

    @Autowired
    TerritoryRepository territoryRepository;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/listarTerritorios")
    public String listarTerritorios(Model model) {
        List<Territory> listaTerritorios = territoryRepository.findAll();
        model.addAttribute("listaterritorios",listaTerritorios);
        return "territories/listaTerritory";
    }

    @GetMapping("/nuevoForm")
    public String nuevoFormTerritorios(Model model){
        List<Region> listita = regionRepository.findAll();
        model.addAttribute("lista",listita);
        return "territories/newTerritory";
    }

    @PostMapping("/crear")
    public String saveShip(Territory territorio, RedirectAttributes attributes) {
        Optional<Territory> territo = territoryRepository.findById(territorio.getId());
        if(territo.isPresent()){
            attributes.addFlashAttribute("msg","El ID ya existe.Porfavor elegir otro.");
            return "redirect:/territory/listarTerritorios";
        }
        if(territorio.getTerritorydescription().equalsIgnoreCase("")){
            attributes.addFlashAttribute("msg","La descripcion del territorio no puede estar vacia");
            return "redirect:/territory/listarTerritorios";
        }
        attributes.addFlashAttribute("msg","Territorio creado exitosamente.");
        territoryRepository.save(territorio);
        return "redirect:/territory/listarTerritorios";
    }

    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") String iD) {
        territoryRepository.deleteById(iD);
        return "redirect:/territory/listarTerritorios";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") String id, Model model) {
        Optional<Territory> optionalTerritorio = territoryRepository.findById(id);
        if(optionalTerritorio.isPresent()){
            Territory territorio = optionalTerritorio.get();
            model.addAttribute("territorio",territorio);

            List<Region> lista = regionRepository.findAll();

            //------------------------------------------------------------------
            int index = 0;
            for(Region region: lista){
                /*if(region.getId() == territorio.getRegionid().getId()){
                    break;
                }*/
                if(region.getId() == territorio.getRegionid()){
                    break;
                }
                index++;
            }
            Region regioncita = lista.remove(index);
            List<Region> listita = new ArrayList<>();
            listita.add(regioncita);

            for(Region region: lista){
                listita.add(region);
            }
            //------------------------------------------------------------------

            model.addAttribute("listita",listita);

            return "territories/editTerritory";
        }

        return "redirect:/territory/listarTerritorios";
    }

    @PostMapping("/updateTerritorio")
    public String updateTerritorio(Territory territorio, RedirectAttributes attributes) {
        if(territorio.getTerritorydescription().equalsIgnoreCase("")){
            attributes.addFlashAttribute("msg","La descripcion del territorio no puede estar vacia");
            return "redirect:/territory/listarTerritorios";
        }

        territoryRepository.save(territorio);
        return "redirect:/territory/listarTerritorios";
    }

}
