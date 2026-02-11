# Requisitos - ServicioReservas (Pizzería)

## Explicación
https://www.youtube.com/watch?v=e6jhmN3dD7Y

## Funcionalidad Principal
- **Crear reservas**: El sistema debe permitir crear nuevas reservas de clientes en la pizzería

## Reglas de Negocio

### Validación de Reservas
- **Email obligatorio**: El email del cliente no puede ser nulo ni estar vacío
- **Número de personas válido**: El número de personas debe ser mayor que 0
- **Límite máximo de personas**: No se permiten reservas para más de 8 personas por mesa
- **Umbral de reserva grande**: Se considera reserva grande cuando el número de personas es mayor a 6
- **Dobles reservas**: Si una persona ya ha reservado no se le permite volver a reservar

### Almacenamiento
- **Archivo de texto**: Las reservas se almacenan en el archivo `reservas_opcion1.txt`
- **Formato de registro**: Cada reserva se guarda en una línea con formato: `email;numeroPersonas;reservaGrande`
- **Modo append**: Las nuevas reservas se añaden al final del archivo

### Cambio en los requisitos
- Nuestre jefe nos pide que modernicemos un poco el sistema, ahora quiere guardarlo como es lógico en una base de datos
- **BBDD**: Se quiere guardar en una base de datos, en una tabla Reserva

## Manejo de Errores

### Excepciones de Validación
- `IllegalArgumentException` para datos inválidos (email nulo/vacío, número de personas inválido)
- Mensajes específicos para cada tipo de error de validación

### Excepciones de Negocio
- `IllegalStateException` cuando un cliente ya tiene una reserva existente
- `RuntimeException` para errores de lectura/escritura de archivo

## Estructura de Datos

### Clase Reserva
- **email**: String - Identificador único del cliente
- **numeroPersonas**: int - Cantidad de personas para la reserva
- **reservaGrande**: boolean - Indica si es una reserva grande (>6 personas)


