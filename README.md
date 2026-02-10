💰 AlkeWallet – Billetera Digital (Java · VS Code · Gradle)

Java · JUnit 5 · Gradle · Visual Studio Code

📋 Descripción

AlkeWallet es una aplicación de billetera digital desarrollada en Java que permite a los usuarios crear una cuenta, gestionar su saldo, realizar depósitos y retiros, y convertir dinero entre distintas monedas.

El proyecto aplica principios de Programación Orientada a Objetos (POO), uso de interfaces, validaciones de reglas de negocio y pruebas unitarias con JUnit 5.

Diseñado para ejecutarse en consola y desarrollado en Visual Studio Code.

🎯 Características Principales

✅ Crear cuenta con saldo inicial y moneda
💰 Consultar saldo disponible
📥 Depositar dinero (solo montos válidos)
📤 Retirar dinero con validación de fondos
💱 Convertir saldo entre monedas (CLP, USD, EUR)
🔒 Validaciones para evitar operaciones inválidas
🧪 Pruebas unitarias con JUnit 5
🖥️ Menú interactivo por consola

🛠️ Tecnologías Utilizadas

Java 21 – Lenguaje principal

Gradle – Gestión de dependencias y build

JUnit 5 – Pruebas unitarias

Visual Studio Code – Entorno de desarrollo

📁 Estructura del Proyecto
AlkeWallet/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── alkewallet/
│   │   │           ├── WalletApp.java        # Clase principal (menú)
│   │   │           ├── model/
│   │   │           │   ├── Cuenta.java       # Lógica de cuenta
│   │   │           │   ├── Wallet.java       # Coordinador de operaciones
│   │   │           │   └── Moneda.java       # Enum de monedas
│   │   │           └── convertidor/
│   │   │               ├── ConvertidorMoneda.java
│   │   │               └── ConvertidorMonedaSimple.java
│   │   └── test/
│   │       └── java/
│   │           └── alkewallet/
│   │               └── model/
│   │                   └── CuentaTest.java  # Pruebas unitarias
│   └── build.gradle
├── gradlew
├── gradlew.bat
├── DIAGRAMA.md
└── README.md


🚀 Instalación y Configuración
Prerrequisitos

Java JDK 21 o superior

Visual Studio Code

Gradle Wrapper (incluido en el proyecto)

Verificar Java
java -version

▶️ Cómo Ejecutar la Aplicación
Opción 1: Desde VS Code (Recomendado)

Abrir el proyecto en Visual Studio Code

Abrir el archivo WalletApp.java

Ejecutar el método main

La aplicación se ejecutará en la terminal integrada

Opción 2: Desde Terminal
./gradlew run


(o ejecutar directamente el main desde VS Code)

🧪 Ejecutar Pruebas Unitarias

Las pruebas están implementadas con JUnit 5 y validan la lógica de negocio de la clase Cuenta.

Desde Terminal
./gradlew test

Ejecutar solo pruebas de Cuenta
./gradlew test --tests alkewallet.model.CuentaTest

Resultado esperado
BUILD SUCCESSFUL

🧪 Casos de Prueba Implementados

Clase CuentaTest:

✅ Asignación correcta del saldo inicial
✅ Depósitos válidos
✅ Rechazo de depósitos negativos
✅ Retiros válidos
✅ Rechazo de retiros mayores al saldo
✅ Rechazo de retiros negativos

Las pruebas garantizan que las reglas de negocio financieras se cumplan correctamente.

📖 Uso de la Aplicación
Menú Principal (Consola)
MENÚ PRINCIPAL
1. Crear Cuenta
2. Ver Saldo
3. Depositar
4. Retirar
5. Convertir Saldo
0. Salir

Flujo típico

Crear cuenta → ingresar nombre, moneda y saldo inicial

Depositar dinero

Consultar saldo

Convertir saldo a otra moneda

🏗️ Arquitectura del Proyecto
Diagrama Conceptual Simplificado
Wallet
 ├── Cuenta
 └── ConvertidorMoneda (interface)
        └── ConvertidorMonedaSimple

Componentes

Cuenta: maneja saldo, depósitos y retiros

Wallet: coordina operaciones de la cuenta

Moneda: enum de monedas disponibles

ConvertidorMoneda: contrato de conversión

WalletApp: menú y flujo de la aplicación

🎓 Requerimientos Académicos Cumplidos

✅ Programación Orientada a Objetos
✅ Uso de clases, encapsulación e interfaces
✅ Diagrama de clases
✅ Pruebas unitarias con JUnit 5
✅ Validación de reglas de negocio
✅ Aplicación funcional por consola

👤 Autor

Proyecto desarrollado por Cristian Torres
como parte de su formación en desarrollo de software.

📄 Licencia

Proyecto de uso educativo.