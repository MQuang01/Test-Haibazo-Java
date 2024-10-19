package com.example.haibazotest.service.imp;

import com.example.haibazotest.domain.User;
import com.example.haibazotest.domain.dto.req.user.LoginReqDTO;
import com.example.haibazotest.domain.dto.req.user.RegisterReqDTO;
import com.example.haibazotest.domain.dto.res.order.OrderResDTO;
import com.example.haibazotest.domain.dto.res.user.UserResDTO;
import com.example.haibazotest.mapper.OrderMapper;
import com.example.haibazotest.mapper.UserMapper;
import com.example.haibazotest.respository.IUserRepository;
import com.example.haibazotest.respository.IOrderRepository;
import com.example.haibazotest.service.IUserService;
import com.example.haibazotest.until.ErrorCode;
import com.example.haibazotest.until.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    IUserRepository userRepository;
    UserMapper userMapper = UserMapper.INSTANCE;
    OrderMapper orderMapper = OrderMapper.INSTANCE;

    private final IOrderRepository orderRepository;

    @Override
    public UserResDTO login(LoginReqDTO loginReqDTO) {
        User user = userRepository.findByUsernameAndPassword(loginReqDTO.getUsername(), loginReqDTO.getPassword());
        if (user == null) throw new NoSuchElementException(ErrorCode.USER_NOT_FOUND.getMessage());
        List<OrderResDTO> listOrder = orderRepository.findByUserId(user.getId()).stream().map(orderMapper::toOrderResDTO).toList();
        UserResDTO userResDTO = userMapper.toUserResDTO(user);
        userResDTO.setListOrder(listOrder);
        return userResDTO;
    }

    @Override
    public UserResDTO register(RegisterReqDTO registerReqDTO) {
        if(userRepository.findUserByUserName(registerReqDTO.getUsername()) != null){
            throw new IllegalArgumentException(ErrorCode.USERNAME_EXISTS.getMessage());
        }
        User newUser = userMapper.toUser(registerReqDTO);
        User savedUser = userRepository.save(newUser);
        UserResDTO userResDTO = userMapper.toUserResDTO(savedUser);
        userResDTO.setListOrder(null);
        return userResDTO;
    }

    @Override
    public List<UserResDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserResDTO> listUserResDTO = new ArrayList<>();
        for(User user : users){
            UserResDTO userResDTO = userMapper.toUserResDTO(user);
            userResDTO.setListOrder(orderRepository.findByUserId(user.getId()).stream().map(orderMapper::toOrderResDTO).toList());
            listUserResDTO.add(userResDTO);
        }
        return listUserResDTO;
    }

    @Override
    public UserResDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ErrorCode.USER_NOT_FOUND.getMessage()));
        UserResDTO userResDTO = userMapper.toUserResDTO(user);
        userResDTO.setListOrder(orderRepository.findByUserId(user.getId()).stream().map(orderMapper::toOrderResDTO).toList());
        return userResDTO;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResDTO updateUserById(Long id, RegisterReqDTO registerReqDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ErrorCode.USER_NOT_FOUND.getMessage()));

        ObjectMapper.mapNonNullFields(registerReqDTO, existingUser);
        User savedUser = userRepository.save(existingUser);

        UserResDTO userResDTO = userMapper.toUserResDTO(savedUser);
        userResDTO.setListOrder(orderRepository.findByUserId(id).stream().map(orderMapper::toOrderResDTO).toList());

        return userResDTO;
    }
}
