package com.example.demo.utils;

public class DocumentationJson {

    public String documentationText() {

        String docs = """
        
            {
                "openapi":"3.0.0",
                "info":{
                    "title":"Todo Management API",
                    "version":"1.0",
                    "description":"The Task Management API is a robust and easy-to-use service for managing tasks and to-do lists. This API provides endpoints to create, update, view, and delete tasks, making it ideal for both individual and team-based task tracking. It supports various features such as task prioritization, due dates, and categorization, helping to organize tasks effectively. The API also allows users to mark tasks as completed, offering the flexibility and functionality needed to manage all aspects of task management. \\n\\nEach service handles its errors independently, with translations available for supported languages. The gateway handles one error code in English, with no translation support:\\n\\n[ 1 ] - Service Unavailable (code 503):\\n\\n```json\\n{\\n  \\"status\\": \\"error\\",\\n  \\"code\\": 503,\\n  \\"message\\": \\"Service unavailable, we are investigating. Please try again later.\\"\\n}\\n```"
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
                                                                    "example":"ddf76b28-84ac-4573-b00f-d8c1c6129bd3"
                                                                },
                                                                "categoryName":{
                                                                    "type":"string",
                                                                    "example":"credit card"
                                                                }
                                                            }
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
                }
            }
        
        """;

        return docs;

    }


}
