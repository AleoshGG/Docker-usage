# ----- Etapa 1: BUILD -----
# Usamos la imagen oficial de Node.js (LTS).
FROM node:20 AS builder
LABEL authors="alexis"

WORKDIR /app

# Copiar package.json y package-lock.json
COPY package*.json ./

# Instalar dependencias
RUN npm install

# Copiar todo el código fuente
COPY . .

# Construir la app de Angular para producción
RUN npm run build -- --configuration production

# ----- Etapa 2: FINAL -----
# Empezamos desde una imagen 'slim' de Node para reducir el tamaño
FROM node:20-slim

WORKDIR /app

# Instalar 'serve' globalmente.
RUN npm install -g serve

# Copiar solo los archivos construidos desde la etapa 'builder'
COPY --from=builder /app/dist/demofront/browser/ .

# Añadir un usuario no-root por seguridad (buena práctica)
RUN addgroup --system appgroup && \
    adduser --system --ingroup appgroup --no-create-home appuser
RUN chown -R appuser:appgroup /app
USER appuser

# 'serve' corre en el puerto 3000 por defecto 
EXPOSE 3000

# El comando 'serve -s' sirve el directorio actual ('.')
# y la bandera '-s' es CLAVE para que el routing de Angular (SPA) funcione.
ENTRYPOINT ["serve", "-s", "."]