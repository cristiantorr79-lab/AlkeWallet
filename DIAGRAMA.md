┌────────────────────────────┐
│           Wallet           │
├────────────────────────────┤
│ - cuenta : Cuenta          │
│ - convertidor :            │
│   ConvertidorMoneda        │
├────────────────────────────┤
│ + consultarSaldo() : double│
│ + recargarSaldo(double)    │
│ + convertirSaldo(String)   │
└─────────────┬──────────────┘
              │
              │ asociación
              ▼
┌────────────────────────────┐
│           Cuenta           │
├────────────────────────────┤
│ - saldo : double           │
├────────────────────────────┤
│ + getSaldo() : double      │
│ + recargar(double)         │
│ + descontar(double)        │
└────────────────────────────┘


┌─────────────────────────────────────┐
│        <<interface>>                 │
│        ConvertidorMoneda             │
├─────────────────────────────────────┤
│ + convertir(double, String) : double│
└───────────────▲─────────────────────┘
                │ implementación
                │
┌─────────────────────────────────────┐
│     ConvertidorMonedaSimple          │
├─────────────────────────────────────┤
│ - TASA_USD : double                  │
├─────────────────────────────────────┤
│ + convertir(double, String) : double │
└─────────────────────────────────────┘
