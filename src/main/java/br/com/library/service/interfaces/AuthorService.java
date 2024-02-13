package br.com.library.service.interfaces;

import br.com.library.dto.request.CreateAuthorDTO;
import br.com.library.entity.Author;

public interface AuthorService {
    Author createAuthor(CreateAuthorDTO authorDTO);
}
