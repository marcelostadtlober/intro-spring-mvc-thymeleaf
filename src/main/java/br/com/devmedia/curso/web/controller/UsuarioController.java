package br.com.devmedia.curso.web.controller;

import br.com.devmedia.curso.dao.UsuarioDao;
import br.com.devmedia.curso.domain.TipoSexo;
import br.com.devmedia.curso.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDao dao;

    @GetMapping("/todos")
    public ModelAndView home(ModelMap model) {
        model.addAttribute("usuarios", dao.getTodos());
        return new ModelAndView("/user/list", model);
    }

    @GetMapping("/nome")
    public ModelAndView listarPorNome(@RequestParam(value = "nome") String nome, ModelMap model) {

        if (nome == null) {
            return new ModelAndView("redirect:/usuario/todos");
        }

        model.addAttribute("usuarios", dao.getByNome(nome));
        return new ModelAndView("/user/list", model);
    }

    @GetMapping("/sexo")
    public ModelAndView listarPorSexo(@RequestParam(value = "tipoSexo") TipoSexo sexo, ModelMap model) {

        if (sexo == null) {
            return new ModelAndView("redirect:/usuario/todos");
        }

        model.addAttribute("usuarios", dao.getBySexo(sexo));
        return new ModelAndView("/user/list", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro(Usuario usuario) {

        return new ModelAndView("/user/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Usuario usuario, BindingResult result, RedirectAttributes attrib) {

        if (result.hasErrors()) {
            return new ModelAndView("/user/add");
        }

        dao.salvar(usuario);
        attrib.addFlashAttribute("message", "Registro inserido com sucesso.");
        return new ModelAndView("redirect:/usuario/todos");
    }

    @GetMapping("/update/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("usuario", dao.getId(id));
        return new ModelAndView("/user/add", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Usuario usuario, BindingResult result, RedirectAttributes attrib) {

        if (result.hasErrors()) {
            return new ModelAndView( "/user/add");
        }

        dao.editar(usuario);
        attrib.addFlashAttribute("message", "Registro editado com sucesso.");
        return new ModelAndView("redirect:/usuario/todos");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attrib) {

        dao.excluir(id);
        attrib.addFlashAttribute("message", "Usuário removido com sucesso.");
        return "redirect:/usuario/todos";
    }

    @ModelAttribute("sexos")
    public TipoSexo[] tipoSexo() {
        return TipoSexo.values();
    }
}
