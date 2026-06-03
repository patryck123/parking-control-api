# 🚗 Parking Control API

Sistema de controle de estacionamento com check-in e check-out de veículos.

## 📋 Sobre o Projeto

API para gerenciar vagas de um estacionamento. Controla a entrada e saída de veículos, rastreia por placa, calcula o tempo de permanência e indica vagas disponíveis por tipo (CARRO, MOTO, CAMINHÃO).

## ✨ Funcionalidades

- ✅ Cadastrar vagas por tipo (CAR, MOTORCYCLE, TRUCK)
- ✅ Registrar entrada de veículo (check-in com placa e modelo)
- ✅ Registrar saída (check-out com hora de saída)
- ✅ Consultar vagas disponíveis
- ✅ Buscar vaga por placa do veículo
- ✅ Listar histórico por placa
- ✅ Status das vagas: AVAILABLE, OCCUPIED

## 🔗 Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/parking` | Listar todas as vagas |
| GET | `/api/parking/available` | Vagas disponíveis |
| GET | `/api/parking/{id}` | Buscar vaga por ID |
| POST | `/api/parking` | Criar vaga |
| POST | `/api/parking/{id}/checkin` | Check-in de veículo |
| POST | `/api/parking/{id}/checkout` | Check-out de veículo |
| GET | `/api/parking/plate/{plate}` | Buscar por placa |

## 🛠️ Tecnologias

- Java 17 · Spring Boot 3.2 · MySQL · Maven · Lombok
