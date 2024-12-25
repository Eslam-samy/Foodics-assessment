# Simple E-Commerce Android Application

## Overview
This project is a simple Android application that allows users to browse categories, view products, and add products directly to their order. The app integrates mock APIs for fetching data and stores it locally using Room. The application follows the MVI architecture and utilizes Jetpack Compose with Material 3 for UI design.

## Features
### 1. Product Search
- Users can search for products by name.
- A search bar filters products in real-time as the user types.

### 2. Order Management
- Users can add products to the order by clicking directly on the product.
- The order view displays the total price and quantity of each product.
- A "View Order" button at the bottom shows the order summary and resets the order after viewing.

### 3. API Integration
- Data is fetched from mock APIs created using Mockaroo.
- Categories and products are retrieved and stored locally using Room for offline access.

### 4. User Interface (UI) and Screens
- **Tables Screen**: Displays categories and products.
- **Navigation Bar**: Provides access to Orders, Menu, and Settings screens.
- **Orders Screen**: Placeholder screen for future development.
- **Menu Screen**: Currently left empty.
- **Settings Screen**: Placeholder screen for future development.

### 5. Responsive Design
- The app functions in portrait mode only.
- The UI is responsive across different phone screen sizes and resolutions.

## Technologies Used
- **Ktor**: API calls
- **Koin**: Dependency injection
- **Room**: Local database
- **Material 3**: UI components
- **Jetpack Compose**: UI development
- **MVVM**: Architectural pattern

## API Schema
### Categories Endpoint
```json
[
  {
    "id": "1",
    "name": "Beverages"
  },
  {
    "id": "2",
    "name": "Snacks"
  }
]
```

### Products Endpoint
```json
[
  {
    "id": "101",
    "category": {
      "id": "1",
      "name": "Beverages"
    },
    "name": "Orange Juice",
    "description": "Freshly squeezed orange juice.",
    "image": "https://example.com/orange_juice.png",
    "price": 3.5
  },
  {
    "id": "102",
    "category": {
      "id": "2",
      "name": "Snacks"
    },
    "name": "Potato Chips",
    "description": "Crispy and salted chips.",
    "image": "https://example.com/chips.png",
    "price": 1.75
  }
]
```

## Project Structure
- **app**: Main application logic.
- **data**: Contains Room entities, DAOs, and repository classes.
- **di**: Koin modules for dependency injection.
- **ui**: Jetpack Compose screens and components.
- **network**: Ktor API service for fetching data.

## How to Run the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/Eslam-samy/Foodics-assessment
   ```
2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run the app on an emulator or physical device.

## GitHub Repository
- Repository URL: [[[https://github.com/Eslam-samy/Foodics-assessment](https://github.com/Eslam-samy/Foodics-assessment)]
- Branching Strategy:
  - **Main Branch**: Production-ready code.
  - **Dev Branch**: Development branch with active feature development.
  - **Pull Requests (PRs)**: Minimum of 3 PRs merged into Dev.

## Deliverables
- Fully functional Android project with MVI architecture.
- Working API integration using Ktor.
- Room database for local storage.
- Responsive UI developed with Jetpack Compose and Material 3.
- Clear and organized codebase following clean architecture principles.



