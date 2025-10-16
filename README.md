# Clojure Counter App

A beautiful full-stack Clojure application with ClojureScript frontend and Datomic backend.

## Features

- 2 counters (A and B) with real-time updates
- Smooth slide selector to switch between counters
- Increase selected counter
- Reset selected counter
- Persistent storage in Datomic
- Beautiful gradient UI with animations
- Fully responsive design

## Setup & Run

### Terminal 1 - Compile ClojureScript:

```bash
cd clojure-counter
clojure -M:dev -m shadow.cljs.devtools.cli watch app
```

### Terminal 2 - Start Backend Server:

```bash
clojure -M -m counter.server
```

### Access:

Open http://localhost:3000

## Structure

- `src/counter/core.cljs` - ClojureScript frontend (vanilla JS, no React)
- `src/counter/server.clj` - Clojure backend (http-kit + Compojure)
- `src/counter/db.clj` - Datomic database layer
- `resources/public/index.html` - HTML entry point with integrated CSS

## UI Features

- **Gradient Background**: Purple gradient backdrop
- **Card-based Counters**: Visual feedback with elevation on selection
- **Slide Toggle**: Smooth left-right selector with animated thumb
- **Modern Buttons**: Gradient primary button and secondary actions
- **Responsive**: Works on mobile and desktop
- **Smooth Animations**: All state changes are animated
