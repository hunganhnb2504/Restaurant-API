package com.restaurant.restaurantapi.controllers;


import com.restaurant.restaurantapi.dtos.ResponseObject;
import com.restaurant.restaurantapi.dtos.orders.OrdersDTO;
import com.restaurant.restaurantapi.entities.OrderStatus;
import com.restaurant.restaurantapi.entities.User;
import com.restaurant.restaurantapi.services.impl.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    // API: Lấy danh sách đơn hàng cần giao
    @GetMapping("/orders")
    public ResponseEntity<ResponseObject> getOrdersForShipping() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        List<OrdersDTO> orders = shippingService.getPendingOrders(currentUser);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(true, 200, "ok", orders)
        );
    }



    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrdersDTO> getOrderDetails(@PathVariable Long orderId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        OrdersDTO order = shippingService.getOrderDetails(orderId, currentUser);
        return ResponseEntity.ok(order);
    }
//
//    @PutMapping("/profile")
//    public ResponseEntity<Void> updateShippingProfile(@RequestBody ShippingProfileDTO profile) {
//        shippingService.updateShippingProfile(profile);
//        return ResponseEntity.ok().build();
//    }

    //    // API: Cập nhật trạng thái đơn hàng
//    @PutMapping("/order/{orderId}/status")
//    public ResponseEntity<Void> updateOrderStatus(
//            @PathVariable Long orderId,
//            @RequestBody OrderStatus newStatus) {
//        shippingService.updateOrderStatus(orderId, newStatus);
//        return ResponseEntity.ok().build();
//    }
}
