package br.com.library.controller;


import br.com.library.dto.request.CreateAuthorDTO;
import br.com.library.service.interfaces.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController("author")
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/v1/create")
    ResponseEntity<?> createAuthor(@RequestBody CreateAuthorDTO authorDTO) {
        try {
            var createdAuthor = authorService.createAuthor(authorDTO);
            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdAuthor.getId())
                    .toUri();
            return ResponseEntity.created(location).body(createdAuthor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while create author: " + e.getMessage());
        }
    }

}
