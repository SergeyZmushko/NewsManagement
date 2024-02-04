
# News-management

News management application Rest API for CRUD operations with resources.

## Idea
This educational project is focused on creating an API for storing news using Spring Boot. The application allows for the creation of user accounts, authentication within the app, and the publishing of news and comments on them.

# Technological stack
- Spring Boot as the foundational framework.
- Spring Security ensures robust security measures.
- Mockito is employed for unit testing.
- PostgreSQL is chosen as the database for persistent data storage.
- MapStruct is integrated for mapping between domain objects and Data Transfer Objects (DTOs).
- Swagger is leveraged to automatically generate comprehensive API documentation.
- SonarQube is utilized to conduct in-depth code analysis, promoting code quality and identifying potential issues early in the development process.


## API References

## Author-controller

#### View all authors

```http
  GET /api/{apiVersion}/authors
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `requestDTO` | `object` | params for filter and searching authors with paginating |

#### Retrieve specific author with the supplied id

```http
  GET /api/{apiVersion}/authors/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of author to fetch |

#### Create a piece of author

```http
  POST /api/{apiVersion}/authors
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `createRequest`      | `Object` | **Required**. Author data to create new author |


#### Update a piece of author information

```http
  PATCH /api/{apiVersion}/authors/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of author to update |

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `updateRequest`      | `object` | **Required**. Author data for update |

#### Deletes specific author with the supplied id

```http
  DELETE /api/{apiVersion}/authors/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of author to delete |



## News-controller

#### View all news

```http
  GET /api/{apiVersion}/news
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `requestDTO` | `object` | params for filter and searching news with paginating |

#### Retrieve specific news with the supplied id

```http
  GET /api/{apiVersion}/news/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of news to fetch |

#### Create a piece of author

```http
  POST /api/{apiVersion}/news
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `dtoRequest`      | `Object` | **Required**. News data to create new news |


#### Update a piece of news information

```http
  PATCH /api/{apiVersion}/news/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of news to update |

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `dtoRequest`      | `object` | **Required**. News data for update |

#### Deletes specific author with the supplied id

```http
  DELETE /api/{apiVersion}/news/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of news to delete |

#### View all authors with the supplied news id

```http
  GET /api/{apiVersion}/news/{id}/author
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of news to get author |

#### View all comments with the supplied news id

```http
  GET /api/{apiVersion}/news/{id}/comments
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of news to get comments |

#### View all comments with the supplied news id

```http
  GET /api/{apiVersion}/news/{id}/tags
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of news to get tags |


## Comment-controller

#### View all comments

```http
  GET /api/{apiVersion}/comments
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `requestDTO` | `object` | params for filter and searching comments with paginating |

#### Retrieve specific comment with the supplied id

```http
  GET /api/{apiVersion}/comments/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of comment to fetch |

#### Create a piece of comment

```http
  POST /api/{apiVersion}/comments
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `createRequest`      | `Object` | **Required**. Comment data to create new comment |


#### Update a piece of comment information

```http
  PATCH /api/{apiVersion}/comments/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of comment to update |

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `updateRequest`      | `object` | **Required**. Comment data for update |

#### Deletes specific comment with the supplied id

```http
  DELETE /api/{apiVersion}/comments/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of comment to delete |

## Tag-controller

#### View all tags

```http
  GET /api/{apiVersion}/tags
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `requestDTO` | `object` | params for filter and searching tags with paginating |

#### Retrieve specific tag with the supplied id

```http
  GET /api/{apiVersion}/tags/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of tag to fetch |

#### Create a piece of tag

```http
  POST /api/{apiVersion}/tags
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `createRequest`      | `Object` | **Required**. Tag data to create new tag |


#### Update a piece of tag information

```http
  PATCH /api/{apiVersion}/tags/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of tag to update |

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `updateRequest`      | `object` | **Required**. Tag data for update |

#### Deletes specific tag with the supplied id

```http
  DELETE /api/{apiVersion}/tags/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of tag to delete |