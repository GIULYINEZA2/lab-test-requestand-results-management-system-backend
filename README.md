# Lab Test Request and Results Management System

**Student:** Ndayishimiye Ineza Giuly  
**Student ID:** 27390  
**Course:** INSY 8322 – Web Technology and Internet  
**Project:** Lab Test Request and Results Management System Backend

---

## Prerequisites

- Java 17+
- Maven (or use the included `mvnw`)
- PostgreSQL running locally

## Database Setup

Create a PostgreSQL database named `lab_test_db`:

```sql
CREATE DATABASE lab_test_db;
```

Update credentials in `src/main/resources/application.properties` if your PostgreSQL username/password differ from `postgres/postgres`.

## How to Run

```bash
cd lab-test-requestandresults-management-system
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`.

---

## API Endpoints Summary

### LabSections — `/api/lab-sections`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/save` | A1/A2 – Save a lab section |
| GET | `/all` | A2 – Get all lab sections |
| GET | `/{id}` | A2 – Get by id |
| PUT | `/update/{id}` | A2 – Update |
| DELETE | `/delete/{id}` | A2 – Delete |
| GET | `/unused` | C3 – Lab sections nobody holds |

### Workstations — `/api/workstations`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/save` | A2 – Save |
| GET | `/all` | A2 – Get all |
| GET | `/{id}` | A2 – Get by id |
| PUT | `/update/{id}` | A2 – Update |
| DELETE | `/delete/{id}` | A2 – Delete |
| GET | `/free` | C2 – Free workstations |

### Technicians — `/api/technicians`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/save` | A2 – Save |
| GET | `/all` | A2 – Get all |
| GET | `/{id}` | A2 – Get by id |
| PUT | `/update/{id}` | A2 – Update |
| DELETE | `/delete/{id}` | A2 – Delete |
| GET | `/by-lab-section?name=hematology` | C1 – Technicians by lab section |
| PUT | `/{technicianId}/lab-sections/{labSectionId}` | D3 – Add lab section |
| PATCH | `/{technicianId}/workstation?workstationNumber=4` | D4 – Move workstation |

### Examinees — `/api/examinees`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/save` | A2 – Save |
| GET | `/all` | A2 – Get all |
| GET | `/{id}` | A2 – Get by id |
| PUT | `/update/{id}` | A2 – Update |
| DELETE | `/delete/{id}` | A2 – Delete |
| GET | `/by-last-name?lastName=uwimana` | B1 – Find by last name |
| GET | `/of-technician/{technicianId}` | C4 – Examinees of a technician |

### Test Requests — `/api/test-requests`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/save` | A2/D1 – Save (with business rules) |
| GET | `/all` | A2 – Get all |
| GET | `/{id}` | A2 – Get by id |
| PUT | `/update/{id}` | A2 – Update (no status change) |
| DELETE | `/delete/{id}` | A2/E3 – Delete (protected) |
| GET | `/by-status?status=REQUESTED` | B2 – By status |
| GET | `/between?start=2026-10-01&end=2026-10-31` | B3 – Date range |
| PATCH | `/{id}/status?status=SAMPLED` | D2 – Status flow |
| GET | `/stats/by-status` | F1 – Count per status |
| PATCH | `/cancel-day?technicianId=...&date=2026-10-20` | F2 – Bulk cancel |
| GET | `/page?page=0&size=5&sort=requestDate,desc` | Bonus – Pagination |

### Test Results — `/api/test-results`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/save?testRequestId=...` | E1 – Save result |
| GET | `/examinee/{hospitalNumber}` | E2 – History by examinee |
| DELETE | `/delete/{id}` | E3 – Delete result |

### Dashboard — `/api/dashboard`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/summary` | Bonus – System summary |

---

## Seed Data Order (Postman)

Enter data in this order through the save endpoints:

1. POST 4 lab sections (Hematology, Microbiology, Biochemistry, Serology)
2. POST 4 workstations (numbered 1–4)
3. POST 3 technicians (assign workstations 1, 2, 3)
4. PUT technician lab sections (D3 endpoint)
5. POST 5 examinees (two with lastName "Uwimana", one with hospitalNumber HN-20451)
6. POST 10 test requests (spread Oct–Nov 2026, all 4 statuses)
7. PATCH 2 test requests to PROCESSED status (D2 endpoint)
8. POST test results for PROCESSED requests (E1 endpoint)
