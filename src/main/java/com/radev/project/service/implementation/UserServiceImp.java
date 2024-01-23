package com.radev.project.service.implementation;

import com.radev.project.dao.RoleRepository;
import com.radev.project.dao.UserRepository;
import com.radev.project.dto.MetaData;
import com.radev.project.dto.PageTemplate;
import com.radev.project.dto.user.ChangePasswordRequest;
import com.radev.project.dto.user.UserRegister;
import com.radev.project.dto.user.UserUpdate;
import com.radev.project.entity.Role;
import com.radev.project.entity.User;
import com.radev.project.service.abstraction.AuthService;
import com.radev.project.service.abstraction.CrudService;
import com.radev.project.service.abstraction.UserService;
import com.radev.project.service.exception._40x.UserNotFoundException;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
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
    public Object findById(Object id) {
        return userRepository.findById((Long) id)
                .orElseThrow(() -> new UserNotFoundException("id",id));
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
                .orElseThrow(()-> new UserNotFoundException("id",userUpdate.getId()));

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
    public void delete(Object userId) {
        User user = userRepository.findById((Long) userId)
                .orElseThrow(() -> new UserNotFoundException("id",userId));
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

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest passwordRequest) {
        String username = authService.getCurrentUser().getUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("username",username));

        // Validate password
        if (!passwordEncoder.matches(passwordRequest.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid current password");
        }

        user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        user.setUpdatedDate(LocalDateTime.now());
        user.setUpdatedBy(username);

        userRepository.save(user);
    }
}
