package br.com.library.service.interfaces;

import br.com.library.dto.request.CreateAuthorDTO;
import br.com.library.entity.Author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(CreateAuthorDTO authorDTO);
    Author findById(String id);
    List<Author> findAllAuthors();
}
