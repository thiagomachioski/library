package br.com.library.service;

import br.com.library.dto.request.CreateAuthorDTO;
import br.com.library.entity.Author;
import br.com.library.exception.ServiceException;
import br.com.library.repository.AuthorRepository;
import br.com.library.service.interfaces.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
