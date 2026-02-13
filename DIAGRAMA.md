## Diagrama de Clases (UML)

┌──────────────────────────────────────┐
│            WalletApp                 │
│         <<main class>>               │
├──────────────────────────────────────┤
│ - scanner : Scanner                  │
│ - wallet : Wallet                    │
│ - df : DecimalFormat                 │
├──────────────────────────────────────┤
│ + main(String[]) : void              │
│ - validarCuentaCreada() : boolean    │
│ - seleccionarMoneda() : Moneda       │
└───────────────┬──────────────────────┘
                │ usa
                ▼
┌──────────────────────────────────────┐
│             Wallet                   │
├──────────────────────────────────────┤
│ - cuenta : Cuenta                    │
│ - convertidor : ConvertidorMoneda    │
├──────────────────────────────────────┤
│ + Wallet(Cuenta, ConvertidorMoneda)  │
│ + getMoneda() : Moneda               │
│ + getSaldo() : double                │
│ + depositar(double) : void           │
│ + retirar(double) : boolean          │
│ + convertirSaldo(String) : double    │
└────┬─────────────────────┬───────────┘
     │ composición         │ usa
     │ ◆                   │
     ▼                     ▼
┌──────────────────┐  ┌─────────────────────────────┐
│     Cuenta       │  │    <<interface>>            │
├──────────────────┤  │   ConvertidorMoneda         │
│ - titular:String │  ├─────────────────────────────┤
│ - saldo : double │  │ + convertir(double, String, │
│ - moneda : Moneda│  │     String) : double        │
├──────────────────┤  └──────────▲──────────────────┘
│ + Cuenta(String, │             │ implementa
│   double, Moneda)│             │ ┌───────┐
│ + getTitular():  │             └─┤   △   │
│   String         │               └───────┘
│ + getSaldo() :   │                   │
│   double         │  ┌────────────────┴─────────────┐
│ + getMoneda() :  │  │ ConvertidorMonedaSimple      │
│   Moneda         │  ├──────────────────────────────┤
│ + depositar(     │  │ - CLP_TO_USD : double        │
│   double) : void │  │ - USD_TO_CLP : double        │
│ + retirar(double)│  │ - CLP_TO_EUR : double        │
│   : boolean      │  │ - EUR_TO_CLP : double        │
│ + toString() :   │  │ - USD_TO_EUR : double        │
│   String         │  │ - EUR_TO_USD : double        │
└────┬─────────────┘  ├──────────────────────────────┤
     │ usa            │ + convertir(double, String,  │
     │                │     String) : double         │
     ▼                └──────────────────────────────┘
┌──────────────────┐
│   <<enumeration>>│
│      Moneda      │
├──────────────────┤
│ CLP              │
│ USD              │
│ EUR              │
├──────────────────┤
│ - codigo : String│
│ - simbolo: String│
├──────────────────┤
│ + getCodigo() :  │
│   String         │
│ + getSimbolo() : │
│   String         │
└──────────────────┘


LEYENDA:
────────────────────────────────
◆  = Composición (fuerte)
△  = Herencia/Implementación
───> = Asociación/Uso
<<interface>> = Interfaz
<<enumeration>> = Enumeración
```



