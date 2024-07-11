# News App

News App is an Android application that allows users to browse breaking news, save articles for later reading, and search for specific news articles using the NewsAPI service. It follows the MVVM (Model-View-ViewModel) architecture pattern along with Clean Architecture principles for separation of concerns and testability.

## Features

- **Breaking News Tab:** Displays the latest breaking news fetched from the NewsAPI.
- **Saved News Tab:** Shows articles saved by the user for offline reading, stored in a local Room database.
- **Search News Tab:** Allows users to search for news articles based on keywords or phrases using the NewsAPI.

## Libraries Used

- **Retrofit:** For making network requests to the NewsAPI and handling API responses.
- **Glide:** For efficient asynchronous image loading and caching.
- **Room:** For local database storage to save and retrieve articles that users have saved.
- **Navigation Component:** For handling navigation between different fragments in the app.
- **ViewModel and LiveData:** For managing UI-related data in a lifecycle-conscious way and sharing data between fragments.
- **Coroutines:** Used for managing background threads and performing asynchronous tasks.

## Architecture

The app is structured using the MVVM architecture pattern, with the following layers:

- **Presentation Layer:** Contains UI components (fragments and activities) and ViewModels. ViewModel communicates with the domain layer and exposes states/data to the UI layer using LiveData.
  
- **Domain Layer:** Contains business logic and interacts with the data layer to fetch and manipulate data. It consists of use cases or interactors.

- **Data Layer:** Manages the data from different sources. Uses Repository pattern to provide an abstraction layer over the different data sources (local Room database and remote NewsAPI).

## Installation

1. Clone the repository from GitHub:
  ```
    git clone https://github.com/dharachodvadiya/news-app.git
  ```

2. Open the project in Android Studio.

3. Add your NewsAPI key in the `local.properties` file:
  ```
    news_api_key="your_news_api_key"
  ```

4. Build and run the app on an Android emulator or a physical device.

## Configuration

To configure the app with your own NewsAPI key:

1. Go to [NewsAPI](https://newsapi.org/) and sign up to get your API key.

2. Replace `"your_news_api_key"` in the `local.properties` file with your actual NewsAPI key.

## Contributing

Contributions are welcome! If you have any ideas, suggestions, or bug fixes, please open an issue or submit a pull request.
  
