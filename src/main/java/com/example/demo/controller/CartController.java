package com.example.demo.controller;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{userEmail}")
    public ResponseEntity<String> putItemInCart(@PathVariable String userEmail, @RequestBody OrderItemDTO orderItemDTO) {
        try {
            cartService.updateCartByBook(userEmail, orderItemDTO);
            return ResponseEntity.ok().body("Book with id " + orderItemDTO.getBookId() + " added to cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<EntityModel<Cart>> getCartByEmail(@PathVariable String userEmail) {
        try {
            Cart cart = cartService.getCartByEmail(userEmail);
            if (cart == null) {
                return ResponseEntity.notFound().build();
            }

            EntityModel<Cart> cartResource = EntityModel.of(cart);
            String uri = "/carts/" + userEmail;
            cartResource.add(Link.of(getBaseUrl() + uri).withSelfRel());
            cartResource.add(Link.of(getBaseUrl() + uri).withRel("POST"));

            return ResponseEntity.ok(cartResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    private String getBaseUrl() {
        String requestUrl = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
        return requestUrl.replaceFirst("/carts.*", "");
    }
}
