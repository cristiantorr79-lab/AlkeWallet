# 💰 AlkeWallet - Billetera Digital

Proyecto de billetera digital desarrollado para el bootcamp de Alkemy, que permite a los usuarios gestionar sus activos financieros de manera segura y conveniente.

## 📋 Descripción

AlkeWallet es una aplicación de consola que permite:
- Crear una cuenta de usuario
- Consultar saldo disponible
- Realizar depósitos
- Realizar retiros
- Convertir saldo entre diferentes monedas (CLP, USD, EUR)

## 🎯 Objetivo

Desarrollar una billetera digital funcional, segura y fácil de usar que proporcione a los usuarios una solución confiable para administrar sus activos financieros de manera digital.

## 🛠️ Tecnologías Utilizadas

- **Java 17** o superior
- **JUnit 5** para pruebas unitarias
- **Maven** (opcional, si lo usas)
- Paradigma de **Programación Orientada a Objetos (POO)**

## 📁 Estructura del Proyecto
```
AlkeWallet/
├── src/
│   └── alkewallet/
│       ├── WalletApp.java                 # Clase principal con menú
│       ├── model/
│       │   ├── Cuenta.java                # Gestión de cuenta
│       │   ├── Wallet.java                # Lógica de billetera
│       │   └── Moneda.java                # Enum de monedas
│       └── convertidor/
│           ├── ConvertidorMoneda.java     # Interfaz
│           └── ConvertidorMonedaSimple.java # Implementación
├── test/
│   └── alkewallet/
│       ├── model/
│       │   ├── CuentaTest.java
│       │   └── WalletTest.java
│       └── convertidor/
│           └── ConvertidorMonedaSimpleTest.java
└── README.md
```

## ⚙️ Requisitos Previos

- **JDK 17** o superior instalado
- **IDE** recomendado: IntelliJ IDEA, Eclipse, VS Code o NetBeans
- (Opcional) Maven para gestión de dependencias

## 🚀 Instalación y Ejecución

### Opción 1: Desde el IDE

1. **Clonar el repositorio:**
```bash
   git clone https://github.com/cristiantorr79-lab/AlkeWallet.git
```

2. **Abrir el proyecto en tu IDE:**
   - IntelliJ IDEA: `File > Open > Seleccionar carpeta del proyecto`
   - Eclipse: `File > Import > Existing Projects into Workspace`
   - VS Code: `File > Open Folder`

3. **Ejecutar la aplicación:**
   - Buscar la clase `WalletApp.java`
   - Click derecho > `Run 'WalletApp.main()'`

### Opción 2: Desde la línea de comandos

1. **Compilar:**
```bash
   cd AlkeWallet
   javac -d bin src/alkewallet/*.java src/alkewallet/model/*.java src/alkewallet/convertidor/*.java
```

2. **Ejecutar:**
```bash
   java -cp bin alkewallet.WalletApp
```

## 🧪 Ejecutar Pruebas Unitarias

### Desde el IDE:
1. Click derecho en la carpeta `test`
2. Seleccionar `Run All Tests`

### Desde línea de comandos (con Maven):
```bash
mvn test
```

## 📖 Uso de la Aplicación

### Menú Principal
```
MENÚ PRINCIPAL:
1: Crear Cuenta
2: Ver Saldo
3: Depositar
4: Retirar
5: Convertir saldo
0: Salir
```

### Ejemplo de Uso

1. **Crear una cuenta:**
   - Selecciona opción `1`
   - Ingresa tu nombre
   - Selecciona moneda (CLP, USD o EUR)
   - Define saldo inicial

2. **Depositar dinero:**
   - Selecciona opción `3`
   - Ingresa el monto a depositar

3. **Retirar dinero:**
   - Selecciona opción `4`
   - Ingresa el monto a retirar

4. **Convertir saldo:**
   - Selecciona opción `5`
   - Elige la moneda destino
   - El sistema mostrará el equivalente (no cambia tu saldo)

## 💱 Tasas de Cambio

Las tasas de cambio utilizadas son:

| Conversión | Tasa |
|------------|------|
| CLP → USD | 850 CLP = 1 USD |
| USD → CLP | 1 USD = 850 CLP |
| CLP → EUR | 900 CLP = 1 EUR |
| EUR → CLP | 1 EUR = 900 CLP |
| USD → EUR | 1.1 USD = 1 EUR |
| EUR → USD | 1 EUR = 1.1 USD |

> **Nota:** Estas tasas son de ejemplo para propósitos educativos.

## 🏗️ Arquitectura y Diseño

### Principios de POO Aplicados

- **Encapsulamiento:** Atributos privados con getters/setters
- **Abstracción:** Uso de interfaces (`ConvertidorMoneda`)
- **Composición:** `Wallet` compone `Cuenta` y `ConvertidorMoneda`
- **Polimorfismo:** Implementación de la interfaz `ConvertidorMoneda`

### Patrones de Diseño

- **Strategy Pattern:** Para el convertidor de monedas
- **Dependency Injection:** En el constructor de `Wallet`

## 🧪 Pruebas Unitarias

El proyecto cuenta con **17 pruebas unitarias** que cubren:

### CuentaTest.java (6 tests)
- ✅ Asignación de saldo inicial
- ✅ Depósitos válidos e inválidos
- ✅ Retiros exitosos, con fondos insuficientes y negativos

### WalletTest.java (8 tests)
- ✅ Consulta de saldo y moneda
- ✅ Operaciones de depósito y retiro
- ✅ Conversiones de moneda (CLP↔USD, CLP↔EUR)

### ConvertidorMonedaSimpleTest.java (3 tests)
- ✅ Conversiones entre todas las monedas soportadas

## 👨‍💻 Autor

**Cristian Torres**
- GitHub: [@cristiantorr79-lab](https://github.com/cristiantorr79-lab)
- Proyecto: Evaluación Módulo 2 - Bootcamp Alkemy

## 📅 Fecha

Febrero 2026

## 📄 Licencia

Este proyecto fue desarrollado con fines educativos para el bootcamp de Desarrollo de Aplicaciones Moviles Android dentro del programa Talento Digital de Sence.

## 🙏 Agradecimientos

- Sence e Infocal por el bootcamp y la oportunidad de aprendizaje
- Instructores y mentores del programa
- Compañeros de bootcamp por el apoyo y colaboración

---

⭐ Si este proyecto te fue útil, no olvides darle una estrella en GitHub
