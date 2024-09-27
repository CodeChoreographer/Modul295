package com.modul295_lb1.productmanager.resources;

import com.modul295_lb1.productmanager.resources.categories.CategoryRepository;
import com.modul295_lb1.productmanager.resources.products.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("frontend")
public class FrontendController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public FrontendController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("index")
    public String getIndexPage(Model model){

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }
}
