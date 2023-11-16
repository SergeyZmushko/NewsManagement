package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.implementation.AuthorService;
import com.mjc.school.service.implementation.CommentService;
import com.mjc.school.service.implementation.NewsService;
import com.mjc.school.service.implementation.TagService;
import com.mjc.school.versioning.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.mjc.school.controller.RestApiConst.NEWS_API_ROOT_PATH;

import java.util.List;

@ApiVersion()
@RestController
@RequestMapping(value = NEWS_API_ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(produces = "application/json", value = "Operations for creating, updating, retrieving and deleting news in the application")
public class NewsController implements BaseController<CreateNewsDtoRequest, NewsDtoResponse, Long, Integer, UpdateNewsDtoRequest> {

    private final NewsService newsService;
    private final TagService tagService;
    private final AuthorService authorService;
    private final CommentService commentService;

    @Autowired
    public NewsController(NewsService newsService, TagService tagService,
                          AuthorService authorService, CommentService commentService){
        this.newsService = newsService;
        this.tagService = tagService;
        this.authorService = authorService;
        this.commentService = commentService;
    }
    @Override
    @GetMapping
    @ApiOperation(value = "View all news", response = PageDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all news"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public PageDtoResponse<NewsDtoResponse> readAll(@RequestParam (defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam (defaultValue = "title:asc") String sort) {
        return newsService.readAll(pageNo, pageSize, sort);
    }
    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve specific news with the supplied id", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the news with the supplied id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public NewsDtoResponse readById(@PathVariable Long id) {
        return newsService.readById(id);
    }


    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a piece of news", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a piece of news"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public NewsDtoResponse create(@RequestBody CreateNewsDtoRequest dtoRequest) {
        return newsService.create(dtoRequest);
    }

    @Override
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a piece of news information", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated news information"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public NewsDtoResponse update(@PathVariable Long id, @RequestBody UpdateNewsDtoRequest dtoRequest) {
        return newsService.update(id, dtoRequest);
    }
    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deletes specific news with the supplied id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deletes the specific news"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public void deleteById(@PathVariable Long id) {
        newsService.deleteById(id);
    }

    @GetMapping("/{id}/tags")
    @ApiOperation(value = "View all tags with the supplied news id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the specific tags by news id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public List<TagDtoResponse> readTagsByNewsId(@PathVariable Long id) {
        return tagService.readByNewsId(id);
    }

    @GetMapping("/{id}/author")
    @ApiOperation(value = "View all authors with the supplied news id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the specific author by news id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public AuthorDtoResponse readAuthorByNewsId(@PathVariable Long id) {
        return authorService.readByNewsId(id);
    }

    @GetMapping("/{id}/comments")
    @ApiOperation(value = "View all comments with the supplied news id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the specific comments by news id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public List<CommentDtoResponse> readCommentsByNewsId(@PathVariable Long id) {
        return commentService.readByNewsId(id);
    }
}
