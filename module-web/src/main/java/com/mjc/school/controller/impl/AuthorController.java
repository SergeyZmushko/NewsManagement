package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.PageDtoResponse;
import com.mjc.school.service.dto.ResourceSearchFilterRequestDTO;
import com.mjc.school.versioning.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.mjc.school.controller.RestApiConst.AUTHOR_API_ROOT_PATH;

@ApiVersion()
@RestController
@RequestMapping(value = AUTHOR_API_ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(produces = "application/json", value = "Operations for creating, updating, retrieving and deleting authors in the application")
public class AuthorController implements
        BaseController<AuthorDtoRequest, AuthorDtoResponse, Long, ResourceSearchFilterRequestDTO, AuthorDtoRequest> {

    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long, ResourceSearchFilterRequestDTO, AuthorDtoRequest> authorService;

    @Autowired
    public AuthorController(
            final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long, ResourceSearchFilterRequestDTO, AuthorDtoRequest> authorService) {
        this.authorService = authorService;
    }

    @Override
    @GetMapping
    @ApiOperation(value = "View all authors", response = PageDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all authors"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public PageDtoResponse<AuthorDtoResponse> readAll(final ResourceSearchFilterRequestDTO searchRequest) {
        return authorService.readAll(searchRequest);
    }

    @Override
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retrieve specific author with the supplied id", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the author with the supplied id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public AuthorDtoResponse readById(@PathVariable Long id) {
        return authorService.readById(id);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a piece of author", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a piece of author"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public AuthorDtoResponse create(@RequestBody AuthorDtoRequest createRequest) {
        return authorService.create(createRequest);
    }

    @Override
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a piece of author information", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated author information"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public AuthorDtoResponse update(@PathVariable Long id, @RequestBody AuthorDtoRequest updateRequest) {
        return authorService.update(id, updateRequest);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deletes specific author with the supplied id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted author with the id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}
