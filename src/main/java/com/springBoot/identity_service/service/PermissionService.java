package com.springBoot.identity_service.service;

import com.springBoot.identity_service.dto.request.PermissionRequest;
import com.springBoot.identity_service.dto.response.PermissionResponse;
import com.springBoot.identity_service.entity.Permission;
import com.springBoot.identity_service.mapper.PermissionMapper;
import com.springBoot.identity_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void deletePermissionById(String permissionId) {
        permissionRepository.deleteById(permissionId);

    }
}
