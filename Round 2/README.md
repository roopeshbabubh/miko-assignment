# spring-redis-app

This document provides information on the API endpoints for the spring-redis app. The API is designed to interact with a language (aLang: simple assembly Language) interpreter, allowing users to perform operations such as moving values to registers, adding registers, executing programs with multiple statements, retrieving contents of registers, and all the register adding statements.

## Endpoints:

### 1. Move a Value to Register

**POST:** http://localhost:8080/api/aLang/moveValue

**Body:**
```json
{
    "statement": "MV REG3,#1000"
}
```

### 2. Adding registers together and Saving results in the first register

**POST:** http://localhost:8080/api/aLang/addValue

**Body:**
```json
{
    "statement": "ADD REG1,600"
}
```

**OR**

**Body:**
```json
{
    "statement": "ADD REG1,REG2"
}
```

### 3. Execute a Program with Multiple Statements

**POST:** http://localhost:8080/api/aLang/executeProgram

**Body:**
```json
{
    "program": "MV REG1,#2000\n MV REG2,#3000\n ADD REG1,REG2,REG3\n ADD REG1,600\n SHOW REG"
}
```

### 4. Show the Contents of Registers

**GET:** http://localhost:8080/api/aLang/showReg

**Body:**
```json
{
    "statement": "SHOW REG"
}
```

**OR**

**Body(optional, to show a specific register):**
```json
{
    "statement": "SHOW REG1"
}
```

### 5. Get All ADD Statements

**GET:** http://localhost:8080/api/aLang/addValueStatements

## Usage Instructions:

1. **Move a Value to Register:**
   - Send a POST request to the endpoint: `http://localhost:8080/api/aLang/moveValue`
   - Include a JSON body with the statement to move a value to a register.

2. **Adding Register:**
   - Send a POST request to the endpoint: `http://localhost:8080/api/aLang/addValue`
   - Include a JSON body with the statement to add a register. 
   - This statement can involve adding two or more registers or combining registers with constants.

3. **Execute a Program with Multiple Statements:**
   - Send a POST request to the endpoint: `http://localhost:8080/api/aLang/executeProgram`
   - Include a JSON body with the program containing multiple statements.

4. **Show the Contents of Registers:**
   - Send a GET request to the endpoint: `http://localhost:8080/api/aLang/showReg`
   - Include a JSON body with the statement to show registers. 
   - This statement can either display the content of all registers or specify a particular register.

5. **Get All ADD Statements:**
   - Send a GET request to the endpoint: `http://localhost:8080/api/aLang/addValueStatements`


## Use CLI commands to operate in Docker:

```bash
docker-compose build
docker-compose up
docker-compose down
```
