package com.example.cinetrailer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// Gestionar loas archivos localmente
@Service
public class FileSystemStorageService implements StorageService{

    @Value("${storage, location}") // Cargar el valor de storage
    private String storageLocation;

    @PostConstruct
    @Override
    public void init() {
        try {
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException e) {
            throw new RuntimeException("Error al inicar la ubicación del almacen de archivos");
        }
    }

    @Override
    public String storage(MultipartFile file) {
        String filename = file.getOriginalFilename();

        if (file.isEmpty()) {
            throw new RuntimeException("No se puede almacenar un archivo vacío");
        }

        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, Paths.get(storageLocation).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error al almacenar el archivo " + filename, e);
        }
        return filename;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void delete(String filename) {

    }
}
