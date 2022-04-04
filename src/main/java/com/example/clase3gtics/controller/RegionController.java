package com.example.clase3gtics.controller;

import com.example.clase3gtics.entity.Region;
import com.example.clase3gtics.entity.Territory;
import com.example.clase3gtics.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/listarRegiones")
    public String listarRegiones(Model model) {
        List<Region> listaRegiones = regionRepository.findAll();
        model.addAttribute("listaRegiones",listaRegiones);
        return "region/listaRegion";
    }

    @GetMapping("/nuevoForm")
    public String nuevoFormRegiones(){
        return "region/newRegion";
    }

    @PostMapping("/crear")
    public String saveShip(Region region, RedirectAttributes attributes) {

        if(region.getRegiondescription().equalsIgnoreCase("")){
            attributes.addFlashAttribute("msg","La descripcion de la region no puede estar vacia");
            return "redirect:/region/listarRegiones";
        }
        attributes.addFlashAttribute("msg","Region creado exitosamente.");
        regionRepository.save(region);
        return "redirect:/region/listarRegiones";
    }

    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") String iD, RedirectAttributes attributes) {
        regionRepository.deleteById(Integer.parseInt(iD));
        attributes.addFlashAttribute("msg","Region borrado exitosamente.");
        return "redirect:/region/listarRegiones";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") String id, Model model) {
        Optional<Region> optionalRegion = regionRepository.findById(Integer.parseInt(id));
        model.addAttribute("Region",optionalRegion.get());
        return "region/editRegion";
    }

    @PostMapping("/updateRegion")
    public String updateRegion(Region region, RedirectAttributes attributes) {
        if(region.getRegiondescription().equalsIgnoreCase("")){
            attributes.addFlashAttribute("msg","La descripcion de la region no puede estar vacia");
            return "redirect:/region/listarRegiones";
        }

        attributes.addFlashAttribute("msg","Region editado exitosamente.");
        regionRepository.save(region);
        return "redirect:/region/listarRegiones";
    }

}
