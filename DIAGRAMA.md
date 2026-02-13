## 📐 Diagrama de Clases (UML)
```
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
│   double, Moneda)│             │
│ + getTitular():  │             │
│   String         │  ┌──────────┴─────────────────┐
│ + getSaldo() :   │  │ ConvertidorMonedaSimple    │
│   double         │  ├────────────────────────────┤
│ + getMoneda() :  │  │ - CLP_TO_USD : double      │
│   Moneda         │  │ - USD_TO_CLP : double      │
│ + depositar(     │  │ - CLP_TO_EUR : double      │
│   double) : void │  │ - EUR_TO_CLP : double      │
│ + retirar(double)│  │ - USD_TO_EUR : double      │
│   : boolean      │  │ - EUR_TO_USD : double      │
│ + toString() :   │  ├────────────────────────────┤
│   String         │  │ + convertir(double, String,│
└────┬─────────────┘  │     String) : double       │
     │ usa            └────────────────────────────┘
     │
     ▼
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
──────────────────
◆  = Composición
△  = Herencia/Implementación
──> = Asociación/Uso
<<interface>> = Interfaz
<<enumeration>> = Enumeración
```
