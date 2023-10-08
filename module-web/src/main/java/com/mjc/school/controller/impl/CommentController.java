package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.*;
import com.mjc.school.versioning.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.mjc.school.controller.RestApiConst.COMMENTS_API_ROOT_PATH;
@ApiVersion()
@RestController
@RequestMapping(value = COMMENTS_API_ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(produces = "application/json", value = "Operations for creating, updating, retrieving and deleting comments in the application")
public class CommentController implements BaseController<CommentDtoRequest, CommentDtoResponse, Long, ResourceSearchFilterRequestDTO, CommentDtoRequest> {

    private final BaseService<CommentDtoRequest, CommentDtoResponse, Long, ResourceSearchFilterRequestDTO, CommentDtoRequest> commentService;
    @Autowired
    public CommentController (final BaseService<CommentDtoRequest, CommentDtoResponse, Long, ResourceSearchFilterRequestDTO, CommentDtoRequest> commentService){
        this.commentService = commentService;
    }

    @Override
    @GetMapping
    @ApiOperation(value = "View all comments", response = PageDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all comments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public PageDtoResponse<CommentDtoResponse> readAll(final ResourceSearchFilterRequestDTO searchRequest) {
        return commentService.readAll(searchRequest);
    }


    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve specific comment with the supplied id", response = CommentDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the comment with the supplied id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public CommentDtoResponse readById(@PathVariable Long id) {
        return commentService.readById(id);
    }


    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a piece of comment", response = CommentDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a piece of comment"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public CommentDtoResponse create(@RequestBody CommentDtoRequest createRequest) {
        return commentService.create(createRequest);
    }


    @Override
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a piece of comment information", response = CommentDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated comment information"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public CommentDtoResponse update(@PathVariable Long id, @RequestBody CommentDtoRequest updateRequest) {
        return commentService.update(id, updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deletes specific comment with the supplied id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deletes the specific comment"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public void deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
    }
}
