

## Spring Boot con OAuth 2.0 de Google - Despliegue en Kubernetes

Este proyecto implementa una API REST con Spring Boot que utiliza OAuth 2.0 de Google para autenticación. Este README proporciona instrucciones paso a paso para construir la imagen Docker y desplegarla en Kubernetes, ya sea manualmente con manifests YAML o utilizando Helm.

### Requisitos previos
- **Docker**: Instalado y configurado en tu máquina local.
- **Kubernetes**: Un clúster activo (por ejemplo, Minikube local o un clúster en la nube).
- **kubectl**: Configurado para comunicarse con tu clúster Kubernetes.
- **Helm**: Instalado si planeas usar Helm (opcional).
- **Docker Hub**: Cuenta activa (en este caso, usuario `axelav95`).
- Credenciales de Google OAuth 2.0 (`client-id` y `client-secret`) obtenidas desde Google Cloud Console.

Nota: Para poder observar el comportamiento de esta aplicación en producción es necesario que la URI de Google, donde se
encuentran las credenciales, tenga un dominio, dado que Google no permite poner IP Externas.
---

## Estructura del proyecto
```
spring-boot-oauth2-google/
├── Dockerfile
├── pom.xml
├── src/
├── k8s/                  # Manifests YAML para Kubernetes
│   ├── deployment.yaml
│   ├── service.yaml
│   └── secret.yaml
├── helm/                 # Archivos de Helm
│   └── spring-boot-oauth2-chart/
│       ├── Chart.yaml
│       ├── values.yaml
│       └── templates/
│           ├── deployment.yaml
│           ├── service.yaml
│           └── secret.yaml
└── README.md
```

---

## Paso 1: Construir y subir la imagen a Docker Hub

1. **Inicia sesión en Docker Hub**:
   ```bash
   docker login
   ```
   Usa tu usuario (`axelav95`) y contraseña (o token de acceso si tienes 2FA).

2. **Construye la imagen Docker**:
   Desde la raíz del proyecto:
   ```bash
   docker build -t axelav95/spring-boot-oauth2-google:latest .
   ```

3. **Sube la imagen a Docker Hub**:
   ```bash
   docker push axelav95/spring-boot-oauth2-google:latest
   ```

4. **Verifica localmente (opcional)**:
   ```bash
   docker run -p 8080:8080 axelav95/spring-boot-oauth2-google:latest
   ```
   Accede a `http://localhost:8080/public` para probar.

---

## Opción 1: Despliegue manual con Kubernetes

### Preparación
Asegúrate de que las credenciales de Google OAuth estén codificadas en Base64 para el archivo `secret.yaml`. Por ejemplo:
- `echo -n "<TU_ID_DE_CLIENTE>" | base64`
- `echo -n "<TU_SECRETO_DE_CLIENTE>" | base64`
Edita `k8s/secret.yaml` con estos valores.

### Comandos
1. **Aplicar el Secret**:
   ```bash
   kubectl apply -f k8s/secret.yaml
   ```

2. **Aplicar el Deployment**:
   ```bash
   kubectl apply -f k8s/deployment.yaml
   ```

3. **Aplicar el Service**:
   ```bash
   kubectl apply -f k8s/service.yaml
   ```

4. **Verificar el despliegue**:
   ```bash
   kubectl get pods
   kubectl get services
   ```
   Si usas Minikube:
   ```bash
   minikube service spring-boot-oauth2-service --url
   ```
   Esto te dará la URL para acceder a la aplicación.

5. **Probar los endpoints**:
   Usa la URL proporcionada para acceder a:
   - `<URL>/public`
   - `<URL>/user` (requiere autenticación con Google).

### Limpieza (opcional)
Para eliminar los recursos:
```bash
kubectl delete -f k8s/service.yaml
kubectl delete -f k8s/deployment.yaml
kubectl delete -f k8s/secret.yaml
```

---

## Opción 2: Despliegue con Helm

### Preparación
Edita `helm/spring-boot-oauth2-chart/values.yaml` con tus credenciales de Google OAuth:
```yaml
env:
  googleClientId: "<TU_ID_DE_CLIENTE>"
  googleClientSecret: "<TU_SECRETO_DE_CLIENTE>"
```

### Comandos
1. **Navega al directorio del chart**:
   ```bash
   cd helm/spring-boot-oauth2-chart
   ```

2. **Instalar el chart**:
   ```bash
   helm install spring-boot-oauth2 .
   ```
   - Esto creará un release llamado `spring-boot-oauth2`.
   - Usa `--set` para sobrescribir valores si es necesario, por ejemplo:
     ```bash
     helm install spring-boot-oauth2 . --set env.googleClientId=<nuevo-id>
     ```

3. **Verificar el despliegue**:
   ```bash
   kubectl get pods
   kubectl get services
   ```
   Si usas Minikube:
   ```bash
   minikube service spring-boot-oauth2-service --url
   ```

4. **Probar los endpoints**:
   Usa la URL proporcionada para acceder a:
   - `<URL>/public`
   - `<URL>/user` (requiere autenticación con Google).

5. **Actualizar el chart (opcional)**:
   Si cambias algo en `values.yaml`:
   ```bash
   helm upgrade spring-boot-oauth2 .
   ```

6. **Limpieza (opcional)**:
   Para eliminar el release:
   ```bash
   helm uninstall spring-boot-oauth2
   ```

---

## Notas adicionales
- **Minikube**: Si usas Minikube localmente, inicia el clúster con `minikube start` antes de desplegar.
- **Credenciales seguras**: Las credenciales en `secret.yaml` (Kubernetes) o `values.yaml` (Helm) deben manejarse con cuidado. En producción, considera usar un gestor de secretos como HashiCorp Vault.
- **Escalabilidad**: Ajusta el número de réplicas en `k8s/deployment.yaml` o `helm/spring-boot-oauth2-chart/values.yaml` (`replicaCount`) según sea necesario.
- **URL externa**: Si despliegas en un clúster en la nube, el `Service` tipo `LoadBalancer` te dará una IP externa. Usa `kubectl get svc` para obtenerla.

---

## Solución de problemas
- **Imagen no encontrada**: Asegúrate de que `axelav95/spring-boot-oauth2-google:latest` esté en Docker Hub y que el clúster tenga acceso a internet.
- **Errores de autenticación**: Verifica que las credenciales en el `Secret` o `values.yaml` sean correctas.
- **Pods en estado CrashLoopBackOff**: Usa `kubectl logs <nombre-del-pod>` para depurar.

---
