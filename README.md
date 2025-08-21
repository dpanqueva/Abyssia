# Abyssia
# 📘 Propuesta Técnica – Plataforma de IA Transversal Basada en Documentos

## 1. Resumen Ejecutivo

El presente documento propone el desarrollo de una **plataforma de inteligencia artificial transversal** que permite a empresas de cualquier industria **alimentar a un asistente virtual con sus documentos internos** (protocolos, contratos, manuales, reglamentos, inventarios) para resolver incidencias, responder preguntas frecuentes y automatizar procesos.

Los sectores iniciales de validación serán **salud** e **inmobiliario**, dos industrias con gran volumen de documentación, problemas recurrentes de gestión de información y baja digitalización.

---

## 2. Problema

* **Salud:** exceso de documentación (protocolos médicos, normativas, seguros, facturación) que genera pérdida de tiempo, errores y carga administrativa para personal médico y administrativo.
* **Inmobiliario:** gran cantidad de contratos, reglamentos, inventario de propiedades y preguntas repetitivas de clientes; procesos lentos y poco eficientes.
* **Problema transversal:** en cualquier empresa, **buscar, interpretar y aplicar información en documentos es lento, costoso y propenso a errores**.

---

## 3. Solución Propuesta

Una **plataforma SaaS** que permite a las empresas:

1. **Subir documentos** (PDF, Word, Excel, imágenes escaneadas).
2. **Indexarlos y procesarlos** con técnicas de IA (chunking, embeddings, búsqueda semántica, RAG).
3. **Consultar un chat corporativo** que responde basándose en la documentación, con **citas exactas de los documentos**.
4. **Automatizar acciones**: generación de correos, tickets, contratos o reportes desde las respuestas.
5. **Gestionar conocimiento**: versiones de documentos, caducidad, permisos de acceso y auditoría.

---

## 4. Arquitectura de Referencia

### Componentes Principales

* **Frontend:** Angular (UI de chat, carga de documentos, panel de métricas).
* **Backend:** Spring Boot (API Gateway, servicios de usuarios, documentos, auditoría).
* **Microservicio IA (Python FastAPI):** encargado de procesamiento RAG (chunking, embeddings, re-ranking, generación de respuestas).
* **Almacenamiento:** MinIO/S3 para documentos.
* **Base de Datos Relacional:** PostgreSQL (multi-tenant, usuarios, auditoría).
* **Base de Datos Vectorial:** pgvector o Qdrant (almacenamiento de embeddings).
* **Colas de Mensajes:** Redis/RabbitMQ/Kafka (para ingestión y eventos).
* **Seguridad:** Keycloak + Spring Security (RBAC, OAuth2/OIDC).

### Flujo Principal

1. Usuario sube documento → Spring Boot lo guarda en S3 + metadatos en PostgreSQL.
2. Evento de nuevo documento → microservicio Python procesa (OCR, chunking, embeddings).
3. Pregunta del usuario → API Gateway → RAG recupera chunks relevantes → LLM genera respuesta con citas.
4. Respuesta entregada al chat + auditoría en logs y métricas.

### Seguridad y Cumplimiento

* **Cifrado en tránsito (TLS) y en reposo (AES-256).**
* **Aislamiento multi-tenant con RLS en PostgreSQL.**
* **Auditoría completa de accesos y consultas.**
* **Cumplimiento GDPR y adaptable a HIPAA en salud.**

---

## 5. Roadmap (12 Meses)

### Fase 1 (0–2 meses)

* Entrevistas con clientes en salud e inmobiliario.
* Diseño del MVP (subida de documentos + chat básico).
* Golden set de preguntas frecuentes.

### Fase 2 (2–4 meses)

* Desarrollo MVP (Spring Boot + Angular + FastAPI RAG).
* Primer piloto con clínica y agencia inmobiliaria.
* Validación de precisión (>70% respuestas correctas con cita).

### Fase 3 (4–6 meses)

* Mejoras de precisión (retrieval híbrido, re-ranking).
* Panel de métricas y feedback en la app.
* Automatización básica (generación de correos/tickets).

### Fase 4 (6–9 meses)

* Ampliación a 5–10 clientes de pago.
* Implementación de multi-tenancy avanzado.
* Seguridad reforzada (auditoría completa, retención de datos).

### Fase 5 (9–12 meses)

* Escalamiento comercial (casos de éxito, demos, pricing SaaS).
* Preparación para inversión (si se desea crecer rápido).

---

## 6. Modelo de Negocio

* **Freemium limitado:** hasta 50 consultas/mes.
* **Planes SaaS por volumen:**

  * Starter: 99–199 €/mes (20 usuarios, 3.000 consultas).
  * Growth: 499–999 €/mes (100 usuarios, 20.000 consultas).
  * Enterprise: a medida (on-premises, integraciones, auditorías).

---

## 7. Casos de Uso Iniciales

### Salud

* Responder preguntas administrativas: “¿Cómo facturo con seguro X?”
* Proporcionar protocolos médicos y de bioseguridad.
* Reducir carga administrativa del personal clínico.

### Inmobiliario

