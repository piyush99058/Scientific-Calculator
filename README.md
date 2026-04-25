# 🧮 Scientific Calculator

A feature-rich **Scientific Calculator** built with **Java Swing**, featuring a modern dark-themed UI and support for both basic arithmetic and advanced scientific operations.

---

## 📸 Preview

> Dark-themed GUI with color-coded buttons for intuitive usage.

---

## ✨ Features

- **Basic Arithmetic** — Addition, Subtraction, Multiplication, Division
- **Scientific Functions** — `sin`, `cos`, `tan`, `log`, `ln`, `√`
- **Advanced Operations** — Power (`x^y`), Factorial (`!`), Percentage (`%`)
- **Mathematical Constants** — `π` (Pi), `e` (Euler's Number)
- **Utility Buttons** — Toggle sign (`±`), Parentheses `( )`, Decimal point
- **Clear Options** — `C` (clear entry), `AC` (all clear), `DEL` (backspace)
- **Error Handling** — Division by zero, negative square root, invalid log inputs
- **Chain Calculations** — Supports continuous operations without pressing `=`

---

## 🎨 UI Design

| Button Type         | Color        |
|---------------------|--------------|
| Number Buttons      | Dark Gray    |
| Operators (`+`,`-`,`*`,`/`) | Red   |
| Equals (`=`)        | Green        |
| Scientific Functions | Blue        |
| Clear / Delete      | Dark Red     |
| `%`, `(`, `)`       | Purple       |

---

## 🛠️ Tech Stack

- **Language:** Java
- **GUI Framework:** Java Swing (javax.swing)
- **IDE:** Eclipse
- **JDK:** Java 8+

---

## 🚀 Getting Started

### Prerequisites

- Java JDK 8 or higher installed
- Any Java IDE (Eclipse, IntelliJ IDEA) or terminal

### Run via IDE

1. Clone the repository:
   ```bash
   git clone https://github.com/piyush99058/Scientific-Calculator.git
   ```
2. Open the project in your IDE (Eclipse recommended)
3. Run `Calculator.java` as a Java Application

### Run via Terminal

```bash
# Navigate to the project folder
cd Scientific-Calculator

# Compile
javac Calculator.java

# Run
java Calculator
```

---

## 📁 Project Structure

```
Scientific-Calculator/
│
├── Calculator.java       # Main application file (UI + Logic)
└── README.md             # Project documentation
```

---

## 📌 How to Use

1. **Enter numbers** using the number buttons
2. **Select an operator** (`+`, `-`, `*`, `/`, `x^y`)
3. **Enter the second number**
4. Press **`=`** to get the result
5. For scientific functions, **enter the number first**, then press the function button (e.g., type `45` → press `sin`)

---

## 🧠 Author

**Piyush** — [GitHub Profile](https://github.com/piyush99058)

> Built as a portfolio project during Java development learning journey.

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
