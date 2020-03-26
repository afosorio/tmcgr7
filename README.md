# MoneyChange
Aplicación para el manejo de cambio de moneda de usd a cualquier otra moneda en tiempo real.
La aplicación permite al usuario
- Ubicarlo
- Escoger moneda que quiere convertir
- Guardar historial de monedas convertidas
- Cargar la información del historico y poder cambiar el valor a convertir
- Ver el detalle de cada conversión incluyendo fecha.

## Plataforma

**Android** 
El proyecto puede ser abierto desde Android Studio o IntelliJ

## Instalación

Clone este repositorio e importelo en la plataforma seleccionada.

```bash
git clone https://github.com/afosorio/tmcgr7.git
```

## Generando el APK para Android

Desde Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***


## Versiones

 Kotlin 1.3.61  
 Android Studio 3.6.1  
 Android Plugin 3.6.1  
 Gradle 5.1-all (Wrapper)   
 **Android Minimal API:** 21  
 **Android Target API:** 29  

## Estructura

-  ***módulo de aplicación.***
    - Cosas específicas de Android
          - Maneja todos los requisitos de interfaz de usuario, todas las clases de fragmentos se suscriben a eventos que el viewModel puede reaccionar.
          - Para construir la IU se sigue el patrón de "Single Activity" por flujo y Fragmento por pantalla.
          - El controlador de navegación JetPack se usa para manejar la navegación en la aplicación
-  ***módulo de datos***
    - Llamadas API asignadas usando el cliente Retrofit (https://square.github.io/retrofit/) en los repositorios
    - Maneja las clases de Datasource.
-  ***módulo de dominio***
    - Principalmente clases de datos que representan los tipos de datos en la aplicación.
-  ***módulo de casos de uso***
    - Los casos de uso son clases puras de Kotlin que representan una unidad de lógica de negocio (también conocidos como interactors). Para ejecutar casos de uso en un hilo diferente, estamos utilizando Coroutines y los resultados se modelan en función de la clase sellada ResultData.
    - El tipo ResultData representa valores con dos posibilidades: un valor que es exitoso o un error;

## Arquitectura

MVVM + Clean Arquitecture

## Colaboradores

Este proyecto es desarrollado por:
* [Laura De la Rosa](https://gitlab.com/lauramdelarosa)
* [Juan Pablo Vivas](https://github.com/Juanpvivas)
* [Andres Osorio](https://github.com/afosorio)
* [Esteban Posada](https://github.com/EstebanPosada)
* [Gabriel Perez](https://github.com/gapfDev)




## Nombrando Ramas en git
Para colaborar en el proyecto debe crear una rama que especifique qué va a hacer.
hay algunos prefijos establecidos que usará.
si vas a:
  * hacer una nueva función que su rama debería ser name: feature / {name_of_the_feature} - ig: feature / push_notification
  * Corregir algún error de una característica que su rama debería ser nombre: fix / {name_of_the_fix}
  * haga código general que no sea de un historial específico, por lo que su rama debe ser name: general / {name_of_the_general} - ig: general / tooltip