* Consulta de propiedades filtradas.
* Respuestas inmediatas sobre cláusulas contractuales.
* Automatización de correos y minutas.

### Transversal

* Soporte interno para empleados en cualquier sector.
* Resolución de incidencias basada en políticas internas.
* Automatización de tickets y reportes.

---

## 8. Riesgos y Mitigaciones

* **Alucinaciones de la IA:** uso de RAG + política de "no answer" si no hay evidencia.
* **Documentación desactualizada:** caducidad + alertas de actualización.
* **Costos de LLM:** caching, modelos mixtos (cloud + open source locales).
* **Adopción baja:** formación inicial, champions internos, casos de uso claros.

---

## 9. Presupuesto Estimado MVP

* Infraestructura inicial: 200–500 €/mes.
* LLM/embeddings: 200–600 €/mes (piloto).
* Legal y compliance: 2.000–3.000 €.
* Total inicial: 5.000–10.000 € (primeros 3–6 meses).

---

## 10. Conclusión

Esta propuesta define una **plataforma transversal de IA** que resuelve un dolor universal: el acceso y aplicación rápida de información contenida en documentos internos.

La combinación de **Spring Boot + Angular + microservicio Python para IA** ofrece un stack robusto, escalable y compatible con entornos corporativos.

Los pilotos en salud e inmobiliario permitirán validar valor de negocio, ajustar la plataforma y preparar el camino hacia escalamiento global.

---

# 🏗 Arquitectura de Software – Abyssia (versión Spring Boot IA)

![Arquitectura Abyssia](arquitectura/imagen.png)

---

## 1. Visión General

Abyssia es una plataforma SaaS que permite a las empresas consultar y automatizar conocimiento a partir de sus documentos, conservando los documentos en los repositorios del cliente (S3 o GitHub en el MVP).

Toda la lógica de backend, incluyendo la IA/RAG, se implementa en **Spring Boot**, facilitando integración, despliegue y mantenimiento.

---

## 2. Componentes Principales

### a) Frontend – Angular

- Dashboard de usuario y administración de fuentes externas.
- Chat corporativo para consultas a documentos.
- Visualización de métricas, logs de auditoría y respuestas generadas.
- Consumo de APIs REST/GraphQL del backend.

### b) Backend – Spring Boot

- **API Gateway / Orquestador**: recibe solicitudes, valida autenticación y distribuye llamadas a los servicios internos.

#### Servicios principales:

- `Document Service`: registra metadatos de documentos y configuraciones de conectores, inicia pipelines de ingesta.
- `User & Tenant Service`: gestión de usuarios, roles, permisos y multi-tenancy.
- `IA / RAG Service`:
  - Ingesta y normalización de documentos.
  - Chunking, embeddings y vectorización usando librerías Java compatibles (ej. Deeplearning4j, Java bindings de OpenAI/LLM).
  - Motor de recuperación semántica (BM25 + embeddings).
  - Generación de respuestas con LLM.
  - Guardrails: políticas de “no answer”, confianza mínima y trazabilidad de documentos.
- `Audit & Metrics Service`: registra consultas, latencias, uso y cumplimiento normativo.

#### Seguridad:

- OAuth2/OIDC para autenticación (Keycloak u otro proveedor).
- Control de acceso por rol y tenant.
- Tokens temporales para acceso a conectores externos.

### c) Repositorios Externos

- **Amazon S3**: almacenamiento de objetos corporativos. Abyssia accede usando roles IAM temporales o claves proporcionadas por el cliente.
- **GitHub**: repositorios privados, wikis o markdown. Abyssia accede vía OAuth y usa webhooks para sincronización de cambios.

### d) Almacenamiento Interno

| Componente                              | Uso principal                                      |
|-----------------------------------------|---------------------------------------------------|
| PostgreSQL                              | Metadatos de documentos, usuarios, roles, auditoría |
| Vector DB (pgvector o Qdrant + Java)    | Embeddings para búsqueda semántica                |
| Cache temporal (opcional)               | Resultados de consultas recientes                 |

### e) Comunicación y Eventos

- Redis / RabbitMQ / Kafka para eventos de ingesta y actualización.
- Webhooks para sincronización automática con repositorios externos.

### f) Seguridad y Cumplimiento

- Cifrado TLS para datos en tránsito.
- Embeddings y metadatos seguros, sin almacenar los documentos originales.
- Auditoría completa de accesos, consultas y cambios de configuración.
- Escalable para cumplir normativas de privacidad: **HIPAA**, **GDPR**, **ISO27001**.

---

## 3. Flujo de Información (MVP – S3 y GitHub)

```mermaid
graph TD
A[Usuario registra conector externo] --> B[Spring Boot valida credenciales]
B --> C[Descarga y normaliza documentos]
C --> D[Chunking y generación de embeddings]
D --> E[Almacenamiento en vector DB]
E --> F[Usuario realiza consulta vía chat]
F --> G[Spring Boot busca chunks relevantes]
G --> H[LLM genera respuesta con citas]
H --> I[Respuesta con trazabilidad al usuario]
I --> J[Auditoría registra evento y métricas]

