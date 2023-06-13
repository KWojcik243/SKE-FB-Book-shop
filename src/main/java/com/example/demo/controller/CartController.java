package com.example.demo.controller;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        try{
            cartService.updateCartByBook(userEmail, orderItemDTO);
            return ResponseEntity.ok().body("Book with id " + orderItemDTO.getBookId() + " added to cart succesfully.");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<Cart> getCartByEmail(@PathVariable String userEmail){
        try {
            Cart cart = cartService.getCartByEmail(userEmail);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
