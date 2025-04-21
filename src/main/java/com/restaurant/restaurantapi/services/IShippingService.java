package com.restaurant.restaurantapi.services;
import com.restaurant.restaurantapi.dtos.orders.OrdersDTO;
import com.restaurant.restaurantapi.entities.User;


import java.util.List;

public interface IShippingService {
    List<OrdersDTO> getPendingOrders(User user);
    OrdersDTO getOrderDetails(Long orderId, User user);
//    void updateShippingProfile(ShippingProfileDTO profile);
}
