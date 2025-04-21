# Budget Planner Application

This is a full-stack budgeting tool built with:
-**Java + Spring Boot** (backend)
-**PostgreSQL + Flyway** (database + migrations)
-**React + TypeScript + Vite** (frontend)
-**Yarn** for frontend package management
-**Test-Driven Development** (TDD) approach

---

## Project Structure

budget-planner/
  backend/# Spring Boot
  frontend/# Vite+React+TS

---

## Key Features 

-Create, updatem and delete budget items
-Visual pie chart breakdown of expenses
-Expert-based financial tips based on results

---

## Testing Approach

This project uses **strict TDD**:
-All production code follows a failing test
-Backend tests using JUnit + WebMvcTest
-Frontend tests using Vitest + React Testing Library

## Dev Setup

### Backend:
cd backend
./gradlew bR

### Frontend:
cd frontend
yarn install
yarn dev
