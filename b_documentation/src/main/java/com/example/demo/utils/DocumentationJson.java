package com.example.demo.utils;

public class DocumentationJson {

    public String documentationText() {

        String docs = """
        
            {
                "openapi":"3.0.0",
                "info":{
                    "title":"Todo Management API",
                    "version":"1.0",
                    "description":"The Task Management API is a robust and easy-to-use service for managing tasks and to-do lists. This API provides endpoints to create, update, view, and delete tasks, making it ideal for both individual and team-based task tracking. It supports various features such as task prioritization, due dates, and categorization, helping to organize tasks effectively. The API also allows users to mark tasks as completed, offering the flexibility and functionality needed to manage all aspects of task management."
                },
                "components":{
                    "securitySchemes":{
                        "BearerAuth":{
                            "type":"http",
                            "scheme":"bearer",
                            "bearerFormat":"JWT"
                        }
                    }
                },
                "paths":{

                    # HELLOWORLD
                    # ==========================================================
                    "/helloworld/helloworld":{
                        "get":{
                            "summary":"Get hello world message",
                            "description":"Retrieves a hello world message. You can optionally provide a custom message via query parameter.",
                            "tags":[
                                "HELLO WORLD"
                            ],
                            "parameters":[
                                {
                                    "name":"message",
                                    "in":"query",
                                    "required":false,
                                    "description":"Custom message to be returned. Defaults to 'Hello World!!!' if not provided.",
                                    "schema":{
                                        "type":"string",
                                        "example":"Hello from the API!"
                                    }
                                }
                            ],
                            "responses":{
                                "200":{
                                    "description":"Successful response with hello world message.",
                                    "content":{
                                        "application/json":{
                                            "schema":{
                                                "type":"object",
                                                "properties":{
                                                    "statusCode":{
                                                        "type":"integer",
                                                        "example":200
                                                    },
                                                    "statusMessage":{
                                                        "type":"string",
                                                        "example":"success"
                                                    },
                                                    "message":{
                                                        "type":"string",
                                                        "example":"Hello World!!!"
                                                    },
                                                    "links":{
                                                        "type":"object",
                                                        "properties":{
                                                            "self":{
                                                                "type":"string",
                                                                "example":"/helloworld/helloworld"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    },
                    # ==========================================================

                    # TASKS
                    # ==========================================================
                    "/tasks/category/create":{
                        "post":{
                            "summary":"Create a new category",
                            "description":"Creates a new category after validating the name and ensuring that there is no conflict with an existing category.",
                            "tags":[
                                "CATEGORY"
                            ],
                            "parameters":[
                               \s
                            ],
                            "requestBody":{
                                "required":true,
                                "content":{
                                    "application/json":{
                                        "schema":{
                                            "type":"object",
                                            "properties":{
                                                "categoryName":{
                                                    "type":"string",
                                                    "description":"Name of the category to be created",
                                                    "example":"Technology"
                                                }
                                            },
                                            "required":[
                                                "categoryName"
                                            ]
                                        }
                                    }
                                }
                            },
                            "responses":{
                                "201":{
                                    "description":"Category successfully created.",
                                    "content":{
                                        "application/json":{
                                            "schema":{
                                                "type":"object",
                                                "properties":{
                                                    "statusCode":{
                                                        "type":"integer",
                                                        "example":201
                                                    },
                                                    "statusMessage":{
                                                        "type":"string",
                                                        "example":"success"
                                                    },
                                                    "message":{
                                                        "type":"string",
                                                        "example":"Category successfully created"
                                                    },
                                                    "meta":{
                                                        "type":"object",
                                                        "properties":{
                                                            "idCreated":{
                                                                "type":"string",
                                                                "example":"{id}"
                                                            }
                                                        }
                                                    },
                                                    "links":{
                                                        "type":"object",
                                                        "properties":{
                                                            "self":{
                                                                "type":"string",
                                                                "example":"/tasks/category/create"
                                                            },
                                                            "next":{
                                                                "type":"string",
                                                                "example":"/tasks/category/list"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "400":{
                                    "description":"Field validation error",
                                    "content":{
                                        "application/json":{
                                            "schema":{
                                                "type":"object",
                                                "properties":{
                                                    "errorCode":{
                                                        "type":"integer",
                                                        "example":400
                                                    },
                                                    "field":{
                                                        "type":"string",
                                                        "example":"categoryName"
                                                    },
                                                    "message":{
                                                        "type":"string",
                                                        "example":"Category name is required"
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "409":{
                                    "description":"Conflict when trying to create the category, name already exists",
                                    "content":{
                                        "application/json":{
                                            "schema":{
                                                "type":"object",
                                                "properties":{
                                                    "errorCode":{
                                                        "type":"integer",
                                                        "example":409
                                                    },
                                                    "message":{
                                                        "type":"string",
                                                        "example":"Category already exists"
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    },
                    # ==========================================================
                    "/tasks/category/list-all":{
                        "get":{
                            "summary":"Get all categories",
                            "description":"Retrieves a list of all categories with their IDs and names.",
                            "tags":[
                                "CATEGORY"
                            ],
                            "responses":{
                                "200":{
                                    "description":"Successful response with category list.",
                                    "content":{
                                        "application/json":{
                                            "schema":{
                                                "type":"object",
                                                "properties":{
                                                    "statusCode":{
                                                        "type":"integer",
                                                        "example":200
                                                    },
                                                    "statusMessage":{
                                                        "type":"string",
                                                        "example":"success"
                                                    },
                                                    "message":{
                                                        "type":"string",
                                                        "example":"Data received successfully."
                                                    },
                                                    "data":{
                                                        "type":"array",
                                                        "items":{
                                                            "type":"object",
                                                            "properties":{
                                                                "id":{
                                                                    "type":"string",
                                                                    "format":"uuid",
                                                                    "example":"{id}"
                                                                },
                                                                "categoryName":{
                                                                    "type":"string",
                                                                    "example":"credit card"
                                                                }
                                                            }
                                                        }
                                                    },"meta":{
                                                        "type":"object",
                                                        "properties":{
                                                            "totalItems":{
                                                                "type":"number",
                                                                "example":"1"
                                                            },
                                                        }
                                                    },
                                                    "links":{
                                                        "type":"object",
                                                        "properties":{
                                                            "self":{
                                                                "type":"string",
                                                                "example":"/tasks/category/list-all"
                                                            },
                                                            "next":{
                                                                "type":"string",
                                                                "example":"/tasks/category/update"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    },
                    # ==========================================================
                    "/tasks/category/update/{id}": {
                        "put": {
                            "summary": "Update Category",
                            "description": "Updates an existing category by its ID. The category name to be updated is provided in the request body. The ID and updated category name are validated before updating the category in the database.",
                            "tags": [
                                "CATEGORY"
                            ],
                            "parameters": [
                                {
                                    "name": "id",
                                    "in": "path",
                                    "required": true,
                                    "description": "The UUID of the category to update.",
                                    "schema": {
                                        "type": "string",
                                        "example": "{id}"
                                    }
                                }
                            ],
                            "requestBody": {
                                "required": true,
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "updateCategoryName": {
                                                    "type": "string",
                                                    "description": "The new name for the category.",
                                                    "example": "New Category Name"
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "responses": {
                                "200": {
                                    "description": "Successful response indicating the category was updated.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 200
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "success"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Category updated successfully"
                                                    },
                                                    "links": {
                                                        "type": "object",
                                                        "properties": {
                                                            "self": {
                                                                "type": "string",
                                                                "example": "/tasks/category/update/{id}"
                                                            },
                                                            "next": {
                                                                "type": "string",
                                                                "example": "/tasks/category/list"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "400": {
                                    "description": "Bad Request - Invalid or missing data in the request.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 400
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "field": {
                                                        "type": "string",
                                                        "example": "id"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "The UUID is invalid."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "404": {
                                    "description": "Not Found - Category with the specified ID does not exist.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 404
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Category not found."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "409": {
                                    "description": "Conflict - The category name already exists.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 409
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "This category already exists."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    },
                    # ==========================================================
                    "/tasks/category/delete/{id}": {
                        "delete": {
                            "summary": "Delete Category",
                            "description": "Deletes an existing category by its ID. The ID must be validated to ensure it is a valid UUID format. If the category exists, it will be deleted from the database.",
                            "tags": [
                                "CATEGORY"
                            ],
                            "parameters": [
                                {
                                    "name": "id",
                                    "in": "path",
                                    "required": true,
                                    "description": "The UUID of the category to delete.",
                                    "schema": {
                                        "type": "string",
                                        "example": "{id}"
                                    }
                                }
                            ],
                            "responses": {
                                "200": {
                                    "description": "Successful response indicating the category was deleted.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 200
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "success"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Category deleted successfully"
                                                    },
                                                    "links": {
                                                        "type": "object",
                                                        "properties": {
                                                            "self": {
                                                                "type": "string",
                                                                "example": "/tasks/category/delete/{id}"
                                                            },
                                                            "next": {
                                                                "type": "string",
                                                                "example": "/tasks/category/list"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "400": {
                                    "description": "Bad Request - Invalid or missing data in the request.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 400
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "field": {
                                                        "type": "string",
                                                        "example": "id"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "The UUID is invalid."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "404": {
                                    "description": "Not Found - Category with the specified ID does not exist.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 404
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Category not found."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "409": {
                                    "description": "Conflict - The category name already exists.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 409
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "This category already exists."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    },
                    # ==========================================================
                    "/tasks/create": {
                        "post": {
                            "summary": "Create Task",
                            "description": "Creates a new task with the provided details. The request body must be validated to ensure all required fields are present and correctly formatted.",
                            "tags": [
                                "TASK"
                            ],
                            "requestBody": {
                                "required": true,
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "taskName": {
                                                    "type": "string",
                                                    "example": "Finish project report"
                                                },
                                                "description": {
                                                    "type": "string",
                                                    "example": "Complete the final report for the project."
                                                },
                                                "category": {
                                                    "type": "string",
                                                    "example": "Work"
                                                },
                                                "priority": {
                                                    "type": "string",
                                                    "example": "High"
                                                },
                                                "status": {
                                                    "type": "string",
                                                    "example": "Pending"
                                                },
                                                "dueDate": {
                                                    "type": "string",
                                                    "format": "date",
                                                    "example": "2025-03-15"
                                                }
                                            },
                                            "required": ["taskName", "description", "category", "priority", "status", "dueDate"]
                                        }
                                    }
                                }
                            },
                            "responses": {
                                "201": {
                                    "description": "Successful response indicating the task was created.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 201
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "success"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Task successfully created."
                                                    },
                                                    "meta": {
                                                        "type": "object",
                                                        "properties": {
                                                            "idCreated": {
                                                                "type": "string",
                                                                "example": "{id}"
                                                            }
                                                        }
                                                    },
                                                    "links": {
                                                        "type": "object",
                                                        "properties": {
                                                            "self": {
                                                                "type": "string",
                                                                "example": "/tasks/create"
                                                            },
                                                            "next": {
                                                                "type": "string",
                                                                "example": "/tasks/list"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "409": {
                                    "description": "Conflict – The task already exists on the same date.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 409
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "This task already exists."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "400": {
                                    "description": "Bad Request – Validation error on the input data.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 400
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "field": {
                                                        "type": "string",
                                                        "example": "description"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Cannot be empty."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    },
                    # ==========================================================
                    "/tasks/update/{id}": {
                        "put": {
                            "summary": "Update Task",
                            "description": "Updates an existing task based on the provided task ID and updated details. The request body must contain the necessary fields for updating the task. If no changes are provided, the existing task details will remain unchanged.",
                            "tags": [
                                "TASK"
                            ],
                            "parameters": [
                                {
                                    "name": "id",
                                    "in": "path",
                                    "required": true,
                                    "description": "The unique identifier of the task to be updated.",
                                    "schema": {
                                        "type": "string",
                                        "example": "{id}"
                                    }
                                }
                            ],
                            "requestBody": {
                                "required": false,
                                "content": {
                                    "application/json": {
                                        "schema": {
                                            "type": "object",
                                            "properties": {
                                                "taskName": {
                                                    "type": "string",
                                                    "example": "Finish project report"
                                                },
                                                "description": {
                                                    "type": "string",
                                                    "example": "Complete the final report for the project."
                                                },
                                                "category": {
                                                    "type": "string",
                                                    "example": "Work"
                                                },
                                                "priority": {
                                                    "type": "string",
                                                    "example": "High"
                                                },
                                                "status": {
                                                    "type": "string",
                                                    "example": "Pending"
                                                },
                                                "dueDate": {
                                                    "type": "string",
                                                    "format": "date",
                                                    "example": "2025-03-15"
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            "responses": {
                                "200": {
                                    "description": "Successful response indicating the task was updated.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 200
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "success"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Task successfully updated."
                                                    },
                                                    "links": {
                                                        "type": "object",
                                                        "properties": {
                                                            "self": {
                                                                "type": "string",
                                                                "example": "/tasks/update/{id}"
                                                            },
                                                            "next": {
                                                                "type": "string",
                                                                "example": "/tasks/list"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "404": {
                                    "description": "Not Found – The task with the specified ID does not exist.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 404
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Task not found."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "409": {
                                    "description": "Conflict – The task already exists with the same name and due date.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 409
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "This task already exists on the same date."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "400": {
                                    "description": "Bad Request – Validation error on the input data.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 400
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "field": {
                                                        "type": "string",
                                                        "example": "description"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Cannot be empty."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    },
                    # ==========================================================
                    "/tasks/delete/{id}": {
                        "delete": {
                            "summary": "Delete Task",
                            "description": "Deletes a task based on the provided task ID. If the task does not exist, a 404 error will be returned. The request should pass a valid UUID in the path to identify the task to be deleted.",
                            "tags": [
                                "TASK"
                            ],
                            "parameters": [
                                {
                                    "name": "id",
                                    "in": "path",
                                    "required": true,
                                    "description": "The unique identifier of the task to be deleted. The ID must follow the UUID format.",
                                    "schema": {
                                        "type": "string",
                                        "example": "{id}"
                                    }
                                }
                            ],
                            "responses": {
                                "200": {
                                    "description": "Successful response indicating the task was deleted.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 200
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "success"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Task successfully deleted."
                                                    },
                                                    "links": {
                                                        "type": "object",
                                                        "properties": {
                                                            "self": {
                                                                "type": "string",
                                                                "example": "/tasks/delete/{id}"
                                                            },
                                                            "next": {
                                                                "type": "string",
                                                                "example": "/tasks/list"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "404": {
                                    "description": "Not Found – The task with the specified ID does not exist.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 404
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Task not found."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "400": {
                                    "description": "Bad Request – Validation error on the provided UUID format.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 400
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "field": {
                                                        "type": "string",
                                                        "example": "id"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Invalid UUID format."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    },
                    # ==========================================================
                    "/tasks/list": {
                        "get": {
                            "summary": "List Tasks",
                            "description": "Fetches a list of tasks based on the provided filters. If no filters are applied, all tasks will be returned. The filters can include task name, description, category, priority, status, and date range (start and end dates).",
                            "tags": [
                                "TASK"
                            ],
                            "parameters": [
                                {
                                    "name": "taskName",
                                    "in": "query",
                                    "required": false,
                                    "description": "Filter tasks by name. The value is a case-insensitive substring.",
                                    "schema": {
                                        "type": "string",
                                        "example": "Task Name"
                                    }
                                },
                                {
                                    "name": "description",
                                    "in": "query",
                                    "required": false,
                                    "description": "Filter tasks by description. The value is a case-insensitive substring.",
                                    "schema": {
                                        "type": "string",
                                        "example": "Task Description"
                                    }
                                },
                                {
                                    "name": "category",
                                    "in": "query",
                                    "required": false,
                                    "description": "Filter tasks by category. The value is a case-insensitive substring.",
                                    "schema": {
                                        "type": "string",
                                        "example": "Work"
                                    }
                                },
                                {
                                    "name": "priority",
                                    "in": "query",
                                    "required": false,
                                    "description": "Filter tasks by priority. The value is a case-insensitive substring.",
                                    "schema": {
                                        "type": "string",
                                        "example": "High"
                                    }
                                },
                                {
                                    "name": "status",
                                    "in": "query",
                                    "required": false,
                                    "description": "Filter tasks by status. The value is a case-insensitive substring.",
                                    "schema": {
                                        "type": "string",
                                        "example": "Pending"
                                    }
                                },
                                {
                                    "name": "initDate",
                                    "in": "query",
                                    "required": false,
                                    "description": "Filter tasks by start date. The value should be in the format 'YYYY-MM-DD'.",
                                    "schema": {
                                        "type": "string",
                                        "example": "2025-01-01"
                                    }
                                },
                                {
                                    "name": "endDate",
                                    "in": "query",
                                    "required": false,
                                    "description": "Filter tasks by end date. The value should be in the format 'YYYY-MM-DD'.",
                                    "schema": {
                                        "type": "string",
                                        "example": "2025-12-31"
                                    }
                                }
                            ],
                            "responses": {
                                "200": {
                                    "description": "Successful response indicating the tasks were retrieved.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 200
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "success"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Tasks successfully retrieved."
                                                    },
                                                    "data": {
                                                        "type": "array",
                                                        "items": {
                                                            "type": "object",
                                                            "properties": {
                                                                "taskName": {
                                                                    "type": "string",
                                                                    "example": "Task Name"
                                                                },
                                                                "description": {
                                                                    "type": "string",
                                                                    "example": "Task Description"
                                                                },
                                                                "category": {
                                                                    "type": "string",
                                                                    "example": "Work"
                                                                },
                                                                "priority": {
                                                                    "type": "string",
                                                                    "example": "High"
                                                                },
                                                                "status": {
                                                                    "type": "string",
                                                                    "example": "Pending"
                                                                },
                                                                "initDate": {
                                                                    "type": "string",
                                                                    "example": "2025-01-01"
                                                                },
                                                                "endDate": {
                                                                    "type": "string",
                                                                    "example": "2025-12-31"
                                                                }
                                                            }
                                                        }
                                                    },
                                                    "meta": {
                                                        "type": "object",
                                                        "properties": {
                                                            "totalItems": {
                                                                "type": "integer",
                                                                "example": 1
                                                            }
                                                        }
                                                    },
                                                    "links": {
                                                        "type": "object",
                                                        "properties": {
                                                            "self": {
                                                                "type": "string",
                                                                "example": "/tasks/list"
                                                            },
                                                            "next": {
                                                                "type": "string",
                                                                "example": "/tasks/update"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                "400": {
                                    "description": "Bad Request – Validation error on the provided filter parameters.",
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "type": "object",
                                                "properties": {
                                                    "statusCode": {
                                                        "type": "integer",
                                                        "example": 400
                                                    },
                                                    "statusMessage": {
                                                        "type": "string",
                                                        "example": "error"
                                                    },
                                                    "field": {
                                                        "type": "string",
                                                        "example": "status"
                                                    },
                                                    "message": {
                                                        "type": "string",
                                                        "example": "Cannot be empty."
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    # ==========================================================
                }
            }
        """;

        return docs;

    }


}