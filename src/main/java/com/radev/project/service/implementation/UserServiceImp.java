package com.radev.project.service.implementation;

import com.radev.project.dao.RoleRepository;
import com.radev.project.dao.UserRepository;
import com.radev.project.dto.MetaData;
import com.radev.project.dto.PageTemplate;
import com.radev.project.dto.user.UserRegister;
import com.radev.project.dto.user.UserUpdate;
import com.radev.project.entity.Role;
import com.radev.project.entity.User;
import com.radev.project.service.abstraction.AuthService;
import com.radev.project.service.abstraction.CrudService;
import com.radev.project.service.abstraction.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("user")
public class UserServiceImp implements CrudService,UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<?> findAll() {
        return userRepository.findAll();
    }
    @Override
    public Object create(Object payload) {
        UserRegister userRegister = (UserRegister) payload;

        if (userRepository.existsByUsername(userRegister.getUsername())) {
            throw new EntityExistsException("username is already used!");
        }

        if (userRepository.existsByEmail(userRegister.getEmail())) {
            throw new EntityExistsException("Email is already used!");
        }

        // Create new user's account
        User user = new User(
                userRegister.getUsername(),
                userRegister.getEmail(),
                passwordEncoder.encode(userRegister.getPassword()));
        user.setCreatedBy(authService.getCurrentUser().getId().toString());

        var roleIds = userRegister.getRoleIds();
        List<Role> roles;
        if (roleIds == null || roleIds.size() == 0) {
            throw new RuntimeException("Role must be set!");
        } else {
            roles = roleRepository.findAllById(roleIds);
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }
    @Override
    public Object update(Object payload) {
        UserUpdate userUpdate = (UserUpdate) payload;

        var user = userRepository.findById(userUpdate.getId())
                .orElseThrow(()-> new EntityNotFoundException("user not found for id : "+ userUpdate.getId()));

        if (!user.getUsername().equals(userUpdate.getUsername()) && userRepository.existsByUsername(userUpdate.getUsername())) {
            throw new EntityExistsException("Username is already used by another user!");
        }
        if (!user.getEmail().equals(userUpdate.getEmail()) && userRepository.existsByEmail(userUpdate.getEmail())) {
            throw new EntityExistsException("Email is already used by another user!");
        }

        user.setUsername(userUpdate.getUsername());
        user.setEmail(userUpdate.getEmail());
        user.setUpdatedDate(LocalDateTime.now());
        user.setUpdatedBy(authService.getCurrentUser().getId().toString());

        var roleIds = userUpdate.getRoleIds();
        List<Role> roles;
        if (roleIds == null || roleIds.size() == 0) {
            throw new RuntimeException("Role must be set!");
        } else {
            roles = roleRepository.findAllById(roleIds);
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }
    @Override
    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        userRepository.delete(user);
    }
    @Override
    public PageTemplate findAllPagination(String search, Long roleId, Integer page, Integer size, String sortBy, String sortType) {
        PageTemplate result = new PageTemplate();
        MetaData meta = new MetaData();

        PageRequest pagination = PageRequest.of(
                page-1,
                size,
                Sort.by(Sort.Direction.fromString(sortType), sortBy));

        Page<User> users = userRepository.findAllPagination(
                search,
                roleId,
                pagination
        );

        meta.setTotalCount(users.getTotalElements());
        meta.setPageCount(users.getTotalPages());
        meta.setCurrentPage(page);
        meta.setPerPage(size);

        result.set_items(users.getTotalPages() == 0 ? null : users.getContent());
        result.set_meta(meta);

        return result;
    }
}
