package com.restaurant.restaurantapi.services.impl;



import com.restaurant.restaurantapi.dtos.orders.OrdersDTO;
import com.restaurant.restaurantapi.entities.OrderStatus;
import com.restaurant.restaurantapi.entities.Orders;
import com.restaurant.restaurantapi.entities.User;

import com.restaurant.restaurantapi.exceptions.AppException;
import com.restaurant.restaurantapi.exceptions.ErrorCode;

import com.restaurant.restaurantapi.mappers.OrdersMapper;
import com.restaurant.restaurantapi.repositories.OrdersRepository;
import com.restaurant.restaurantapi.repositories.UserRepository;
import com.restaurant.restaurantapi.services.IShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShippingService implements IShippingService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final OrdersMapper orderMapper;

    @Override
    public List<OrdersDTO> getPendingOrders(User user) throws AppException {
        List<Orders> orders = ordersRepository.findByStatus(OrderStatus.pending);
        return orders.stream()
                .map(orderMapper::toOrdersDTO)
                .collect(Collectors.toList());
    }
//
//    @Override
//    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
//        Orders order = ordersRepository.findById(orderId)
//                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
//
//        if (newStatus.ordinal() < order.getStatus().ordinal()) {
//            throw new AppException(ErrorCode.INVALID_ORDER_STATUS_CHANGE);
//        }
//
//        order.setStatus(newStatus);
//        ordersRepository.save(order);
//    }

    @Override
    public OrdersDTO getOrderDetails(Long orderId, User user) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        return orderMapper.toOrdersDTO(order);
    }

//    @Override
//    public void updateShippingProfile(ShippingProfileDTO profile) {
//        User shippingPerson = userRepository.findById(profile.getId())
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
//
//        shippingPerson.setFullName(profile.getFullName());
//        shippingPerson.setPhone(profile.getPhone());
//        shippingPerson.setAddress(profile.getAddress());
//        userRepository.save(shippingPerson);
//    }
}
