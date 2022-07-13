# Documentacion API
## Genders

* **url** http://localhost:8080/api/v1/characters

#### **POST**

**body**

```json
{
	"name": "paranoia",
	"image": "url image paranoia"
}
```

**response status code 202**

**body**
```json
{
  "gender": {
    "name": "paranoia",
    "image": "url image paranoia",
    "id": 6
  }
}
```

#### **PUT**

**body**

```json
{
    "id": 6,
	"name": "paranormal",
	"image": "url image paranoia"
}
```

**response status code 202**

**body**
```json
{
  "gender": {
    "name": "paranormal",
    "image": "url image paranoia",
    "id": 6
  }
}
```

#### **DELETE**

**params**: id

**response status code 202**

#### **GET**   `FIND ALL`

**params**: `without params`

**response status code 200**

**body**
```json
{
  "genders": [
    {
      "name": "accion",
      "image": "url image accion",
      "id": 1
    },
    {
      "name": "drama",
      "image": "url image drama",
      "id": 2
    },
    {
      "name": "animacion",
      "image": "url image animacion",
      "id": 3
    }
  ]
}
```

#### **GET**   `FIND BY ID`

**params**: id

**response status code 200**

**body**
```json
{
  "gender": {
    "name": "animacion",
    "image": "url image animacion",
    "id": 3
  }
}
```

#### **GET**   `FIND BY NAME`

**params**: name

**response status code 200**

**body**
```json
{
  "genders": [
    {
      "name": "accion",
      "image": "url image accion",
      "id": 1
    },
    {
      "name": "animacion",
      "image": "url image animacion",
      "id": 3
    },
    {
      "name": "suspenso",
      "image": "url image suspenso",
      "id": 4
    }
  ]
}
```

## Movies

* **url** http://localhost:8080/api/v1/movies

#### **POST**

**body**

```json
{
	"title": "Peter pan",
	"image": "url image peter pan",
	"qualification": 4,
	"gender_id": 3
}
```

**response status code 202**

**body**
```json
{
  "movie": {
    "id": 7,
    "title": "Peter pan",
    "image": "url image peter pan",
    "qualification": 4,
    "gender_id": 3
  }
}
```

#### **POST**. Add character to movie.
**path** `/add_character`

**params**: movie_id, character_id

**response status code 202**

#### **PUT**

**body**

```json
{
	"id": 6,
	"title": "Turbito",
	"image": "url image turbo",
	"qualification": 4,
	"gender_id": 3
}
```

**response status code 202**

**body**
```json
{
  "movie": {
    "id": 6,
    "title": "Turbito",
    "image": "url image turbo",
    "qualification": 4,
    "gender_id": 3
  }
}
```

#### **DELETE**

**params**: id

**response status code 202**

#### **GET**   `FIND ALL`

**params**: `without params`

**response status code 200**

**body**
```json
{
  "movies": [
    {
      "id": 4,
      "title": "Cars",
      "image": "url image cars",
      "created_at": "2021-10-10"
    },
    {
      "id": 5,
      "title": "Cars 2",
      "image": "url image cars 2",
      "created_at": "2021-10-10"
    },
    {
      "id": 1,
      "title": "Rambo 1",
      "image": "url image rambo 1",
      "created_at": "2021-10-10"
    }
  ]
}
```

#### **GET**   `FIND BY ID`

**params**: id

**response status code 200**

**body**
```json
{
  "movie": {
    "id": 2,
    "title": "Rambo 2",
    "image": "url image rambo 2",
    "qualification": 3,
    "created_at": "2021-10-10",
    "characters": [
      {
        "id": 1,
        "name": "Jhon Rambo",
        "image": null
      },
      {
        "id": 3,
        "name": "Wanda nara",
        "image": null
      }
    ]
  }
}
```

#### **GET**   `FIND BY PARAMS`

**params**: name, genre (integer), order (asc or desc).

**response status code 200**

**body**
```json
{
  "movies": [
    {
      "id": 5,
      "title": "Cars 2",
      "image": "url image cars 2",
      "created_at": "2021-10-10"
    },
    {
      "id": 4,
      "title": "Cars",
      "image": "url image cars",
      "created_at": "2021-10-10"
    }
  ]
}
```

## Characters

* **url** http://localhost:8080/api/v1/characters

#### **POST**

**body**

```json
{
	"name": "tribilin",
	"image": "image url bribilin",
	"age": 19,
	"weight": 1.61,
	"history": "el trapesista",
	"movie_id": 3
}
```

**response status code 202**

**body**
```json
{
  "character": {
    "id": 4,
    "name": "tribilin",
    "image": "image url bribilin",
    "age": 19,
    "weight": 1.61,
    "history": "el trapesista",
    "movies": [
      "Rambo 3"
    ],
    "movie_id": null
  }
}
```

#### **PUT**

**body**

```json
{
	"id": 3,
	"name": "Wanda naraaaa",
	"image": "image url wanda nara",
	"age": 19,
	"weight": 1.61,
	"history": "enamorada de jhon rambo, asiatica",
	"movie_id": 2
}
```

**response status code 202**

**body**
```json
{
  "character": {
    "id": 3,
    "name": "Wanda naraaaa",
    "image": "image url wanda nara",
    "age": 19,
    "weight": 1.61,
    "history": "enamorada de jhon rambo, asiatica",
    "movies": [],
    "movie_id": null
  }
}
```

#### **DELETE**

**params**: id

**response status code 202**

#### **GET**   `FIND ALL`

**params**: `without params`

**response status code 200**

**body**
```json
{
  "characters": [
    {
      "id": 1,
      "name": "Jhon Rambo",
      "image": "image url jhon rambo"
    },
    {
      "id": 2,
      "name": "Coronel",
      "image": "image url coronel"
    },
    {
      "id": 3,
      "name": "Wanda naraaaa",
      "image": "image url wanda nara"
    }
  ]
}
```

#### **GET**   `FIND BY ID`

**params**: id

**response status code 200**

**body**
```json
{
  "character": {
    "id": 3,
    "name": "Wanda naraaaa",
    "image": "image url wanda nara",
    "age": 19,
    "weight": 1.61,
    "history": "enamorada de jhon rambo, asiatica",
    "movies": [
      "Rambo 2"
    ],
    "movie_id": null
  }
}
```

#### **GET**   `FIND BY PARAMS`

**params**: name, age (integer), weight (float), movies (integer).

**response status code 200**

**body**
```json
{
  "characters": [
    {
      "id": 1,
      "name": "Jhon Rambo",
      "image": "image url jhon rambo"
    },
    {
      "id": 3,
      "name": "Wanda naraaaa",
      "image": "image url wanda nara"
    }
  ]
}
```
