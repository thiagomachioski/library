package br.com.library.service;

import br.com.library.dto.request.CreateAuthorDTO;
import br.com.library.entity.Author;
import br.com.library.exception.ServiceException;
import br.com.library.repository.AuthorRepository;
import br.com.library.service.interfaces.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;
    private final ModelMapper mapper;

    public AuthorServiceImpl(AuthorRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Author createAuthor(CreateAuthorDTO authorDTO) {
        try {
            return repository.save(mapper.map(authorDTO, Author.class));
        } catch (Exception e) {
            throw new ServiceException("Error when saving new author: ", e);
        }
    }

    @Override
    public Author findById(String id) {
        try {
            return repository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new ServiceException("Author not found for id: " + id));
        } catch (Exception e) {
            throw new ServiceException("Error when find author by id: " + id, e);
        }
    }

    @Override
    public List<Author> findAllAuthors() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error when find all authors: ", e);
        }
    }
}
