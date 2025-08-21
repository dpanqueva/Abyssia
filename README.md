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

# 🌌 Abyssia – Plataforma SaaS para Consultas Inteligentes sobre Documentos Corporativos

Abyssia es una solución SaaS que transforma documentos empresariales en conocimiento accesible y automatizable. Mediante una arquitectura moderna basada en microservicios, IA generativa y conectores seguros, Abyssia permite a las organizaciones consultar sus propios documentos sin moverlos de sus repositorios.

---

## 🚀 Visión General

Abyssia permite:

- Consultar documentos corporativos mediante chat inteligente.
- Automatizar flujos de conocimiento sin comprometer la privacidad.
- Integrarse fácilmente con repositorios como Amazon S3 y GitHub.

La arquitectura se compone de:

- **Frontend Angular**: interfaz intuitiva para usuarios y administradores.
- **Backend Spring Boot**: orquestación, seguridad y gestión de usuarios.
- **Microservicio IA (FastAPI)**: procesamiento semántico y generación de respuestas vía RAG (Retrieval-Augmented Generation).

---

## 🧩 Componentes Principales

### 1. Frontend – Angular

- Dashboard de administración y configuración de conectores.
- Chat corporativo para consultas semánticas.
- Visualización de métricas, auditoría y respuestas generadas.
- Consumo de APIs REST y GraphQL.

### 2. Backend – Spring Boot

- **API Gateway**: autenticación y distribución de solicitudes.
- **Servicios**:
  - `Document Service`: ingesta y metadatos.
  - `User & Tenant Service`: multi-tenancy, roles y permisos.
  - `Audit & Metrics Service`: trazabilidad y cumplimiento.
- **Seguridad**:
  - OAuth2/OIDC (Keycloak u otros).
  - Control de acceso por rol y tenant.
  - Tokens temporales para conectores externos.

### 3. Microservicio de IA – Python / FastAPI

- **Ingesta**:
  - Descarga desde S3/GitHub.
  - OCR y conversión a texto.
  - Chunking y embeddings.
- **Consulta**:
  - Recuperación semántica (Retriever).
  - Generación de respuestas con citas (LLM Generator).
  - Guardrails: confianza, políticas de “no-answer”, trazabilidad.

### 4. Repositorios Externos

- **Amazon S3**: acceso mediante IAM temporal o claves del cliente.
- **GitHub**: acceso OAuth y sincronización vía webhooks.

### 5. Almacenamiento Interno

| Componente         | Uso principal                                  |
|--------------------|------------------------------------------------|
| PostgreSQL         | Metadatos, usuarios, auditoría                 |
| Vector DB (pgvector/Qdrant) | Embeddings para búsqueda semántica     |
| Cache (opcional)   | Reducción de latencia en consultas recientes   |

### 6. Comunicación y Eventos

- Redis / RabbitMQ / Kafka para eventos internos.
- Webhooks para sincronización con repositorios externos.

### 7. Seguridad y Cumplimiento

- Cifrado TLS en tránsito.
- No se almacenan documentos originales.
- Auditoría completa de accesos y consultas.
- Compatible con normativas: **HIPAA**, **GDPR**, **ISO27001**.

---

## 🔄 Flujo de Información (MVP – S3 y GitHub)

```mermaid
graph TD
A[Usuario registra conector] --> B[Abyssia valida credenciales]
B --> C[Microservicio IA descarga y normaliza documentos]
C --> D[Genera chunks y embeddings]
D --> E[Usuario realiza consulta vía chat]
E --> F[Backend identifica chunks relevantes]
F --> G[LLM genera respuesta con citas]
G --> H[Respuesta + trazabilidad al usuario]
H --> I[Auditoría registra evento y métricas]

