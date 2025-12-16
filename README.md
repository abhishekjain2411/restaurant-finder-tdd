# Restaurant Finder Application - TDD Implementation

A Java-based restaurant finder application demonstrating Test-Driven Development (TDD) principles, complete with UML diagrams and comprehensive test coverage.

## Project Overview

This application allows users to:
- Search for restaurants
- View restaurant details and operating hours
- Browse menu items with prices
- Calculate total order amount (without placing actual orders)

## Features

### Part 1: UML Diagrams
- **Use Case Diagram**: Shows actors, use cases, and their relationships across 5 modules
- **Activity Diagram**: Illustrates the complete user workflow from search to cart management

### Part 2: Core Implementation (50%)
- Restaurant opening/closing time validation
- Menu management (add/remove items)
- Exception handling for non-existent items
- Comprehensive test suite with Mockito

### Part 3: TDD Feature - Order Total Calculation (20%)
- Calculate total price for selected items
- Support for multiple items and duplicates
- Developed using Test-Driven Development approach
- 98%+ code coverage

## Project Structure

```
restaurant-finder-tdd/
├── src/
│   ├── Restaurant.java
│   ├── Item.java
│   └── itemNotFoundException.java
├── test/
│   └── RestaurantTest.java
├── diagrams/
│   ├── use-case-diagram.png
│   └── activity-diagram.png
└── README.md
```

## Technologies Used

- **Java**: Core programming language
- **JUnit 5**: Testing framework
- **Mockito**: Mocking framework for unit tests
- **PlantUML**: UML diagram generation

## Test Coverage

The project includes comprehensive test cases covering:
- Restaurant opening/closing validation (with mocked time)
- Menu item addition/removal
- Exception handling
- Order total calculation (single item, multiple items, duplicates, empty cart)

**Expected Coverage**: 98%+ line coverage

## How to Run

### Prerequisites
- Java 8 or higher
- JUnit 5
- Mockito

### Compile and Run Tests

```bash
# Compile
javac -cp .:junit-platform-console-standalone.jar src/*.java test/*.java

# Run tests
java -jar junit-platform-console-standalone.jar --class-path . --scan-class-path
```

## TDD Approach (Part 3)

The order total calculation feature was developed following TDD principles:

1. ✅ **Requirements defined**: Input (List of item names), Output (total price)
2. ✅ **Failing tests written first**: 5 test cases created before implementation
3. ✅ **Feature implemented**: `calculateOrderTotal()` method added
4. ✅ **Tests pass**: All tests green after implementation
5. ✅ **High coverage**: 98%+ code coverage achieved

## Key Design Decisions

- **Method Placement**: `calculateOrderTotal()` in `Restaurant` class (logical owner)
- **Input Format**: `List<String>` for flexibility
- **Return Type**: `double` for price precision
- **Stateless Design**: No order storage, just calculation
- **Helper Method**: Private `findItemByName()` for code reusability

## UML Diagrams

### Use Case Diagram
Shows 5 modules:
1. Search Module
2. Restaurant Details Module
3. Menu Management Module
4. Cart Module
5. Navigation Module

### Activity Diagram
Illustrates complete user flow with decision points and error handling.

## Author

Abhishek Jain

## License

This project is created for educational purposes.
