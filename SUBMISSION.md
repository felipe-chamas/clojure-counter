# Clojure Counter - Submission Summary

## Project Overview

A full-stack Clojure web application demonstrating:

- ClojureScript frontend with vanilla DOM manipulation
- Clojure backend with http-kit web server
- Datomic in-memory database for persistence
- Beautiful, modern UI with integrated CSS

## Key Features

### Frontend (ClojureScript)

- **Vanilla DOM rendering** - No React/Reagent, pure ClojureScript
- **Slide toggle selector** - Smooth animated left-right switch between counters A and B
- **Real-time updates** - Fetch API calls to backend with promise chains
- **Clean state management** - Single atom for app state

### Backend (Clojure)

- **http-kit server** - Async HTTP server on port 3000
- **Compojure routing** - REST API with JSON middleware
- **Datomic persistence** - In-memory database with schema

### UI/UX

- **Purple gradient background** - Modern aesthetic
- **Card-based counter display** - Visual feedback on selection
- **Animated slide toggle** - Smooth thumb animation with labels
- **Gradient buttons** - Primary/secondary action styling
- **Fully responsive** - Mobile and desktop optimized
- **Smooth transitions** - All state changes animated

## Code Simplification

All code has been simplified for clarity:

- Minimal inline styling removed in favor of CSS classes
- Concise function definitions
- Clear separation of concerns
- Single-expression functions where possible

## File Structure

```
clojure-counter/
├── src/counter/
│   ├── core.cljs       # Frontend app (70 lines)
│   ├── server.clj      # HTTP server (25 lines)
│   └── db.clj          # Database layer (28 lines)
├── resources/public/
│   └── index.html      # HTML + CSS (230 lines)
├── deps.edn            # Dependencies
├── shadow-cljs.edn     # ClojureScript config
└── README.md           # Documentation
```

## Running the Application

**Terminal 1:**

```bash
cd clojure-counter
clojure -M:dev -m shadow.cljs.devtools.cli watch app
```

**Terminal 2:**

```bash
clojure -M -m counter.server
```

**Browser:**
Open http://localhost:3000

## Technical Highlights

1. **No Framework Overhead** - Direct DOM manipulation in ClojureScript
2. **Type Safety** - Clojure's immutable data structures
3. **Database Transactions** - Atomic updates with Datomic
4. **Functional Design** - Pure functions, minimal side effects
5. **Modern CSS** - Flexbox, CSS Grid, animations, gradients
6. **REST API** - Clean JSON endpoints

## API Endpoints

- `GET /` - Serve HTML
- `GET /api/counters` - Fetch both counter values
- `POST /api/increase/:id` - Increment counter A or B
- `POST /api/reset/:id` - Reset counter A or B to 0

## Dependencies

- Clojure 1.11.1
- ClojureScript 1.11.60
- Datomic Free 0.9.5697
- http-kit 2.7.0
- Compojure 1.7.0
- Ring JSON/Defaults
- Shadow-CLJS 2.26.2

## Submission Status

✅ Slide selector implemented (left-right toggle)
✅ Beautiful integrated CSS styling
✅ Code simplified and cleaned
✅ Fully functional and tested
✅ Ready for review
