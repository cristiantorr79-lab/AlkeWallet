## Diagrama de Clases (UML)

    ┌────────────────────────────┐
    │           Wallet           │
    ├────────────────────────────┤
    │ - cuenta : Cuenta          │
    │ - convertidor :            │
    │   ConvertidorMoneda        │
    ├────────────────────────────┤
    │ + consultarSaldo() : double│
    │ + depositar(double)        │
    │ + convertirSaldo(String)   │
    └─────────────┬──────────────┘
                  │ asociación
                  ▼
    ┌────────────────────────────┐
    │           Cuenta           │
    ├────────────────────────────┤
    │ - saldo : double           │
    ├────────────────────────────┤
    │ + getSaldo() : double      │
    │ + depositar(double)        │
    │ + retirar(double) : boolean│
    └────────────────────────────┘


    ┌─────────────────────────────────────┐
    │        <<interface>>                │
    │      ConvertidorMoneda              │
    ├─────────────────────────────────────┤
    │ + convertir(double, String) : double│
    └───────────────▲─────────────────────┘
                    │ implementación
    ┌─────────────────────────────────────┐
    │   ConvertidorMonedaSimple            │
    ├─────────────────────────────────────┤
    │ - TASA_USD : double                  │
    ├─────────────────────────────────────┤
    │ + convertir(double, String) : double │
    └─────────────────────────────────────┘
