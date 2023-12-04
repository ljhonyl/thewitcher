# PRACTICA MOVILES

## Analisis

|Requisitos funcionales|
|--|
|Ver la lista de personajes|
|Insertar personaje|
|Eliminar personaje|

|Requisitos no funcionales|
|--|
|Registrarse o iniciar sesión|
|Recoger la información de una base de datos|
|Interfaces con botones para la navegación entre vistas|

```plantuml
@startuml
left to right direction
actor User as us
rectangle App{
    usecase "Ver personajes" as uc1
    usecase "Insertar personaje" as uc2
    usecase "Eliminar personaje" as uc3
    usecase Login as uc4
}
us --> uc1
us --> uc2
us --> uc3
uc1 ..> uc4:include 
uc2 ..> uc4:include
uc3 ..> uc4:include
@enduml
```

## DISEÑO

### Patrón de diseño arquitectónico-MVVM

Model-View-ViewModel

```plantuml
@startuml
left to right direction
component Vista
component VistaModelo
component Modelo
component BBDD <<SQLite>>

Vista ---> VistaModelo :observa
VistaModelo --> Vista:notifica
VistaModelo --> Modelo :usa
Modelo --> BBDD:usa
@enduml
```

### Prototipo

![](pantalla_login_lista_detalle.jpg)
![](pantalla_add.jpg)