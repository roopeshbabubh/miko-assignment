# vert-x-app

This document provides information on the API endpoints for the vert-x-app. The API is designed to interact with a language (aLang: simple assembly Language) interpreter, allowing users to perform operation to executing programs with multiple statements.

## Endpoints:

### 1. Execute a Program with Multiple Statements

**POST:** http://localhost:8888/executeProgram

**Body:**
```json
{
    "program": "MV REG1,#2000\n MV REG2,#3000\n ADD REG1,REG2,REG3\n ADD REG1,600\n SHOW REG"
}
```

## Usage Instructions:

1. **Execute a Program with Multiple Statements:**
   - Send a POST request to the endpoint: `http://localhost:8080/api/aLang/executeProgram`
   - Include a JSON body with the program containing multiple statements.
