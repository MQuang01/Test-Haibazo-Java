package com.example.haibazotest.service.imp;

import com.example.haibazotest.domain.Order;
import com.example.haibazotest.domain.OrderDetail;
import com.example.haibazotest.domain.Product;
import com.example.haibazotest.domain.User;
import com.example.haibazotest.domain.dto.req.order.OrderDetailReqDTO;
import com.example.haibazotest.domain.dto.req.order.OrderReqDTO;
import com.example.haibazotest.domain.dto.res.order.OrderResDTO;
import com.example.haibazotest.domain.enumeration.EStatusOrder;
import com.example.haibazotest.mapper.OrderDetailMapper;
import com.example.haibazotest.mapper.OrderMapper;
import com.example.haibazotest.respository.IOrderDetailRepository;
import com.example.haibazotest.respository.IOrderRepository;
import com.example.haibazotest.respository.IProductRepository;
import com.example.haibazotest.respository.IUserRepository;
import com.example.haibazotest.service.IOrderService;
import com.example.haibazotest.until.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {
    IOrderRepository orderRepository;
    IOrderDetailRepository orderDetailRepository;
    IProductRepository productRepository;
    IUserRepository userRepository;
    OrderMapper orderMapper = OrderMapper.INSTANCE;
    OrderDetailMapper orderDetailMapper = OrderDetailMapper.INSTANCE;

    @Override
    public List<OrderResDTO> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResDTO> listOrderResDTO = new ArrayList<>();
        for (Order order : orders) {
            OrderResDTO orderResDTO = orderMapper.toOrderResDTO(order);
            orderResDTO.setListOrderDetail(orderDetailRepository.findByOrderId(order.getId()).stream().map(orderDetailMapper::toOrderDetailResDTO).toList());
            listOrderResDTO.add(orderResDTO);
        }
        return listOrderResDTO;
    }

    @Override
    public OrderResDTO getOrderById(Long id) {
        OrderResDTO orderResDTO = orderMapper.toOrderResDTO(orderRepository.findById(id).get());
        orderResDTO.setListOrderDetail(orderDetailRepository.findByOrderId(id).stream().map(orderDetailMapper::toOrderDetailResDTO).toList());
        return orderResDTO;
    }

    @Override
    public OrderResDTO createOrder(OrderReqDTO orderReqDTO) {
        User user = userRepository.findById(Long.valueOf(orderReqDTO.getUserId()))
                .orElseThrow(() -> new NoSuchElementException(ErrorCode.ID_NOT_FOUND.getMessage()));

        Order order = orderMapper.toOrder(orderReqDTO);
        initializeOrder(order, user, orderReqDTO);

        order = orderRepository.save(order);

        List<OrderDetail> listOrderDetail = createOrderDetails(orderReqDTO.getListOrderDetail(), order);
        order.setListOrderDetail(listOrderDetail);
        order.setTotal(listOrderDetail.stream().mapToDouble(orderDetail -> orderDetail.getPrice()).sum());
        orderRepository.save(order); // Lưu order với danh sách chi tiết đơn hàng

        return orderMapper.toOrderResDTO(order);
    }

    private void initializeOrder(Order order, User user, OrderReqDTO orderReqDTO) {
        order.setUser(user);
        order.setAddress(orderReqDTO.getAddress());
        order.setEmail(orderReqDTO.getEmail());
        order.setPhone(orderReqDTO.getPhone());
        order.setDescription(orderReqDTO.getDescription());
        order.setCreatedDate(java.time.LocalDateTime.now());
        order.setStatusOrder(EStatusOrder.WAITING);
    }

    private List<OrderDetail> createOrderDetails(List<OrderDetailReqDTO> orderDetailReqDTOs, Order order) {
        List<OrderDetail> listOrderDetail = new ArrayList<>();

        for (OrderDetailReqDTO orderDetailReqDTO : orderDetailReqDTOs) {
            OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailReqDTO);

            Product product = productRepository.findById(Long.valueOf(orderDetailReqDTO.getProductId()))
                    .orElseThrow(() -> new NoSuchElementException(ErrorCode.ID_NOT_FOUND.getMessage()));

            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            int quantity = Integer.parseInt(orderDetailReqDTO.getQuantity());
            orderDetail.setPrice(product.getPrice() * quantity);

            listOrderDetail.add(orderDetail);

            product.setQuantity(product.getQuantity() - quantity);
            orderDetailRepository.save(orderDetail);
        }

        return listOrderDetail;
    }


    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResDTO> getOrderByUserId(Long aLong) {
        return orderRepository.findByUserId(aLong).stream().map(orderMapper::toOrderResDTO).toList();
    }
}
