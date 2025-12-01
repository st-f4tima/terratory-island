<h1 align="center"><a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.herokuapp.com?font=Pixelify+Sans&weight=500&size=50&duration=1000&pause=1000&color=F7F7F7&center=true&vCenter=true&multiline=true&repeat=false&width=800&height=75&lines=%E2%9B%B5+Terratory+Island+%F0%9F%8F%9D%EF%B8%8F" alt="Typing SVG" /></a></h1>

<p align="center">
  <img src="assets/banner.gif" width="800">
</p>

<p align="center">
  <a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.herokuapp.com?font=Jersey+10&weight=500&size=16&duration=1000&pause=1000&color=F7F7F7&center=true&vCenter=true&multiline=true&repeat=false&width=600&height=100&lines=The+little+island+awaits+its+new+caretaker!+;Seasonal+algorithms+are+active%2C;guaranteeing+fresh+delivery+of+crops%2C+fish%2C+and+animals.;Proceed+with+gentle%2C+essential+resource+tending." alt="Typing SVG" /></a>
</p>

<p align="center">
  <a>
    <img alt="Programming Language" src="https://img.shields.io/badge/Language-Java-49AB53?logoColor=black&style=for-the-badge">
  </a>
  <a>
    <img alt="Course" src="https://img.shields.io/badge/Course-OOP-FEC148?logoColor=black&style=for-the-badge">
  </a>
  <a>
    <img alt="Contributors" src="https://img.shields.io/badge/Contributors-3-02A4DE?logoColor=black&style=for-the-badge">
  </a>
</p>

  ‎  
## 📌 Description

‎‎‎ ‎ ‎ ‎ Terratory Island is a console-based adventure game where you explore a mysterious island and develop it into your own territory. Changing seasons bring new challenges and story events. Level up by planting, fishing, and tending animals as you experience a journey full of discovery and excitement.

  ‎  
## 📦 Object-Oriented Programming Concepts

The project's demonstration of the four pillars of Object-Oriented Programming.

|**Pillar​  ​​  ​   ​  ​  | Implementation | Code Samples |
|-----------|----------------------------|-------------------|
| **Abstraction** | Defines core contracts and shared properties using **Abstract Classes**. All items and inventories follow these blueprints. | `Item`, `Inventory`, `Crop`, `Livestock`, `Fish` |
| **Inheritance** | Specialized classes inherit common fields and methods from `Item`. Establishes clean hierarchy in game entities. | `Crop`, `Livestock`, `Fish` → all inherit from `Item` |
| **Polymorphism** | Uses **Method Overriding** so methods like `sell()` and `createCopy()` behave differently depending on object type. | `sell()` is computed differently for crops (1.5×), livestock (age multiplier), fish (weight multiplier) |
| **Encapsulation** | Prevents direct field access using `private`/`protected`. Object values change only through **public getters/setters**. | `daysGrown` changes via `grow()`. Livestock’s `isFed` set via `feed()`. |


  ‎  
## 📁 Structure

```bash
terratory-island/
│
├── assets/
├── src/
│   ├── base/
│   ├── inventoryEntities/
│   ├── itemEntities/
│   │   ├── animal/
│   │   │   ├── egg/
│   │   │   ├── milk/
│   │   │   └── unique/
│   │   ├── crop/
│   │   │   ├── fall/
│   │   │   ├── spring/
│   │   │   └── summer/
│   │   └── fish/
│   │       ├── fall/
│   │       ├── spring/
│   │       ├── summer/
│   │       └── winter/
│   ├── managers/
│   ├── utils/
│   └── App.java  # Main application entry point
│
│── .gitignore
└── README.md
```

### Directory Description

- **Root Level**
  - `assets/` - Static resources like images
  - `.gitignore` - Git exclusion rules
  - `README.md` - Project documentation

- **src/base/**
  - Core game foundation classes including the main game controller, base inventory system, base item class, and player entity.

- **src/inventoryEntities/**
  - Specialized storage systems for different item categories - manages storage for crops, fish, livestock, and animal products.

- **src/itemEntities/**
  - Contains all game items organized by type:
    - `animal/` - Livestock categorized by product type (egg-layers, milk-producers, unique)
    - `crop/`- Seasonal farming crops (fall, spring, summer)
    - `fish/` - Seasonal fish species (fall, spring, summer, winter)

- **src/managers/**
  - Handle operations for crop planting/harvesting, fishing mechanics, livestock care, and inventory management in game manager.

- **src/utils/**
  - Helper utilities for user input validation.

  ‎  
<h1 align="center"> 🚀 How to Run the Program ⚙️</h1>

Follow these steps to play **Terratory Island**:

### Prerequisites
- JDK 8 or higher
- Java IDE (one of): IntelliJ IDEA, Eclipse, VS Code with Java Extension Pack, NetBeans

### Installation Steps
#### 1. Clone the Repository
- **Using Command Prompt:**
  ```bash
  git clone https://github.com/your-username/terratory-island.git
  cd terratory-island
  ```
#### 2. Compile the Program
- **Using Terminal:**
  ```bash
  cd src
  javac *.java
  ```

#### 4. Run the Game
- **Using Terminal:**
  ```bash
  java App.java
  ```

  ‎  
# 👥 Team of Authors 

| Name | Contribution | GitHub |
| :--- | :--- | :--- |
| **Auditor, Kishean Carisse** | Implemented and refined all **fish system mechanics**, including behavior logic and functional integration | [@carissseee](https://github.com/carissseee) |
| **Calabia, Geanne Margaret** | Developed **livestock-related features**, ensuring proper behavior handling and system functionality | [@ennage](https://github.com/ennage) |
| **Pura, Fatima** | Designed and programmed the **crop growth and management system**, including core logic and feature implementation | [@st-f4tima](https://github.com/st-f4tima) |

  ‎  
<h1 align="center">🌸 Acknowledgment 🌸</h1>

‎‎‎ ‎ ‎ ‎ We extend our deepest appreciation to everyone who contributed to the success of this project.


‎‎‎ ‎ ‎ ‎ First, our heartfelt gratitude goes to [Mr. Emmanuel Charlie Enriquez](21-03267@g.batstate-u.edu.ph), our instructor, for his guidance, advice, and continuous support throughout the development of this project. We also want to thank [Ms. Fatima Marie](https://github.com/marieemoiselle), for her guidance and valuable input during the earlier stages, and the foundation she helped us build.


‎‎‎ ‎ ‎ ‎ To our teammates, thank you for your hard work, dedication, and teamwork throughout this project. Working together has been a rewarding experience, and we truly could not have completed this without each of you.

— *The Three Musketeers* ✨

<p align="center">
  <img src="https://media.giphy.com/media/PBjYnVtoEMfilxyyw9/giphy.gif" alt="Pixel Game" width="400"/>
</p>