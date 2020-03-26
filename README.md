# tmcgr7
Aplicación para el manejo de cambio de moneda de usd a cualquier otra moneda en tiempo real.
La aplicación permite al usuario
-ubicarlo
-escoger moneda que quiere convertir
-guardar historial de monedas convertidas
-cargar la información del historico y poder cambiar el valor a convertir
-ver el detalle de cada conversión incluyendo fecha.

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


## Versions

 Kotlin 1.3.61  
 Android Studio 3.6.1  
 Android Plugin 3.6.1  
 Gradle 5.1-all (Wrapper)   
 **Android Minimal API:** 21  
 **Android Target API:** 29  

## Structure

- root (Gradle project)  
    - android (Gradle Android Module)  
    - ios (Xcode project)  
    - common (Gradle Kotlin module)  
        - commonMain (Meta classes)  
        - commonIos (iOS specific implementations)  
        - commonAndroid (Android specific implementations)  

## Architecture

MVVM + Clean Arquitecture

## Maintainers

This project is maintained by:
* [Laura De la Rosa](https://gitlab.com/lauramdelarosa)
* [Juan Pablo Vivas](https://github.com/Juanpvivas)
* [Andres Osorio](https://github.com/afosorio)
* [Esteban Posada](https://github.com/EstebanPosada)
* [Gabriel Perez](https://github.com/gapfDev)




## Naming branches
to collaborate in the project should create a branch specifying what are you going to do.
there are some established prefixes that you will use.
if you are going to :
 * do a new feature your branch should be name :  feature/{name_of_the_feature} - ig: feature/push_notification
 * fix some bug of a feature your branch should be name : fix/{name_of_the_fix}
 * do general code that are not from a specific history so your branch should be name : general/{name_of_the_general} - ig :general/tooltip


